package com.colson.fileReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataDeal{

	public static class ErrorMsg {

		public String message;

		public String exception;

		public String exceptiontype;

		public String traceId;

		public String method;

		public boolean need;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getException() {
			return exception;
		}

		public void setException(String exception) {
			this.exception = exception;
		}

		public String getExceptiontype() {
			return exceptiontype;
		}

		public void setExceptiontype(String exceptiontype) {
			this.exceptiontype = exceptiontype;
		}

		public String getTraceId() {
			return traceId;
		}

		public void setTraceId(String traceId) {
			this.traceId = traceId;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public boolean isNeed() {
			return need;
		}

		public void setNeed(boolean need) {
			this.need = need;
		}
	}

	public static class ReqMsg {

		public String className;

		public String method;

		public String reqJson;

		public String traceId;

		public String systemCode;

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public String getReqJson() {
			return reqJson;
		}

		public void setReqJson(String reqJson) {
			this.reqJson = reqJson;
		}

		public String getTraceId() {
			return traceId;
		}

		public void setTraceId(String traceId) {
			this.traceId = traceId;
		}

		public String getSystemCode() {
			return systemCode;
		}

		public void setSystemCode(String systemCode) {
			this.systemCode = systemCode;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			ReqMsg reqMsg = (ReqMsg) o;
			return Objects.equals(traceId, reqMsg.traceId);
		}

		@Override
		public int hashCode() {

			return Objects.hash(traceId);
		}
	}

	public static List<ErrorMsg> getHits(String file) throws IOException {

		File dataJson = new File("E:\\cqc_error\\"+file);

		List<String> contentLine = Files.readAllLines(dataJson.toPath(),Charset.forName("UTF-8"));
		String content = contentLine.get(0);

		JSONObject data = JSONObject.parseObject(content);

		data = data.getJSONArray("responses").getJSONObject(0).getJSONObject("hits");

		JSONArray hits = data.getJSONArray("hits");

		List<ErrorMsg> list = new ArrayList<>();
		for(int i=0;i<hits.size();i++){
			JSONObject source = hits.getJSONObject(i).getJSONObject("_source");
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.message = source.getString("message");
			errorMsg.exception = source.getString("exception");
			errorMsg.exceptiontype = source.getString("exceptiontype");
			errorMsg.exceptiontype = source.getString("exceptiontype");
			errorMsg.traceId = source.getString("traceId");
			errorMsg.method = getMethod(errorMsg.getMessage(),errorMsg.getException());
			errorMsg.need = !errorMsg.method.equals("未预扣调占用") && !errorMsg.method.equals("额度查询");
			list.add(errorMsg);

		}

		return list;
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
			throw new IllegalArgumentException("Error");
		}
	}

	@Test
	public void testMain() throws IOException, InterruptedException {

		File dataFile = new File("E:\\cqc_error\\error_data\\data.xlsx");

		String[] fileName = new String[]{"1.json","2.json","3.json","4.json","5.json","6.json"};

		List<ErrorMsg> request = new ArrayList<ErrorMsg>();
		for(String file : fileName){
			request.addAll(getHits(file));
		}

//		ExcelExporter<ErrorMsg> exporter = new ExcelExporter<>(ErrorMsg.class,dataFile);
//		exporter.toExcel(request);

		List<String> allTraceIds = request.stream().filter(ErrorMsg::isNeed).map(ErrorMsg::getTraceId).collect(Collectors.toSet())
				.stream().collect(Collectors.toList());
		List<List<String>> subTraceIds = new ArrayList<>();
		List<String> tempList = null;
		for (int i =0; i<allTraceIds.size(); i++){
			if(tempList == null){
				tempList = new ArrayList<>();
			}
			tempList.add(allTraceIds.get(i));
			if((i+1)%50 == 0 || i == allTraceIds.size() - 1){
				subTraceIds.add(tempList);
				tempList = null;
			}
		}

		int fileIndex = 1;
		for(List<String> list : subTraceIds){

			String jsonQury = "{\n" + "  \"query\": {\n" + "    \"bool\": {\n" + "      \"should\": [\n" + "        {\n"
					+ "          \"match_phrase\": {\n" + "            \"traceId\": \"gmcfcqc0aa201a943160632714\"\n"
					+ "          }\n" + "        },\n" + "        {\n" + "          \"match_phrase\": {\n"
					+ "            \"traceId\": \"gmcfcqc0aa201a943160632885\"\n" + "          }\n" + "        }\n"
					+ "      ],\n" + "      \"minimum_should_match\": 1\n" + "    }\n" + "  }\n" + "}";

			JSONArray query = new JSONArray();
			for(String traceId : list){
				query.add(JSON.parse("{\n" + "          \"match_phrase\": {\n"
									+ "            \"traceId\": \""+traceId+"\"\n" + "          }\n" + "        }"));
			}
			JSONObject queryObj = JSON.parseObject(jsonQury);
			queryObj.getJSONObject("query").getJSONObject("bool").put("should",query);
			File traceQueryFile = new File("E:\\cqc_error\\error_trace\\"+fileIndex+".json");

			FileWriter writer = new FileWriter(traceQueryFile);
			writer.write(queryObj.toJSONString());
			writer.close();

			fileIndex ++ ;
		}

		Set<ReqMsg> reqMsgList = new HashSet<>();

		//数据清洗
		for(int i = 1; i<fileIndex;i++){
			File mvcReq = new File("E:\\cqc_error\\error_req\\"+i+".mvc.json");
			File rpcReq = new File("E:\\cqc_error\\error_req\\"+i+".rpc.json");
//			if(mvcReq.exists() && rpcReq.exists()){
				List<ReqMsg> list = this.getReqMsg(mvcReq);
				reqMsgList.addAll(list);
				list = this.getReqMsg(rpcReq);
				reqMsgList.addAll(list);
//			}
		}

		dataFile = new File("E:\\cqc_error\\error_data\\req_data.xlsx");
//		ExcelExporter<ReqMsg> exportReq = new ExcelExporter<>(ReqMsg.class,dataFile);
//		exportReq.toExcel(reqMsgList.stream().collect(Collectors.toList()));

		System.out.println("请求不在allTraceIds中");
		reqMsgList.stream().forEach(i -> {
			if(!allTraceIds.contains(i.getTraceId())){
				System.out.println(i.getTraceId());
			}
		});
		List<String> repTraceIds = reqMsgList.stream().map(ReqMsg::getTraceId).collect(Collectors.toList());

		System.out.println("traceId不在请求中");
		allTraceIds.stream().forEach(i -> {
			if(!repTraceIds.contains(i)){
				System.out.println(i);
			}
		});


		return;


//		File dataJson = new File("C:\\Users\\Administrator\\Desktop\\data.json");
//
//		List<String> contentLine = Files.readAllLines(dataJson.toPath(),Charset.forName("UTF-8"));
//		String content = contentLine.get(0);
//
//
//		JSONObject data = JSONObject.parseObject(content);
//
//		data = data.getJSONArray("responses").getJSONObject(0).getJSONObject("hits");
//
//		JSONArray hits = data.getJSONArray("hits");
//
//		String jsonQury = "{\n" + "  \"query\": {\n" + "    \"bool\": {\n" + "      \"should\": [\n" + "        {\n"
//				+ "          \"match_phrase\": {\n" + "            \"traceId\": \"gmcfcqc0aa201a943160632714\"\n"
//				+ "          }\n" + "        },\n" + "        {\n" + "          \"match_phrase\": {\n"
//				+ "            \"traceId\": \"gmcfcqc0aa201a943160632885\"\n" + "          }\n" + "        }\n"
//				+ "      ],\n" + "      \"minimum_should_match\": 1\n" + "    }\n" + "  }\n" + "}";
//
//		JSONArray query = new JSONArray();
//
//		for(int i=200;i<hits.size();i++){
//			JSONObject source = hits.getJSONObject(i).getJSONObject("_source");
//			query.add(JSON.parse("{\n" + "          \"match_phrase\": {\n"
//					+ "            \"traceId\": \""+source.getString("traceId")+"\"\n" + "          }\n" + "        }"));
//		}
//
//		JSONObject queryObj = JSON.parseObject(jsonQury);
//		queryObj.getJSONObject("query").getJSONObject("bool").put("should",query);
//		System.out.println(queryObj.toJSONString());




 	}

	public static class ReqMsgDetail extends ReqMsg {

//		@ExcelField("处理方法")
//		private String dealMethod;
//
//		@ExcelField("处理结果")
//		private String dealResult;

//		public String getDealMethod() {
//			return dealMethod;
//		}
//
//		public void setDealMethod(String dealMethod) {
//			this.dealMethod = dealMethod;
//		}
//
//		public String getDealResult() {
//			return dealResult;
//		}
//
//		public void setDealResult(String dealResult) {
//			this.dealResult = dealResult;
//		}
	}

 	@Test
 	public void postTestReq() throws Exception {

//		File dataFile = new File("E:\\cqc_error\\error_deal\\req_data.xlsx");
//
//		ExcelImporter<ReqMsgDetail> importer = new ExcelImporter<>(ReqMsgDetail.class,dataFile);
//		ExcelImportResult<ReqMsgDetail> result = importer.toList();
//
//		Assert.isTrue(result.isAllSuccess(),"未全部读取");
//
//		List<ReqMsgDetail> dataList = result.getResults();
//
//		for(ReqMsgDetail msg : dataList){
//			if(msg.dealMethod.equals("CQC修数据")){
//				String dealResult = null;
//				try {
//					if (msg.method.equals("额度释放")) {
//						dealResult = postRelease(msg.getReqJson());
//					} else if (msg.method.equals("预扣恢复")) {
//						dealResult = postPreOccupyRecovery(msg.getReqJson());
//					} else if (msg.method.equals("预扣占用")) {
//						dealResult = postPreOccupyUsed(msg.getReqJson());
//					} else {
//						dealResult = "处理方法未定义";
//					}
//					Thread.sleep(10);
//				}catch (Throwable ex){
//					ex.printStackTrace();
//					dealResult = ex.getMessage();
//				}
//				msg.setDealResult(dealResult);
//			}
//		}
//
//		File dataResultFile = new File("E:\\cqc_error\\error_deal\\req_data_result.xlsx");
//		ExcelExporter<ReqMsgDetail> exporter = new ExcelExporter<ReqMsgDetail>(ReqMsgDetail.class,dataResultFile);
//		exporter.toExcel(dataList);

	}

 	public List<ReqMsg> getReqMsg(File req) throws IOException {
		String reqJson = StringUtils.join(Files.readAllLines(req.toPath(),Charset.forName("UTF-8")),"");JSONArray reqList = getHitsReq(reqJson);
		List<ReqMsg>  reqMsgList = new ArrayList<>();
		for(int j=0; j<reqList.size();j++){
			JSONObject source = reqList.getJSONObject(j).getJSONObject("_source");
			if(source.getString("module").equals("gmcf-cqc")){
				String msg = source.getString("message");
				ReqMsg reqMsg = new ReqMsg();
				reqMsg.className = getRequestClassName(msg);
				reqMsg.method = getMethod(msg,null);
				reqMsg.setTraceId(source.getString("traceId"));
				reqMsg.setSystemCode(getSystemCode(msg));
				reqMsg.reqJson = getObjectJson(msg);
				reqMsgList.add(reqMsg);
			}

		}
		return reqMsgList;
	}

	public String getRequestClassName(String msg){
		Pattern pattern = Pattern.compile("(\\w+)(Request|Dto)");
		Matcher matcher = pattern.matcher(msg);
		if(matcher.find()){
			return matcher.group();
		}
		throw new IllegalArgumentException("无Request");
	}

	public String getSystemCode(String msg){
		Pattern pattern = Pattern.compile("systemCode=(\\w|-|\\.|<|>)+");
		Matcher matcher = pattern.matcher(msg);
		if(matcher.find()){
			return matcher.group().split("=")[1];
		}

		throw new IllegalArgumentException("无systemCode");
	}

	@Test
	public void testUnfrozen() throws IOException {

//		String req1 = "{\n" + "    \"frozenTraceNo\": \"LCN10500957577277644801\", \n" + "    \"memberNo\": \"30d0cc8d-63b0-487c-99e6-6560280de0ce\", \n"
//				+ "    \"operator\": \"数据订正\", \n" + "    \"productCode\": \"9201\", \n"
//				+ "    \"reasonCode\": \"OVERDUE_M2\", \n" + "    \"reasonDesc\": \"逾期M1-M2\", \n"
//				+ "    \"systemCode\": \"OrderSystem\", \n" + "    \"traceNo\": \"LCN10500957577277644801\"\n" + "}";
//
//		String req2 = "{\n" + "    \"frozenTraceNo\": \"LCN10501182181164974091\", \n" + "    \"memberNo\": \"30d0cc8d-63b0-487c-99e6-6560280de0ce\", \n"
//				+ "    \"operator\": \"数据订正\", \n" + "    \"productCode\": \"9201\", \n"
//				+ "    \"reasonCode\": \"OVERDUE_M2\", \n" + "    \"reasonDesc\": \"逾期M1-M2\", \n"
//				+ "    \"systemCode\": \"OrderSystem\", \n" + "    \"traceNo\": \"LCN10501182181164974091\"\n" + "}";

//		System.out.println(postUnfrozen(req1));
//		System.out.println(postUnfrozen(req2));

		String data = "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=65f22362-836a-4f98-9020-7555fcca95fb,idCardNo=<null>,productCode=9201,traceNo=1118502702989578241,frozenTraceNo=LGMMJ-smallstage-app-201811141108228811495381,operator=<null>,reasonCode=LGMMJ-smallstage-app-201811141108228811495381,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=0af0afada5404d19b77da44bd4eb7826,idCardNo=<null>,productCode=9201,traceNo=1118501919535398912,frozenTraceNo=LGMMJ-smallstage-app-201812161118227541198701,operator=<null>,reasonCode=LGMMJ-smallstage-app-201812161118227541198701,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=b3c2d89a982d4492862730951b783401,idCardNo=<null>,productCode=9201,traceNo=1118501921003540481,frozenTraceNo=LGMMJ-smallstage-app-201901161331508753900051,operator=<null>,reasonCode=LGMMJ-smallstage-app-201901161331508753900051,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=5888473f-dcec-4167-b594-ebfddeb1005c,idCardNo=<null>,productCode=9201,traceNo=1118501536297783296,frozenTraceNo=LGMMJ-smallstage-app-201902162050062448883301,operator=<null>,reasonCode=LGMMJ-smallstage-app-201902162050062448883301,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=dc4be4c5968b43f7a7e59ffad7199f36,idCardNo=<null>,productCode=9201,traceNo=1118499458808680449,frozenTraceNo=LGMMJ-smallstage-app-201811151306572191910361,operator=<null>,reasonCode=LGMMJ-smallstage-app-201811151306572191910361,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=609c6071863f4268874903337569b6eb,idCardNo=<null>,productCode=9201,traceNo=1118499394971238401,frozenTraceNo=LGMMJ-smallstage-app-201901162019171968871251,operator=<null>,reasonCode=LGMMJ-smallstage-app-201901162019171968871251,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=c471f46a5eb7454ca7daf7b68e169c42,idCardNo=<null>,productCode=9201,traceNo=1118497722136137729,frozenTraceNo=LGMMJ-smallstage-app-201901161719355852873741,operator=<null>,reasonCode=LGMMJ-smallstage-app-201901161719355852873741,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=21be22821c724a8c958df98e3fc9622b,idCardNo=<null>,productCode=9201,traceNo=1118497406292328448,frozenTraceNo=LGMMJ-smallstage-app-201809160939383149877351,operator=<null>,reasonCode=LGMMJ-smallstage-app-201809160939383149877351,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=0dc00d1c17074e85a2b57d91a9c196ee,idCardNo=<null>,productCode=9201,traceNo=1118496759157358592,frozenTraceNo=LGMMJ-smallstage-app-201903152204244198856891,operator=<null>,reasonCode=LGMMJ-smallstage-app-201903152204244198856891,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=d4bd20aa4f244bffa7f5af26855cdaeb,idCardNo=<null>,productCode=9201,traceNo=1118496685421629441,frozenTraceNo=LGMMJ-smallstage-app-201811152326597516241831,operator=<null>,reasonCode=LGMMJ-smallstage-app-201811152326597516241831,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=7405f5f08fdf46008cad158d4183633e,idCardNo=<null>,productCode=9201,traceNo=1118494389086191617,frozenTraceNo=LGMMJ-smallstage-app-201812162248124274617201,operator=<null>,reasonCode=LGMMJ-smallstage-app-201812162248124274617201,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=7b2ec17d1a2c48a49a2dbe975245646d,idCardNo=<null>,productCode=9201,traceNo=1118494379351212032,frozenTraceNo=LGMMJ-smallstage-app-201812161906193087100821,operator=<null>,reasonCode=LGMMJ-smallstage-app-201812161906193087100821,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=2bd7522df6e44f3abe0ebbd15784b378,idCardNo=<null>,productCode=9201,traceNo=1118494084869263360,frozenTraceNo=LGMMJ-smallstage-app-201902160854254127695171,operator=<null>,reasonCode=LGMMJ-smallstage-app-201902160854254127695171,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=2ff5e06d87af4e58afdc9489a54d672a,idCardNo=<null>,productCode=9201,traceNo=1118493570085556225,frozenTraceNo=LGMMJ-smallstage-app-201811161529425731781311,operator=<null>,reasonCode=LGMMJ-smallstage-app-201811161529425731781311,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=3f90b7e7-55cf-4330-8682-1ec8f0d130dc,idCardNo=<null>,productCode=9201,traceNo=1118416866595831809,frozenTraceNo=LGMMJ-smallstage-app-201811151326084743056641,operator=<null>,reasonCode=LGMMJ-smallstage-app-201811151326084743056641,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=4414ff5a18d44939a8893cfb6c954ad2,idCardNo=<null>,productCode=9201,traceNo=1118312909647253504,frozenTraceNo=LGMMJ-smallstage-app-201903160730242982681111,operator=<null>,reasonCode=LGMMJ-smallstage-app-201903160730242982681111,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=9ff703f84849447f954ce2f049069f4d,idCardNo=<null>,productCode=9201,traceNo=1118103536656322561,frozenTraceNo=LGMMJ-smallstage-app-201903141412047042845031,operator=<null>,reasonCode=LGMMJ-smallstage-app-201903141412047042845031,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=5f94e255bd064607ba419c12e2c79d6a,idCardNo=<null>,productCode=9201,traceNo=1118014370517876737,frozenTraceNo=LGMMJ-smallstage-app-201903141324393453350501,operator=<null>,reasonCode=LGMMJ-smallstage-app-201903141324393453350501,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=76d5293aee804689bd6842ed866beecf,idCardNo=<null>,productCode=9201,traceNo=1118011513332961281,frozenTraceNo=LGMMJ-smallstage-app-201903151819125097880141,operator=<null>,reasonCode=LGMMJ-smallstage-app-201903151819125097880141,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=d7419f0aa9454b899bb675a4f41a3900,idCardNo=<null>,productCode=9201,traceNo=1117990942306078720,frozenTraceNo=LGMMJ-smallstage-app-201902141138522313438251,operator=<null>,reasonCode=LGMMJ-smallstage-app-201902141138522313438251,reasonDesc=OVERDUE_M2,remark=<null>]\n"
				+ "MVC_REQ com.gomefinance.consumerfinance.cqc.controller.QuotaController.unfrozenQuotaAccount(com.gomefinance.consumerfinance.cqc.facade.dto.UnfrozenQuotaDto) REQ UnfrozenQuotaDto[systemCode=OrderSystem,memberNo=2ccd161e-991c-4876-bf17-c31d9d55bb1b,idCardNo=<null>,productCode=9201,traceNo=1118498001451483136,frozenTraceNo=LGMMJ-smallstage-app-201812150836045351733721,operator=<null>,reasonCode=LGMMJ-smallstage-app-201812150836045351733721,reasonDesc=OVERDUE_M2,remark=<null>]";

		List<String> dataList = Arrays.asList(data.split("\n"));

		List<String> reqList = dataList.stream().map(this::getObjectJson).collect(Collectors.toList());

		reqList.forEach(req -> {
			try {
				System.out.println(postUnfrozen(req));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	@Test
	public void testRelease(){
		String data = "";

		List<String> dataList = Arrays.asList(data.split("\n"));

		List<String> reqList = dataList.stream().map(this::getObjectJson).collect(Collectors.toList());

		reqList = reqList.stream().map(str -> {
			str = str.replace("custNo","memberNo")
					.replace("frontendJournalId","traceNo")
					.replace("releaseAmount","quota")
					.replace("frontendSystemId","systemCode")
					.replace("businessType","reasonCode")
					.replace("productId","productCode");
			return str;
		}).collect(Collectors.toList());

		reqList.forEach(req -> {
			try {
				System.out.println(postRelease(req));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

//	public String getReleaseJson(String msg){
//		Pattern pattern = Pattern.compile("\\w+=\\w+");
//		JSONObject jsonObject = new JSONObject();
//		JSONArray releaseQuotaList = new JSONArray();
//		JSONObject dto = new JSONObject();
//		Matcher matcher = pattern.matcher(msg);
//		while (matcher.find()){
//			String[] strs = matcher.group().split("=");
//			dto.put(strs[0].trim(),"<null>".equals(strs[1].trim())?null:strs[1].trim());
//		}
//		releaseQuotaList.add(dto);
//		jsonObject.put("releaseQuotaList",releaseQuotaList);
//		return jsonObject.toJSONString();
//	}

	public String getObjectJson(String msg){
		Pattern pattern = Pattern.compile("\\w+=(\\w|-|\\.|<|>)+");
		JSONObject dto = new JSONObject();
		Matcher matcher = pattern.matcher(msg);
		while (matcher.find()){
			String[] strs = matcher.group().split("=");
			dto.put(strs[0].trim(),"<null>".equals(strs[1].trim())?null:strs[1].trim());
		}
		return dto.toJSONString();
	}

 	public JSONArray getHitsReq(String content){
		JSONObject data = JSONObject.parseObject(content);
		data = data.getJSONArray("responses").getJSONObject(0).getJSONObject("hits");
		JSONArray hits = data.getJSONArray("hits");
		return hits;
	}

 	public static String getObject(String requestStr){
		requestStr = requestStr.substring(requestStr.indexOf("systemCode"),requestStr.length()-3);
		String[] listStr = requestStr.split(",");
		JSONObject json = new JSONObject();
		for(String str : listStr){
			String[] strs = str.split("=");
			json.put(strs[0].trim(),"<null>".equals(strs[1].trim())?null:strs[1].trim());
		}
		return json.toJSONString();
	}

	private static final String baseUrl = "http://cfcqc.gomemyf.com";
//	private static final String baseUrl = "http://";

	public static String postRelease(String request) throws IOException {

//		String req = "{\n" + "\t\"releaseQuotaList\": [\n" + request + "    ]\n" + "}";
//
//		String uri = baseUrl + "/api/quota/release-quota.json";
//
//		String resp = Jsoup.connect(uri).ignoreContentType(true).header("Content-Type","application/json").requestBody(req)
//				.post().body().text();
//
//		JSONObject json = JSON.parseObject(resp);
//		if(json.getIntValue("respCode") == 1000){
//			return "请求成功";
//		}else{
//			return resp;
//		}
		return "";

	}

	public static String postPreOccupyRecovery(String request) throws IOException {

//		String req = request;
//
//		String uri = baseUrl + "/api/quota/pre-occupy-recovery.json";
//
//		String resp = Jsoup.connect(uri).ignoreContentType(true).header("Content-Type","application/json").requestBody(req)
//				.post().body().text();
//
//		JSONObject json = JSON.parseObject(resp);
//		if(json.getIntValue("respCode") == 1000){
//			return "请求成功";
//		}else{
//			return resp;
//		}
		return "";

	}

	public static String postPreOccupyUsed(String request) throws IOException {

//		String req = request;
//
//		String uri = baseUrl + "/api/quota/pre-occupy-used.json";
//
//		String resp = Jsoup.connect(uri).ignoreContentType(true).header("Content-Type","application/json").requestBody(req)
//				.post().body().text();
//
//		JSONObject json = JSON.parseObject(resp);
//		if(json.getIntValue("respCode") == 1000){
//			return "请求成功";
//		}else{
//			return resp;
//		}
		return "";

	}

	public static String postUnfrozen(String request) throws IOException {

//		String req = request;
//
//		String uri = baseUrl + "/api/quota/unfrozen-quota-account.json";
//
//		String resp = Jsoup.connect(uri).ignoreContentType(true).header("Content-Type","application/json").requestBody(req)
//				.post().body().text();
//
//		JSONObject json = JSON.parseObject(resp);
//		if(json.getIntValue("respCode") == 1000){
//			return "请求成功";
//		}else{
//			return resp;
//		}
		return "";

	}
}
