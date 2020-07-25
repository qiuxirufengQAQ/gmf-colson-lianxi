package com.colson.renHangBaoSong;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrTsjyTransformer {
    private static final String SEPARATOR="|";

    public String transfer(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();

        sb.append(data.get("biddingNo")).append(SEPARATOR);//标的号
        sb.append(data.get("infoType")).append(SEPARATOR);//信息类别
        sb.append(data.get("transType")==null?"9":data.get("transType")).append(SEPARATOR);//特殊交易类型（须字典）
        sb.append(data.get("transTime")==null?"":data.get("transTime")).append(SEPARATOR);//发生日期
        sb.append(data.get("changeStage")==null?"0":data.get("changeStage")).append(SEPARATOR);//变更月数（有期限调整）
        sb.append(data.get("changeAmount")==null?"0":data.get("changeAmount")).append(SEPARATOR);//发生金额
        sb.append(data.get("transDetail")==null?"":data.get("transDetail"));//明细信息

        return sb.toString();
    }
    public static void main(String[] args) {

        GrTsjyTransformer transformer = new GrTsjyTransformer();

        String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\正常还款的数据.sql";

        String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\正常还款的数据_out.sql";

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

            List<String> list = new ArrayList<>();

            for (String s:contentLine) {
                String[] split = s.split(",");
                Map<String,Object> data = new HashMap<>();
                data.put("biddingNo",split[0]);
                data.put("infoType",split[1]);
                data.put("transType",split[2]);
                data.put("transTime",split[3]);
                data.put("changeStage",split[4]);
                data.put("changeAmount",split[5]);
                data.put("transDetail",split[6]);

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
