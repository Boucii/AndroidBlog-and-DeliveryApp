package util;

import java.util.ArrayList;
import java.util.Iterator;

public class Blog {
	public int BID = -1;
	public int userID = -1;
	String userName="";
	String text = "";
	ArrayList<String> commentusers = new ArrayList<String>();
	ArrayList<String> comments = new ArrayList<String>();
	int haspic=0;
	static  DManager dm = DManager.getdm();

	public void Comment(String comment) {
		dm.comment(BID, comment, userID);
	}

	public Blog(int BID, int userID, String text,String userName,int haspic) {
		this.BID = BID;
		this.userID = userID;
		this.text = text;
		this.userName=userName;
		this.haspic=haspic;
	}
	
}
