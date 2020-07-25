package com.colson.renHangBaoSong;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.colson.renHangBaoSong.Utils.getDicConvert;

public class GrZyxxTransformer {
    private static final String SEPARATOR="|";

    public String transfer(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();

        sb.append(data.get("biddingNo")).append(SEPARATOR);//个人职业信息表（GR_ZYXX）
        sb.append(data.get("occupation").toString().equals("")?"":getDicConvert("occupation",data.get("occupation").toString())).append(SEPARATOR);//职业（字典）
        sb.append(data.get("company").toString().equals("")?"暂缺":data.get("company")).append(SEPARATOR);//单位名称
        sb.append(data.get("companyType").toString().equals("")?"Z":data.get("companyType")).append(SEPARATOR);//单位所属行业（字典）
        sb.append(data.get("workAddress").toString().equals("")?"":data.get("workAddress")).append(SEPARATOR);//单位地址
        sb.append(data.get("workZipCode").toString()).append(SEPARATOR);//单位地址邮政编码
        sb.append(data.get("workBegin").toString()).append(SEPARATOR);//本单位工作起始年份
        sb.append(data.get("duties").toString()).append(SEPARATOR);//职务
        sb.append(data.get("title").toString()).append(SEPARATOR);//职称
        sb.append(data.get("annualIncome").toString()).append(SEPARATOR);//年收入
        sb.append(data.get("bankAccount").toString()).append(SEPARATOR);//工资账号
        sb.append(data.get("bank").toString());//工资账户开户银行

        return sb.toString();
    }
    public static void main(String[] args) {

        GrZyxxTransformer transformer = new GrZyxxTransformer();

        String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\附表\\zyxx数据.sql";

        String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\附表\\zyxx数据_out.sql";

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
                data.put("occupation",split[1]);
                data.put("company",split[2]);
                data.put("companyType",split[3]);
                data.put("workAddress",split[4]);
                data.put("workZipCode",split[5]);
                data.put("workBegin",split[6]);
                data.put("duties",split[7]);
                data.put("title",split[8]);
                data.put("annualIncome",split[9]);
                data.put("bankAccount",split[10]);
                data.put("bank",split[11]);
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
