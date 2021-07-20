package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import util.CodeUtil;
import util.DBManager;
import util.Package;

@WebServlet("/NewPackageServlet")
public class NewPackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NewPackageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		DBManager dm=DBManager.getdm();
		String sendername=request.getParameter("sendername");
		String receivername=request.getParameter("receivername");
		String Address=request.getParameter("addr");
		String receievertel=request.getParameter("tel");
		Package p=dm.createpackage(sendername, receivername, Address, receievertel);
		//String res = new Gson().toJson(p);
		Package pmodified=dm.getpackage(p.PID);
		String res = new Gson().toJson(pmodified);
		String path="H:\\java\\workspace\\tstwork\\QRcode\\qrs\\"+Integer.toString(p.PID)+".png";
		System.out.println(path);
		CodeUtil.getBarCode(res,path);
		System.out.println(res);
		out.print("createsuccess");
		out.flush();
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
