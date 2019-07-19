package com.colson.renHangFileReader;


import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUpdateReader {

	@Test
	public void updateData(){
		String sourceFilePath = "D:\\a_update\\DI_GR_ZYXX.sql";

		String outPutFilePath = "D:\\a_update\\DI_GR_ZYXX_OUT.sql";

		File inFile = new File(sourceFilePath);

		File outFile = new File(outPutFilePath);

		try{
			if (!outFile.exists()){
				outFile.createNewFile();
			}
		}catch (IOException io){
			io.printStackTrace();
		}

		try {
			FileOutputStream fos = new FileOutputStream(outFile,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			List<String> contentLine = Files.readAllLines(inFile.toPath(), Charset.forName("UTF-8"));

			for (String s:contentLine) {
				String[] split = s.split(",");

				StringBuilder stringBuilder = new StringBuilder();
				Map<String,Object> data = new HashMap<>();
				data.put("biddingNo",split[0]);
				stringBuilder.append(split[0]);

				if (split[1].equals("")){
					stringBuilder.append("Z");
				}else {
					stringBuilder.append(split[1]);
				}

				if ("".equals(split[2])){
					stringBuilder.append("暂缺");
				}else {
					stringBuilder.append(split[2]);
				}

				if ("".equals(split[3])){
					stringBuilder.append("Z");
				}else {
					stringBuilder.append(split[3]);
				}

				stringBuilder.append(split[4]);
				stringBuilder.append(split[5]);
				stringBuilder.append(split[6]);
				stringBuilder.append(split[7]);
				stringBuilder.append(split[8]);
				stringBuilder.append(split[9]);
				stringBuilder.append(split[10]);
				stringBuilder.append(split[11]);

				bw.write(stringBuilder.toString());
				bw.newLine();
				bw.flush();
			}
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void retainData(){
		String sourceFilePath = "D:\\a_update\\DI_GR_ZYXX.sql";

		String retainFilePath = "D:\\a_update\\DI_GR_GRXX_JC.sql";

		String outPutFilePath = "D:\\a_update\\DI_GR_ZYXX_OUT.sql";

		File inFile = new File(sourceFilePath);

		File retainFile = new File(retainFilePath);

		File outFile = new File(outPutFilePath);

		try{
			if (!outFile.exists()){
				outFile.createNewFile();
			}
		}catch (IOException io){
			io.printStackTrace();
		}

		try {
			HashMap<String,String> retainMap = new HashMap<>();
			List<String> retainLines = Files.readAllLines(retainFile.toPath(), Charset.forName("UTF-8"));


			for (String retain:retainLines) {
				String[] split = retain.split("\\|");

				retainMap.put(split[3],split[3]);
			}

			FileOutputStream fos = new FileOutputStream(outFile,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			List<String> contentLine = Files.readAllLines(inFile.toPath(), Charset.forName("UTF-8"));

			for (String s:contentLine) {
				String[] split = s.split("\\|");

				String biddingNo = split[0];
				if (!retainMap.containsKey(biddingNo)){
					continue;
				}
				bw.write(s);
				bw.newLine();
				bw.flush();
			}
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
