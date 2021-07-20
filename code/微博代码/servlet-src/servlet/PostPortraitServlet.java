package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DManager;


@WebServlet("/PostPortraitServlet")
public class PostPortraitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostPortraitServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String file=(String) request.getParameter("file");
		Decoder decoder = Base64.getDecoder();  
         // ȥ��base64�����ͷ�� �磺"data:image/jpeg;base64," �����ȥ��ת����ͼƬ�����Բ鿴  
         file = file.substring(23);  
                 //����  
         byte[] imgByte = decoder.decode(file);
         DManager dm=DManager.getdm();
         int picid=dm.getnextUID();
         String res="not okey!";
         try {  
        	 String path = this.getServletContext().getRealPath("/assets/userimgs/"+Integer.toString(picid)+".jpg");
             System.out.println("postportrait---"+path);
        	 FileOutputStream fout = new FileOutputStream(path);// ����ļ�·��  
             fout.write(imgByte);  
             fout.close();  
             HttpSession s = request.getSession();
             s.setAttribute("portdone",1);//Ф��ͼƬ�ϴ����
             res="portuploadsuccess";
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
