package com.colson.baseKnowledge;

import java.io.*;

public class TestFile {
	//写文件，方式一
	public static void writeFile1(){
		try{
			String filePath = "D://testFile.sql";
			File file = new File(filePath);
			if (!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("这里是要测试文件");
			bw.newLine();
			bw.write("这里是测试的第二行");
			bw.close();
		}catch (Throwable ta){
			ta.printStackTrace();
		}
	}

	//写文件，方式二
//	public static void writeFile2(){
//		try{
//			String filePath = "D://testFile2.sql";
//
//		}
//	}

	//读文件


	public static void main(String[] args) {
		TestFile.writeFile1();
	}
}
