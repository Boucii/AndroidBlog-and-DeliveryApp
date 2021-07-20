package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Authenticator {
//权限管理类,修改权限,读取权限(从文件),获取权限
	public static Authenticator auth=new Authenticator();
	public static int authlevel=5;
	public static Authenticator getauth() {
		return auth;
	}
	public void changelevel(int leveltobe) {
		System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
		String fileName="H:\\java\\workspace\\tstwork\\QRcode/auth.txt";
        try
        {
                FileWriter writer=new FileWriter(fileName);
                writer.write(Integer.toString(leveltobe));
                writer.close();
                authlevel=leveltobe;
                System.out.println("权限已更改"+authlevel);
        } catch (Exception e)
        {
                e.printStackTrace();
        }
	}
	public void readlevel() {
		String fileName="H:\\java\\workspace\\tstwork\\QRcode/auth.txt";
        int c=0;
        try
        {
                FileReader freader=new FileReader(fileName);
        	    BufferedReader reader = null;
        	    reader = new BufferedReader(freader); 	    
        	    String tempString = null;
        	    tempString = reader.readLine();
                reader.close();
                authlevel=Integer.parseInt(tempString);
                System.out.println(authlevel);
        } catch (Exception e) {
                e.printStackTrace();
        }
	}
	public static void main(String[] args) {
		Authenticator auth=Authenticator.getauth();
		auth.readlevel();
	}
}
