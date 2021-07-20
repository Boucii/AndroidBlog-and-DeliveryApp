package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DManager;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		session.setAttribute("haspic", 0);
		session.setAttribute("format", "");
		//System.out.println(username);
		//System.out.println(password);
		DManager dm = DManager.getdm();
		try {
			int flag = dm.namelogin(username, password);
			if (flag >= 0) {// 如果验证码正确
				session.setAttribute("UserID", flag);
				System.out.println(session);
				out.print("loginsuccess!");
			} else if (flag == -1) {
				out.print("namenotfound");
				out.flush();
				out.close();
			} else {
				out.print("innerproblem");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			out.print("innerproblem");
			out.flush();
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
