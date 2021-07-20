package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBManager;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		//System.out.println(username);	
		String password = request.getParameter("password");
		//System.out.println(password);
		DBManager dm = DBManager.getdm();
			try {
				int flag = dm.register(username, password);
				if (flag >= 0) {
					out.print("registersuccess!");
					out.flush();
					out.close();
				} else if (flag == -1) {
					out.print("duplicatedname");
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
