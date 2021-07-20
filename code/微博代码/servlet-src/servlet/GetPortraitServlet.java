package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GetPortraitServlet")
public class GetPortraitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPortraitServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int uid=Integer.parseInt(request.getParameter("uid"));
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		
		String path = this.getServletContext().getRealPath("/assets/userimgs/"+Integer.toString(uid)+".jpg");
        FileInputStream fis = new FileInputStream(path); 
        ServletOutputStream outputStream = response.getOutputStream();    
        String fileName = path.substring(path.lastIndexOf("\\")+1);
        System.out.println(fileName);
        fileName = URLEncoder.encode(fileName,"UTF-8");
        response.setHeader("content-disposition", "attachment;filename="+fileName);
        response.setHeader("content-type", "image/jpeg");
        int len = 1;
        byte[] b = new byte[1024];
        while((len=fis.read(b))!=-1){
            outputStream.write(b, 0, len);
        }
        outputStream.close();
        fis.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
