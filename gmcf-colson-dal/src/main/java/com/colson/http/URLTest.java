package com.colson.http;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {

	//查看URL类的方法
	@Test
	public void testURL(){
		try
		{
			URL url = new URL("http://www.cnblogs.com/index.html?language=cn#j2se");
			System.out.println("URL is " + url.toString());
			//URL is http://www.cnblogs.com/index.html?language=cn#j2se
			System.out.println("protocol is " + url.getProtocol());
			//protocol is http
			System.out.println("authority is " + url.getAuthority());
			//authority is www.cnblogs.com
			System.out.println("file name is " + url.getFile());
			//file name is /index.html?language=cn
			System.out.println("host is " + url.getHost());
			//host is www.cnblogs.com
			System.out.println("path is " + url.getPath());
			//path is /index.html
			System.out.println("port is " + url.getPort());
			//port is -1
			System.out.println("default port is " + url.getDefaultPort());
			//default port is 80
			System.out.println("query is " + url.getQuery());
			//query is language=cn
			System.out.println("ref is " + url.getRef());
			//ref is j2se
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	//测试URLConnection类
	@Test
	public void testURLConnection(){
		try
		{
			URL url = new URL("http://www.cnblogs.com");
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection connection = null;
			if(urlConnection instanceof HttpURLConnection)
			{
				connection = (HttpURLConnection) urlConnection;
			} else {
				System.out.println("Please enter an HTTP URL.");
				return;
			}
			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			String urlString = "";
			String current;
			while((current = in.readLine()) != null)
			{
				urlString += current;
			}
			System.out.println(urlString);
			//head><title>Document Moved</title></head><body><h1>Object Moved</h1>This document may be found <a HREF="https://www.cnblogs.com/">here</a></body>
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
