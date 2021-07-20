package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DBManager {
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 驱动
	private String url = "jdbc:sqlserver://Localhost:1433;DatabaseName=QRexpress";// 路径,默认端口1433;DatabaseName 是数据库名称
	private String uname = "";// 登录名
	private String password = "";// 密码
	private Connection con = null;
	private Statement sta = null;
	private ResultSet rs = null;
	private static DBManager dm = new DBManager();

	public static DBManager getdm() {
		return dm;
	}

	private DBManager() {
		// 加载驱动
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("驱动加载异常");
		}

		// 连接
		try {
			con = DriverManager.getConnection(url, uname, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接异常");
		}
		if (con != null) {
			System.out.println("数据库连接成功");
		}
	}
public Package createpackage(String a,String b,String c,String d) {
	int pid = getnextpid();
	String sql = "insert into packages values(" + String.valueOf(pid) + ",'" +a+ "','" +b+ "','" +c+ "','" +d + "',"+0+")";
	try {
		sta = con.createStatement();
		int n = sta.executeUpdate(sql);
	} catch (Exception e) {
		e.printStackTrace();
	}
	Package temp = new Package(pid,a,b,c,d,0);
	return temp;	
}
public int getnextpid() {
	try {
		String sql = "select max(PID) from packages";
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
public Package getpackage(int pid) {
	int level=Authenticator.authlevel;
	String sql = "select * from packages where PID="+pid;
	try {
		sta = con.createStatement();
		rs = sta.executeQuery(sql);
		String sendername="defaultsender";
		String receivername="defaultreceiver";
		String Address="defaultaddr";
		String receievertel="12345678900";
		int state=-1;
		Package p=new Package(-1,sendername,receivername,Address,receievertel,-1);
		while (rs.next()) {
			sendername = rs.getString("sendername");
			receivername=rs.getString("receivername");
			Address=rs.getString("addr");
			receievertel=rs.getString("receievertel");
			state=rs.getInt("pstate");
		}
		if((level&1)==1) {
			p.sendername=sendername;
		}
		if((level&2)==2) {
			p.receivername=receivername;
		}
		if((level&4)==4) {
			p.Address=Address;
		}
		if((level&8)==8) {
			p.receievertel=receievertel;
		}
		p.PID=pid;
		p.state=state;
		return p;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	for (int i=0;i<level;i++) {
		if((i&level)==1) {
			
		}
	}
	return null;
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
			if (i == 0) {// 没用该用户名,注册
				sql = "select max(UserID) from users";
				sta = con.createStatement();
				rs = sta.executeQuery(sql);
				int id = 0;
				while (rs.next()) {
					id = rs.getInt(1);
				}
				id = id + 1;
				sql = "insert into users values(" + id + ",'" + name + "','" + pwd + "',"+0+")";
				sta = con.createStatement();
				int n = sta.executeUpdate(sql);
				return id;
			} else if (i == 1) {
				return -1;// 有该用户
			} else {
				return -2;// 出问题
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
			int ident=-1;
			while (rs.next()) {
				i++;
				bid = rs.getInt("UserID");
				ident=rs.getInt("ident");
			}
			if (i == 0) {
				return -1;// 没有该用户
			} else if (i == 1&&ident==0) {
				return bid;// 有该用户
			} else if (i == 1&&ident==1) {
				return -3;// 管理员
			} else {
				return -2;// 出问题(比如用户名重复)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 2;
	}


	public static void main(String[] args) {
	  DBManager dm = DBManager.getdm(); 
	  //Package next =dm.getpackage(0); 
	  //String res = new Gson().toJson(next);
	  //System.out.println(res); 
	  //System.out.println(dm.getnextpid());
	  //dm.createpackage("aa", "bb", "cc", "dd");
	  //dm.register("admin", "1234567");
		int a=dm.namelogin("admin", "1234567") ;
		System.out.println(a); 
	}
}
