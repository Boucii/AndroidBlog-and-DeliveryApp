package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DManager {
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// ����
	private String url = "jdbc:sqlserver://Localhost:1433;DatabaseName=Blogs";// ·��,Ĭ�϶˿�1433;DatabaseName �����ݿ�����
	private String uname = "";// ��¼��
	private String password = "";// ����
	private Connection con = null;
	private Statement sta = null;
	private ResultSet rs = null;
	private static DManager dm = new DManager();

	public static DManager getdm() {
		return dm;
	
	}

	private DManager() {
		// ��������
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("���������쳣");
		}

		// ����
		try {
			con = DriverManager.getConnection(url, uname, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ������쳣");
		}
		if (con != null) {
			System.out.println("���ݿ����ӳɹ�");
		}
	}

	public int getnextCID() {
		try {
			String sql = "select max(CID) from comments";
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			id = id + 1;
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getnextBID() {
		try {
			String sql = "select max(BID) from blog";
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			id = id + 1;
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public String getuname(int uid) {
		try {

			String sql = "select UserName from users where UserID=" + String.valueOf(uid);
			// System.out.println(sql);
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			String name = null;
			while (rs.next()) {
				name = rs.getString(1);
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int comment(int BID, String comment, int userID) {
		int cid = getnextCID();
		String sql = "insert into comments values(" + String.valueOf(cid) + ',' + String.valueOf(userID) + ",'"
				+ comment + "')";
		String sql2 = "insert into bNc values(" + String.valueOf(BID) + ',' + String.valueOf(cid) + ")";
		// System.out.println(sql);
		// System.out.println(sql2);
		try {
			sta = con.createStatement();
			int n = sta.executeUpdate(sql);
			n = sta.executeUpdate(sql2);
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Blog getnextBlog(int BID) {
		// System.out.println("---------------");
		// System.out.println(BID);
		int bidmax = getnextBID() - 1;
		int bid = (BID + 1) % (bidmax + 1);
		String sql = "select * from blog where BID=" + String.valueOf(bid);
		try {
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			int bid2 = -1;
			int uid = -1;
			String text = "";
			int haspic=0;
			while (rs.next()) {
				bid2 = rs.getInt("BID");
				uid = rs.getInt("userID");
				text = rs.getString("Btext");
				haspic=rs.getInt("haspic");
			}
			String uname = getuname(uid);
			Blog temp = new Blog(bid2, uid, text, uname,haspic);
			return temp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Blog getnextmyBlog(int BID, int userID) {
		// System.out.println("---------------");
		// System.out.println(BID);
		int bidmax = getnextBID() - 1;
		int bid = (BID + 1) % (bidmax + 1);
		String sql = "select * from blog where BID>=" + String.valueOf(bid) + " and UserID=" + String.valueOf(userID);
		try {
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			int bid2 = -1;
			int uid = -1;
			String text = "";
			int haspic=0;
			if (rs.next()) {// ������if ������while
				bid2 = rs.getInt("BID");
				uid = rs.getInt("userID");
				text = rs.getString("Btext");
				haspic=rs.getInt("haspic");
			}
			String uname = getuname(uid);
			Blog temp = new Blog(bid2, uid, text, uname,haspic);
			return temp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Blog createBlog(int userid, String text,int haspic) {
		int bid = getnextBID();
		String sql = "insert into blog values(" + String.valueOf(bid) + ',' + String.valueOf(userid) + ",'" + text
				+ "',"+String.valueOf(haspic)+",'')";
		String uname = getuname(userid);
		try {
			sta = con.createStatement();
			int n = sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Blog temp = new Blog(bid, userid, text, uname,haspic);
		return temp;
	}

	public int getnextUID() {
		String sql = "select max(UserID) from users";
		int id = 0;
		try {
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			
			while (rs.next()) {
				id = rs.getInt(1);
			}
			id = id + 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return id;
	}

	public int register(String name, String pwd) {
		String sql = "select * from users where UserName='" + name + "'";
		try {
			int i = 0;
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			int bid = -1;
			while (rs.next()) {
				i++;
				bid = rs.getInt("UserID");
			}
			if (i == 0) {// û�ø��û���,ע��
				sql = "select max(UserID) from users";
				sta = con.createStatement();
				rs = sta.executeQuery(sql);
				int id = 0;
				while (rs.next()) {
					id = rs.getInt(1);
				}
				id = id + 1;
				sql = "insert into users values(" + id + ",'" + name + "','" + pwd + "')";
				sta = con.createStatement();
				int n = sta.executeUpdate(sql);
				return id;
			} else if (i == 1) {
				return -1;// �и��û�
			} else {
				return -2;// ������
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 2;

	}

	public int namelogin(String name, String pwd) {
		String sql = "select * from users where UserName='" + name + "'and pwd='" + pwd + "'";
		try {
			int i = 0;
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			int bid = -1;
			while (rs.next()) {
				i++;
				bid = rs.getInt("UserID");
			}
			if (i == 0) {
				return -1;// û�и��û�
			} else if (i == 1) {
				return bid;// �и��û�
			} else {
				return -2;// ������(�����û����ظ�)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 2;
	}

	public Blog getcomments(Blog b) {
		ArrayList list = new ArrayList();
		String sql = "select * from bNc,comments where BID=" + String.valueOf(b.BID) + " and bNc.CID=comments.CID";
		try {
			// System.out.println(sql);
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			ResultSet rs2 = rs;// ע������,��Ȼ����getuname����
			int uid = -1;
			String comment;
			while (rs2.next()) {
				uid = rs2.getInt("UserID");
				comment = rs2.getString("comment");
				// System.out.println(uid);
				// System.out.println(comment);
				String name = getuname(uid);// --����,���� �ҿ�
				b.commentusers.add(name);
				b.comments.add(comment);
			}
			System.out.println(b.comments);
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		/*
		 * DManager dm = DManager.getdm(); Blog next = dm.getnextBlog(0); next =
		 * dm.getcomments(next); String res = new Gson().toJson(next);
		 * System.out.println(res); int a=dm.namelogin("admin", "1234567");
		 * System.out.print(a); Blog next = dm.getnextmyBlog(2,0); String res = new
		 * Gson().toJson(next); System.out.println(res);
		 */
	}
}
