package com.colson.fileReader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.colson.dal.FeeDto;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;


public class FileReader{

	@Resource
	private FeeDto feeDto;



	public static void main(String[] args) throws Exception{

		//获取追踪编号
		getTraceId(FileEnum.TRACEID.getSourceFilePath(),FileEnum.TRACEID.getResultFilePath());

		//从JSON中获取预扣恢复的请求
//		getOutput("预扣恢复JSON.sql");

		//从JSON中获取预扣占用的请求
//		getOutput("预扣占用JSON.sql");

		//从JSON中获取其他请求
//		getOutput("其他请求.sql");
	}

	public void testMethod(){
		System.out.println(feeDto.toString());
	}


	@Test
	public void deleteDateFromFile(){
		String deletePath = "D:\\fileReader\\浦发.sql";
		String sourcePath = "D:\\fileReader\\GR_GRSFXX_201905_0.sql";
		String outputPath = "D:\\fileReader\\GR_GRSFXX_201905_1.sql";

		File deleteFile = new File(deletePath);
		File sourceFile = new File(sourcePath);
		File outputFile = new File(outputPath);


		try(
				FileOutputStream fos = new FileOutputStream(outputFile,true);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				) {


			List<String> deleteList = Files.readAllLines(deleteFile.toPath(), Charset.forName("UTF-8"));

			//将要删除的数据放入map中
			Map<String,String> deleteMap = new HashMap<>();

			deleteList.stream().forEach(n->deleteMap.put(n,n));

			//获取要处理的数据
			List<String> sourceList = Files.readAllLines(sourceFile.toPath(), Charset.forName("UTF-8"));

			List<String> outputList = new ArrayList<>();

			sourceList.stream().forEach(n->{
				String[] split = n.split(",");
				if (deleteMap.get(split[0])==null){
					outputList.add(n);
					System.out.println(n);
				}
			});
			System.out.println(outputList.size());
			for (String output : outputList) {
				bw.write(output);
				bw.newLine();
				bw.flush();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	//看文件中去重之后有多少数据
	@Test
	public void testHowManySingleData(){
		String filePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\22万人行数据问题\\789月份数据.sql";
		File file = new File(filePath);

		Set<String> set = new HashSet<>(240000);

		String biddingNo = "";
		try {
			List<String> list = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			for (int i = 0; i < list.size(); i++) {
				biddingNo =list.get(i);
				if (set.contains(biddingNo)){
					System.out.println(biddingNo);
				}
				set.add(biddingNo);
			}
			System.out.println(set.size());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void getOutput(String sourceFilePath) throws Exception{
		String resultPath = "";
		if ("预扣恢复JSON.sql".equals(sourceFilePath)){
			resultPath = "D:\\cqc_error\\未预扣调预扣恢复请求.sql";
			sourceFilePath = "D:\\cqc_error\\预扣恢复JSON.sql";
		}else if ("预扣占用JSON.sql".equals(sourceFilePath)){
			resultPath = "D:\\cqc_error\\未预扣调预扣占用请求.sql";
			sourceFilePath = "D:\\cqc_error\\预扣占用JSON.sql";
		}else {
			resultPath = "D:\\cqc_error\\其他请求.sql";
			sourceFilePath = "D:\\cqc_error\\其他JSON.sql";
		}


		File dataJson = new File(sourceFilePath);

		File file = new File(resultPath);

		try{
			if (!file.exists()){
				file.createNewFile();
			}
		}catch (IOException io){
			io.printStackTrace();
		}

		List<String> contentLine = Files.readAllLines(dataJson.toPath(), Charset.forName("UTF-8"));
		String content = contentLine.get(0);

		JSONObject data = JSONObject.parseObject(content);

		data = data.getJSONArray("responses").getJSONObject(0).getJSONObject("hits");

		JSONArray hits = data.getJSONArray("hits");

		FileOutputStream fos = new FileOutputStream(file,true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for(int i=0;i<hits.size();i++){
			JSONObject source = hits.getJSONObject(i).getJSONObject("_source");
			DataDeal.ErrorMsg errorMsg = new DataDeal.ErrorMsg();
			errorMsg.message = source.getString("message");
			errorMsg.exception = source.getString("exception");
			errorMsg.exceptiontype = source.getString("exceptiontype");
			errorMsg.exceptiontype = source.getString("exceptiontype");
			errorMsg.traceId = source.getString("traceId");
//			errorMsg.method = getMethod(errorMsg.getMessage(),errorMsg.getException());
//			errorMsg.need = !errorMsg.method.equals("未预扣调占用") && !errorMsg.method.equals("额度查询");

			bw.write(errorMsg.message);
			bw.newLine();
		}
		bw.close();
	}

	public static void getTraceId(String sourceFilePath,String resultFilePath) throws Exception{
		File dataJson = new File(sourceFilePath);

		String preOccupyRecoveryPath = "D:\\cqc_error\\预扣恢复traceId.sql";

		String preOccupyUsedRequestPath = "D:\\cqc_error\\预扣占用traceId.sql";

		String otherPath = "D:\\cqc_error\\其他traceId.sql";



		File preOccupyRecoveryFile = new File(preOccupyRecoveryPath);

		File preOccupyUsedRequestFile = new File(preOccupyUsedRequestPath);

		File otherFile = new File(otherPath);

		try{
			if (!preOccupyRecoveryFile.exists()){
				preOccupyRecoveryFile.createNewFile();
			}
			if (!preOccupyUsedRequestFile.exists()){
				preOccupyUsedRequestFile.createNewFile();
			}
			if (!otherFile.exists()){
				otherFile.createNewFile();
			}
		}catch (IOException io){
			io.printStackTrace();
		}

		List<String> contentLine = Files.readAllLines(dataJson.toPath(), Charset.forName("UTF-8"));
		String content = contentLine.get(0);

		JSONObject data = JSONObject.parseObject(content);

		data = data.getJSONArray("responses").getJSONObject(0).getJSONObject("hits");

		JSONArray hits = data.getJSONArray("hits");


		FileOutputStream preOccupyRecoveryFos = new FileOutputStream(preOccupyRecoveryFile,true);
		BufferedWriter preOccupyRecoveryBw = new BufferedWriter(new OutputStreamWriter(preOccupyRecoveryFos));

		FileOutputStream preOccupyUsedRequestFos = new FileOutputStream(preOccupyUsedRequestFile,true);
		BufferedWriter preOccupyUsedRequestBw = new BufferedWriter(new OutputStreamWriter(preOccupyUsedRequestFos));

		FileOutputStream otherFos = new FileOutputStream(otherFile,true);
		BufferedWriter otherBw = new BufferedWriter(new OutputStreamWriter(otherFos));
		for(int i=0;i<hits.size();i++){
			JSONObject source = hits.getJSONObject(i).getJSONObject("_source");
			DataDeal.ErrorMsg errorMsg = new DataDeal.ErrorMsg();
			errorMsg.message = source.getString("message");
			errorMsg.exception = source.getString("exception");
			errorMsg.exceptiontype = source.getString("exceptiontype");
			errorMsg.exceptiontype = source.getString("exceptiontype");
			errorMsg.traceId = source.getString("traceId");
			errorMsg.method = getMethod(errorMsg.getMessage(),errorMsg.getException());
			errorMsg.need = !errorMsg.method.equals("未预扣调占用") && !errorMsg.method.equals("额度查询");

			String[] exceptions = errorMsg.exception.split("\\n");

			if ("预扣恢复".equals(errorMsg.method)){
				preOccupyRecoveryBw.write(errorMsg.traceId+"			"+errorMsg.method+"			"+exceptions[0].substring(exceptions[0].indexOf(" ")));
				preOccupyRecoveryBw.newLine();
			}else if ("未预扣调占用".equals(errorMsg.method) || "预扣占用".equals(errorMsg.method)){
				preOccupyUsedRequestBw.write(errorMsg.traceId+"			"+errorMsg.method+"			"+exceptions[0].substring(exceptions[0].indexOf(" ")));
				preOccupyUsedRequestBw.newLine();
			}else {
				otherBw.write(errorMsg.traceId+"			"+errorMsg.method+"			"+exceptions[0].substring(exceptions[0].indexOf(" ")));
				otherBw.newLine();
			}

		}
		preOccupyRecoveryBw.close();
		preOccupyUsedRequestBw.close();
		otherBw.close();
	}
	public static String getMethod(String message,String exception){
		if(message.indexOf("CreateQuotaRequest") >= 0){
			return "额度激活";
		}else if(message.indexOf("PreOccupyQuotaRequest") >= 0){
			return "额度预扣";
		}else if(message.indexOf("PreOccupyRecoveryDto") >= 0){
			return "预扣恢复";
		}else if(message.indexOf("PreOccupyUsedRequest") >= 0){
			if(exception != null && exception.indexOf("CqcBusinessException") >= 0){
				return "未预扣调占用";
			}else{
				return "预扣占用";
			}
		}else if(message.indexOf("QueryQuotaAccountListRequest") >= 0){
			return "额度查询";
		}else if(message.indexOf("QueryQuotaAccountLogListRequest") >= 0){
			return "额度查询";
		}else if(message.indexOf("ReleaseQuotaRequest") >= 0){
			return  "额度释放";
		}else if(message.indexOf("QueryQuotaAccountRequest") >= 0){
			return "额度查询";
		}else {
			return message;
		}
	}

}
