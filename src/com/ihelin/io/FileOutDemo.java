package com.ihelin.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutDemo {

	public static void main(String[] args) throws IOException {
		// 如果该文件不存在，则直接创建，如果存在，则删除后创建
		FileOutputStream out = new FileOutputStream("demo/out.dat");//如要追加，则参数后append true
		out.write('A');// 写出了'A'的低八位
		out.write('B');// 写出了'B'的低八位
		int a = 10;// write只能写八位，那么写一个int需要写4次，每次8位
		out.write(a >>> 24);
		out.write(a >>> 16);
		out.write(a >>> 8);
		out.write(a);
		byte[] utf8 = "中国".getBytes("utf-8");
		out.write(utf8);
		
		IOUtil.printHex("demo/out.dat");
		
		
		out.close();
	}
}
