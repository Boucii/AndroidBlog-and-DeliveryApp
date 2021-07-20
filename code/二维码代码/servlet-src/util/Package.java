package util;

public class Package {
public int PID=-1;
String sendername="defaultsender";
String receivername="defaultreceiver";
String Address="defaultaddr";
String receievertel="12345678900";
int state=-1;//-1未初始化,0已收入,1送达,2配送中
public Package(int pid,String a,String b,String c,String d,int statein) {
	PID=pid;
	sendername=a;
	receivername=b;
	Address=c;
	receievertel=d;
	state=statein;
}
}
