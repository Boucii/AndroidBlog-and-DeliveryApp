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

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CommentServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession s = request.getSession();
		Object cur =s.getAttribute("currentID");//当前博客的bid
		int id=(int)s.getAttribute("UserID");
		if(cur==null) {
			cur=-1;
		}
		String comment=(String) request.getParameter("comment");
		
		DManager dm = DManager.getdm();
		int n=dm.comment((int)cur, comment, id);
		String res="rats--and that was an error=_=";
		if (n==1){
			res="commentsuccessful!";
		}
		//System.out.println(comment);
		//System.out.println(res);
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();	
		out.print(res);
		out.flush();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
