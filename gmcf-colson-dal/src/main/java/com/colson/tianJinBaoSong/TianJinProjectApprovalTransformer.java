package com.colson.tianJinBaoSong;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TianJinProjectApprovalTransformer {
	private static final String SEPARATOR="|";

	public String transfer(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        sb.append(data.get("order_no")==null?"":data.get("order_no")).append(SEPARATOR);//平台订单号
        sb.append(data.get("loan_name")==null?"":data.get("loan_name")).append(SEPARATOR);//贷款名称
        sb.append(data.get("loan_desc")==null?"":data.get("loan_desc")).append(SEPARATOR);//贷款描述
        sb.append(data.get("loan_detail")==null?"":data.get("loan_detail")).append(SEPARATOR);//贷款详情
        sb.append(data.get("loan_amount")==null?"":data.get("loan_amount")).append(SEPARATOR);//贷款金额
        //贷款利率需要根据总期次和产品表里的风险等级匹配对应的利率
        String totalStage = (String) data.get("total_stage");
        String rankLevel = (String) data.get("rank_level");
        String productCode = (String) data.get("product_code");
        //公积金贷
        String[][] arrays1 = {{"1.18","1.28","1.48","1.62"},{"1.18","1.28","1.48","1.58"},{"1.18","1.28","1.48","1.55"},{"1.18","1.28","1.48","1.53"}};
        //取现消费、信用卡代偿
        String[][] arrays2 = {{"1.33","1.58","1.98","2.00"},{"1.08","1.28","1.48","1.75"},{"1.08","1.28","1.48","1.66"},{"1.08","1.28","1.48","1.62"},{"1.05","1.28","1.48","1.58"}};
        int row = -1;
        int column = -1;
        String loanApr = "";
        switch (rankLevel){
            case "A":
                column = 0;
                break;
            case "B":
                column = 1;
                break;
            case "C":
                column = 2;
                break;
            case "D":
                column = 3;
                break;
            default:
                column = -1;
        }
        if ("5561".equals(productCode)){
            //公积金贷
            switch (totalStage){
                case "12":
                    row = 0;
                    break;
                case "18":
                    row = 1;
                    break;
                case "24":
                    row = 2;
                    break;
                case "36":
                    row = 3;
                    break;
                default:
                    row = -1;
            }
            if (row>=0 && column>=0){
                loanApr = arrays1[row][column];
            }else {
                loanApr = "";
            }
            sb.append(loanApr).append(SEPARATOR);//贷款利率
        }else if ("5551".equals(productCode) || "5552".equals(productCode)){
            //取现消费、信用卡代偿
            switch (totalStage){
                case "3":
                    row = 0;
                    break;
                case "6":
                    row = 1;
                    break;
                case "9":
                    row = 2;
                    break;
                case "12":
                    row = 3;
                    break;
                case "18":
                    row = 4;
                    break;
                default:
                    row = -1;
            }
            if (row>=0 && column>=0){
                loanApr = arrays2[row][column];
            }else {
                loanApr = "";
            }
            sb.append(loanApr).append(SEPARATOR);//贷款利率
        }else if("6601".equals(productCode)){
            //门店分期
            sb.append("12").append(SEPARATOR);//贷款利率
        }else {
            sb.append("").append(SEPARATOR);//贷款利率
        }
        sb.append(data.get("period")==null?"":data.get("period")).append(SEPARATOR);//贷款期限
        sb.append(data.get("recover_type")==null?"":data.get("recover_type")).append(SEPARATOR);//还款方式
        sb.append(data.get("loan_time")==null?"":data.get("loan_time")).append(SEPARATOR);//满标日期
        sb.append(data.get("repay_time")==null?"":data.get("repay_time")).append(SEPARATOR);//贷款起息日期
        sb.append(data.get("expired_time")==null?"":data.get("expired_time")).append(SEPARATOR);//贷款到期日期
        sb.append(data.get("interests_type")==null?"":data.get("interests_type")).append(SEPARATOR);//计息方式
        sb.append(data.get("credit_purpose")==null?"":data.get("credit_purpose")).append(SEPARATOR);//贷款投向
        sb.append(data.get("credit_address")==null?"":data.get("credit_address")).append(SEPARATOR);//贷款投放地
        sb.append(data.get("credit_use")==null?"":data.get("credit_use")).append(SEPARATOR);//借款用途
        sb.append(data.get("lender_name")==null?"":data.get("lender_name")).append(SEPARATOR);//贷款人名称
        sb.append(data.get("lender_certificate")==null?"":data.get("lender_certificate")).append(SEPARATOR);//贷款人证件类型
        sb.append(data.get("lender_identification")==null?"":data.get("lender_identification")).append(SEPARATOR);//贷款人证件号码
        sb.append(data.get("lender_phone")==null?"":data.get("lender_phone")).append(SEPARATOR);//贷款人手机
        sb.append(data.get("lender_email")==null?"":data.get("lender_email")).append(SEPARATOR);//贷款人邮箱
        sb.append(data.get("surety_type")==null?"":data.get("surety_type")).append(SEPARATOR);//担保方式
        sb.append(data.get("surety_names")==null?"":data.get("surety_names")).append(SEPARATOR);//担保人明细
        sb.append(data.get("pawn_type")==null?"":data.get("pawn_type")).append(SEPARATOR);//抵押物类型
        sb.append(data.get("pawn_no")==null?"":data.get("pawn_no")).append(SEPARATOR);//抵押物编号
        sb.append(data.get("contribution_proportion")==null?"":data.get("contribution_proportion"));//出资占比
        return sb.toString();
	}



	public static void main(String[] args) {

		TianJinProjectApprovalTransformer transformer = new TianJinProjectApprovalTransformer();

		String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\天津\\问题\\天津立项测试数据.sql";

		String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\天津\\问题\\天津立项测试数据_out.sql";
//		String outPutFilePath2 = "D:\\国美金融\\项目\\COF备忘录\\人行\\补充遗漏数据\\数据库查出的jc_2_binddingNo_out.sql";

		File inFile = new File(sourceFilePath);

		File outFile = new File(outPutFilePath);

//		File outFile2 = new File(outPutFilePath2);

		try{
			if (!outFile.exists()){
				outFile.createNewFile();
			}
//			if (!outFile2.exists()){
//				outFile2.createNewFile();
//			}
		}catch (IOException io){
			io.printStackTrace();
		}


		try {
			FileOutputStream fos = new FileOutputStream(outFile,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//			FileOutputStream fos2 = new FileOutputStream(outFile2,true);
//			BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2));

			List<String> contentLine = Files.readAllLines(inFile.toPath(), Charset.forName("UTF-8"));

			List<String> list = new ArrayList<>();

			for (String s:contentLine) {
				String[] split = s.split("\\|");
				Map<String,Object> data = new HashMap<>();
                data.put("total_stage",split[0]);
                data.put("rank_level",split[1]);
                data.put("product_code",split[2]);
                data.put("order_no",split[3]);
                data.put("loan_name",split[4]);
                data.put("loan_desc",split[5]);
                data.put("loan_detail",split[6]);
                data.put("loan_amount",split[7]);
                data.put("loan_apr",split[8]);
                data.put("period",split[9]);
                data.put("recover_type",split[10]);
                data.put("loan_time",split[11]);
                data.put("repay_time",split[12]);
                data.put("expired_time",split[13]);
                data.put("interests_type",split[14]);
                data.put("credit_purpose",split[15]);
                data.put("credit_address",split[16]);
                data.put("credit_use",split[17]);
                data.put("lender_name",split[18]);
                data.put("lender_certificate",split[19]);
                data.put("lender_identification",split[20]);
                data.put("lender_phone",split[21]);
                data.put("lender_email",split[22]);
                data.put("surety_type",split[23]);
                data.put("surety_names",split[24]);
                data.put("pawn_type",split[25]);
                data.put("pawn_no",split[26]);
                data.put("contribution_proportion",split[27]);

                String transfer = transformer.transfer(data);

				bw.write(transfer);
				bw.newLine();
				bw.flush();
			}
//			System.out.println(list.size());
//			list.stream().forEach(e->{
//				try {
//					bw2.write(e);
//					bw2.newLine();
//					bw2.flush();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//			});
			bw.close();
//			bw2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
