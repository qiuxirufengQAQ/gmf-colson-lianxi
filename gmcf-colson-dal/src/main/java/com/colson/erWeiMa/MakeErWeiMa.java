package com.colson.erWeiMa;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class MakeErWeiMa {
	public void encode(String contents, int width, int height, String imgPath)
	{
		try
		{
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, width, height);
			MatrixToImageWriter
					.writeToFile(bitMatrix, "png", new File(imgPath));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		String imgPath = "D:\\照片\\雏田12.jpg";
		String contents = "别做梦了，好好工作吧，七夕节快乐！";
		try
		{
			// 如果不用更改源码，将字符串转换成ISO-8859-1编码
			contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		int width = 300, height = 300;
		MakeErWeiMa handler = new MakeErWeiMa();
		handler.encode(contents, width, height, imgPath);

		System.out.println("生成二维码成功！");
	}
}
