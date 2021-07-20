package util;

import java.io.File;

public class FindFile {
	public static String findfile(int bid, String path) {
		File file = new File(path); // ��ȡ��file����
		File[] fs = file.listFiles(); // ����path�µ��ļ���Ŀ¼������File������
		for (File f : fs) { // ����File[]����
			if (!f.isDirectory()) { // ����Ŀ¼(���ļ�)�����ӡ
				String fileName = f.getName();
				String caselsh = fileName.substring(0, fileName.lastIndexOf("."));
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (caselsh .equals(Integer.toString(bid)) ) {//ע������,��Ҫ==
					return suffix;
				}
			}
		}
		return "";
	}
}
