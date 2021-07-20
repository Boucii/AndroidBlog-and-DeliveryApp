package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import util.Blog;
import util.DManager;

@WebServlet("/FetchMineServlet")
public class FetchMineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FetchMineServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession s = request.getSession();
		Object cur =s.getAttribute("currentID");//当前博客的bid
		System.out.println(s);
		int id=(int)s.getAttribute("UserID");
		//System.out.println("id是");
		//System.out.println(id);
		if(cur==null) {
			cur=-1;
		}
		int curid = (int)cur;
		DManager dm = DManager.getdm();
		Blog next = dm.getnextmyBlog(curid,id);
		s.setAttribute("currentID", next.BID);
		next=dm.getcomments(next);//把评论读到博客结构体里面
		if(next.BID==-1) {
			next.userID=id;
		}
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();	
		String res=new Gson().toJson(next);
		out.print(res);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
