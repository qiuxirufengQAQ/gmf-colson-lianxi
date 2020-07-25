package com.colson.renHangBaoSong;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GrGrxxJcTransformer {
	private static final String SEPARATOR="|";

	public String transfer(Map<String, Object> data) {

        StringBuilder sb = new StringBuilder();

        sb.append(data.get("orgCode")).append(SEPARATOR);//金融机构代码（业务发生机构代码）
        sb.append(data.get("businessType")).append(SEPARATOR);//业务种类
        sb.append(data.get("businessTypeDetail")).append(SEPARATOR);//业务种类细分
        sb.append(data.get("biddingNo")).append(SEPARATOR);//业务号
        sb.append(data.get("location")).append(SEPARATOR);//发生地点
        sb.append(data.get("loanDay")==null?"":this.getDateTime(data.get("loanDay").toString())).append(SEPARATOR);//开户日期
        sb.append(data.get("dueDate")==null?"":this.getDateTime(data.get("dueDate").toString())).append(SEPARATOR);//到期日期
        sb.append(data.get("currency")==null?"":data.get("currency")).append(SEPARATOR);//币种
        sb.append(data.get("creditLine")==null?"":(int)Math.ceil(Double.parseDouble(data.get("creditLine").toString()))).append(SEPARATOR);//授信额度
        sb.append(data.get("SharedCreditLine")==null?"":(int)Math.ceil(Double.parseDouble(data.get("SharedCreditLine").toString()))).append(SEPARATOR);//共享授信额度
        sb.append(data.get("maxLiability")==null?"":(int)Math.ceil(Double.parseDouble(data.get("maxLiability").toString()))).append(SEPARATOR);//最大负债额
        sb.append(data.get("guaranteeType")).append(SEPARATOR);//担保方式
        sb.append(data.get("repayFrequency")).append(SEPARATOR);//还款频率
        sb.append(data.get("repayTotalStage")==null?"":data.get("repayTotalStage")).append(SEPARATOR);//还款月数
        sb.append(data.get("repayRemainStage")==null?"":data.get("repayRemainStage")).append(SEPARATOR);//剩余还款月数
        sb.append(data.get("repayDate")==null?"":this.getDateTime(data.get("repayDate").toString())).append(SEPARATOR);//结算/应还款日期
        sb.append(data.get("lastRepayDate")==null?"":this.getDateTime(data.get("lastRepayDate").toString())).append(SEPARATOR);//最近一次实际还款日期
        sb.append((int)Math.ceil(Double.parseDouble(data.get("mustRepayTotalAmount").toString()))).append(SEPARATOR);//本月应还款金额
        sb.append((int)Math.ceil(Double.parseDouble(data.get("actualRepayTotalAmount").toString()))).append(SEPARATOR);//本月实际还款金额
        sb.append((int)Math.ceil(Double.parseDouble(data.get("balance").toString()))).append(SEPARATOR);//余额
        sb.append(data.get("currentOverdueStage")).append(SEPARATOR);//当前逾期期数
        sb.append((int)Math.ceil(Double.parseDouble(data.get("currentOverdueAmount").toString()))).append(SEPARATOR);//当前逾期总额
        sb.append((int)Math.ceil(Double.parseDouble(data.get("loanPrincipal31").toString()))).append(SEPARATOR);//逾期31-60天未归还贷款本金
        sb.append((int)Math.ceil(Double.parseDouble(data.get("loanPrincipal61").toString()))).append(SEPARATOR);//逾期61-90天未归还贷款本金
        sb.append((int)Math.ceil(Double.parseDouble(data.get("loanPrincipal91").toString()))).append(SEPARATOR);//逾期91-180天未归还贷款本金
        sb.append((int)Math.ceil(Double.parseDouble(data.get("loanPrincipal180").toString()))).append(SEPARATOR);//逾期180天以上未归还贷款本金
        sb.append(data.get("totalOverdueStage")).append(SEPARATOR);//累计逾期期数
        sb.append(data.get("maxOverdueStage")).append(SEPARATOR);//最高逾期期数

        String riskStatus = data.get("riskStatus").toString();
        if (riskStatus == null || "".equals(riskStatus)){
            Integer debitOverdueCount = Integer.valueOf(data.get("debitOverdueCount").toString());
            Integer mustPayDay = Integer.valueOf(data.get("mustPayDay").toString());

            //若应还款日在1日至4日，则逾期债务数减去1
            if (mustPayDay <= 4){
                debitOverdueCount--;
            }

            //若个数为1，则为1-正常，个数为2或3则为2-关注，个数为4、5、6则为3-次级，个数为7、8、9、10、11、12则为4-可疑，个数大于13，则为5-损失，其他情况为9、未知
            if (debitOverdueCount <= 0){
                riskStatus = "1";
            }else if (debitOverdueCount >= 13){
                riskStatus = "5";
            }else {
                switch (debitOverdueCount){
                    case 1:
                        riskStatus = "1";
                        break;
                    case 2:
                    case 3:
                        riskStatus = "2";
                        break;
                    case 4:
                    case 5:
                    case 6:
                        riskStatus = "3";
                        break;
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        riskStatus = "4";
                        break;
                    default:
                        riskStatus = "9";
                }
            }
            sb.append(riskStatus).append(SEPARATOR);//五级分类状态
        }else {
            sb.append(riskStatus).append(SEPARATOR);//五级分类状态
        }
        sb.append(data.get("accountRepayStatus")==null?"":data.get("accountRepayStatus")).append(SEPARATOR);//账户状态
        String twoYearAccountRepayStatus1  = data.get("twoYearAccountRepayStatus").toString();
        StringBuilder twoYearAccountRepayStatus = new StringBuilder();
        //在结清中（当成交日期为上上月，结清日期为上月，则24个月状态为//////////////////////*C）的数据生成
        if ("//////////////////////*C".equals(twoYearAccountRepayStatus1)){
            twoYearAccountRepayStatus.append("//////////////////////*C");
        }else {
            if(null != data.get("twoYearAccountRepayStatus")){

                int num = 0;
                if (null != data.get("currentStage") && StringUtils.isNotEmpty(data.get("currentStage").toString())){
                    num = Integer.parseInt(data.get("currentStage").toString());
                }
                if(num<twoYearAccountRepayStatus1.length() && num>=0){
                    twoYearAccountRepayStatus.append(twoYearAccountRepayStatus1.substring(0, num));
                }
                if(StringUtils.isNotBlank(twoYearAccountRepayStatus.toString())&& twoYearAccountRepayStatus.toString().length()<24){
                    int num1 = 24-twoYearAccountRepayStatus.toString().length();
                    for (int i=0;i<num1;i++){
                        twoYearAccountRepayStatus.insert(0,"/");
                    }
                }
            }
        }

        sb.append(data.get("twoYearAccountRepayStatus")==null?"":twoYearAccountRepayStatus.toString()).append(SEPARATOR);//24个月（账户）还款状态
        sb.append(data.get("overdraftBalance")).append(SEPARATOR);//透支180天以上未付余额
        sb.append(data.get("loanAccountType")==null?"":data.get("loanAccountType")).append(SEPARATOR);//账户拥有者信息提示
        sb.append(data.get("userName")==null?"":data.get("userName")).append(SEPARATOR);//姓名
        sb.append(data.get("identityType")).append(SEPARATOR);//证件类型
        sb.append(data.get("identityNo")==null?"":data.get("identityNo")).append(SEPARATOR);//证件号码
        sb.append(data.get("reservedFields")==null?"":data.get("reservedFields"));//预留字段
        return sb.toString();
	}

	private String getDateTime(String sourceDateTime){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

		try {
			Date parse = sdf1.parse(sourceDateTime);
			return sdf2.format(parse);
		} catch (ParseException e) {
			return "";
		}
	}


	public static void main(String[] args) {

		GrGrxxJcTransformer transformer = new GrGrxxJcTransformer();

		String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\202001\\正常还款的数据.sql";

		String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\202001\\正常还款的数据_out.sql";
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
				Map<String,Object> data = new HashMap<>();
				data.put("orgCode",split[0]);
				data.put("businessType",split[1]);
				data.put("businessTypeDetail",split[2]);
				data.put("biddingNo",split[3]);
				data.put("location",split[4]);
				data.put("loanDay",split[5]);
				data.put("dueDate",split[6]);
				data.put("currency",split[7]);
				data.put("creditLine",split[8]);
				data.put("SharedCreditLine",split[9]);
				data.put("maxLiability",split[10]);
				data.put("guaranteeType",split[11]);
				data.put("repayFrequency",split[12]);
				data.put("repayTotalStage",split[13]);
				data.put("repayRemainStage",split[14]);
				data.put("repayDate",split[15]);
				data.put("lastRepayDate",split[16]);
				data.put("mustRepayTotalAmount",split[17]);
				data.put("actualRepayTotalAmount",split[18]);
				data.put("balance",split[19]);
				data.put("currentOverdueStage",split[20]);
				data.put("currentOverdueAmount",split[21]);
				data.put("loanPrincipal31",split[22]);
				data.put("loanPrincipal61",split[23]);
				data.put("loanPrincipal91",split[24]);
				data.put("loanPrincipal180",split[25]);
				data.put("totalOverdueStage",split[26]);
				data.put("maxOverdueStage",split[27]);
				data.put("riskStatus",split[28]);
				data.put("mustPayDay",split[29]);
				data.put("debitOverdueCount",split[30]);
				data.put("accountRepayStatus",split[31]);
				data.put("twoYearAccountRepayStatus",split[32]);
				data.put("overdraftBalance",split[33]);
				data.put("loanAccountType",split[34]);
				data.put("userName",split[35]);
				data.put("identityType",split[36]);
				data.put("identityNo",split[37]);
				data.put("reservedFields",split[38]);
				data.put("currentStage",split[39]);

				String transfer = transformer.transfer(data);

				bw.write(transfer);
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
