package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Blog;
import util.DManager;

@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BlogServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession s = request.getSession();
		int id=(int)s.getAttribute("UserID");
		int haspic=(int)s.getAttribute("haspic");
		String btext=(String) request.getParameter("btext");
		DManager dm = DManager.getdm();
		Blog blog=dm.createBlog(id,btext,haspic);
		s.setAttribute("haspic", 0);
		s.setAttribute("format", "");
		String res="rats--and that was an error=_=";
		if (blog!=null){
			res="postsuccessful!";
		}
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
