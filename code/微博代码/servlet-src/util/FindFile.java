package util;

import java.io.File;

public class FindFile {
	public static String findfile(int bid, String path) {
		File file = new File(path); // 获取其file对象
		File[] fs = file.listFiles(); // 遍历path下的文件和目录，放在File数组中
		for (File f : fs) { // 遍历File[]数组
			if (!f.isDirectory()) { // 若非目录(即文件)，则打印
				String fileName = f.getName();
				String caselsh = fileName.substring(0, fileName.lastIndexOf("."));
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (caselsh .equals(Integer.toString(bid)) ) {//注意这里,不要==
					return suffix;
				}
			}
		}
		return "";
	}
}
