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

@WebServlet("/fetchblogservlet")
public class fetchblogservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public fetchblogservlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession s = request.getSession();
		Object cur =s.getAttribute("currentID");//当前博客的bid
		System.out.println(cur);
		Object idobj=s.getAttribute("UserID");
		if(idobj==null) {
			return;
		}
		int id=(int)s.getAttribute("UserID");
		if(cur==null) {
			cur=-1;
		}
		
		int curid = (int)cur;
		DManager dm = DManager.getdm();
		Blog next = dm.getnextBlog(curid);
		s.setAttribute("currentID", next.BID);
		next=dm.getcomments(next);//把评论读到博客结构体里面
		
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();	
		String res=new Gson().toJson(next);
		out.print(res);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
