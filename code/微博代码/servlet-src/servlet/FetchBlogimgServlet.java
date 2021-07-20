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

import util.FindFile;


@WebServlet("/FetchBlogimgServlet")
public class FetchBlogimgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FetchBlogimgServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			int bid=Integer.parseInt(request.getParameter("bid"));
			//HttpSession session = request.getSession();		
			String path = this.getServletContext().getRealPath("/assets/blogimgs/");
			System.out.println(path);
			String suffix=FindFile.findfile(bid, path);
			System.out.println(suffix);
			path=path+Integer.toString(bid)+"."+suffix;
			FileInputStream fis = new FileInputStream(path); 
	        ServletOutputStream outputStream = response.getOutputStream();    
	        String fileName = path.substring(path.lastIndexOf("\\")+1);
	        //System.out.println(fileName);
	        fileName = URLEncoder.encode(fileName,"UTF-8");
	        response.setHeader("content-disposition", "attachment;filename="+fileName);
	        response.setHeader("content-type", "image/"+suffix);
	        int len = 1;
	        byte[] b = new byte[1024];
	        while((len=fis.read(b))!=-1){
	            outputStream.write(b, 0, len);
	        }
	        outputStream.close();
	        fis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
