package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Authenticator;

@WebServlet("/UpdateAuthServlet")
public class UpdateAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateAuthServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Authenticator auth=Authenticator.getauth();
		response.setContentType("text/plain");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String authtobe = request.getParameter("authtobe");
		auth.changelevel(Integer.parseInt(authtobe));
		out.print("updatesuccess");
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
