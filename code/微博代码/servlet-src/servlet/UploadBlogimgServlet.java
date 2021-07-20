package servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.DManager;

@WebServlet("/UploadBlogimgServlet")
public class UploadBlogimgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public UploadBlogimgServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String file=(String) request.getParameter("file");
		//Decoder decoder = Base64.getDecoder();  
         // 去掉base64编码的头部 如："data:image/jpeg;base64," 如果不去，转换的图片不可以查看 
		 Decoder decoder =Base64.getMimeDecoder();
		 //System.out.println(file.length());
         file = file.substring(22);  
         file = file.replaceAll(" ", "+");
         //System.out.println(file.length());
                 //解码  
         byte[] imgByte = decoder.decode(file);
		//byte[] imgByte = Base64.getDecoder().decode(file);
         DManager dm=DManager.getdm();
         int bid=dm.getnextBID();
         String res="not okey!";
         try {  
        	 String path = this.getServletContext().getRealPath("/assets/blogimgs/"+Integer.toString(bid)+".png");
             System.out.println("postpic---"+path);
        	 FileOutputStream fout = new FileOutputStream(path);// 输出文件路径  
             fout.write(imgByte);  
             fout.close();  
             HttpSession s = request.getSession();
             s.setAttribute("haspic",1);//图片上传完毕
             res="picuploadsuccess";
         } catch (Exception e) {  
             e.printStackTrace();  
         }  
        response.setContentType("text/plain;charset=UTF-8");
 		response.setCharacterEncoding("UTF-8");
 		PrintWriter out = response.getWriter();	
 		out.print(res);
 		out.flush(); 

	}

}
