package com.colson.renHangBaoSong;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrJzdzTransformer {
    private static final String SEPARATOR="|";

    public String transfer(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();

        sb.append(data.get("biddingNo")).append(SEPARATOR);//标的号
        sb.append(data.get("infoType")).append(SEPARATOR);//信息类别
        String stateName = data.get("stateName").toString();
        String cityName = data.get("cityName").toString();
        String zoneName = data.get("zoneName").toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stateName).append(cityName).append(zoneName);
        sb.append(stringBuilder.toString().equals("")?"暂缺":stringBuilder.toString()).append(SEPARATOR);//居住地址
        sb.append(data.get("livingZipCode").toString()).append(SEPARATOR);//居住地址邮政编码
        sb.append(data.get("residentialStatus").toString().equals("")?"9":data.get("residentialStatus").toString());//居住状况

        return sb.toString();
    }
    public static void main(String[] args) {

        GrJzdzTransformer transformer = new GrJzdzTransformer();

        String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\附表\\jzdz数据.sql";

        String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\附表\\jzdz数据_out.sql";
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
                data.put("stateName",split[2]);
                data.put("cityName",split[3]);
                data.put("zoneName",split[4]);
                data.put("livingZipCode",split[5]);
                data.put("residentialStatus",split[6]);
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
