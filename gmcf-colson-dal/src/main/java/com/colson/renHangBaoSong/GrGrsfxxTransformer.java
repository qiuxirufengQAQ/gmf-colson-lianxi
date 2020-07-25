package com.colson.renHangBaoSong;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.colson.renHangBaoSong.Utils.getDicConvert;

public class GrGrsfxxTransformer {
    private static final String SEPARATOR="|";

    public String transfer(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();

        sb.append(data.get("biddingNo")).append(SEPARATOR);//标的号
        sb.append(data.get("infoType").toString()).append(SEPARATOR);//信息类别
        sb.append(data.get("sex").toString().equals("")?"":getDicConvert("sex",data.get("sex").toString())).append(SEPARATOR);//性别
        sb.append(data.get("birthday")==null? "19010101":data.get("birthday")).append(SEPARATOR);//出生日期

        String maritalStatus = data.get("maritalStatus").toString();
        sb.append(maritalStatus.equals("")?"90":maritalStatus+"0").append(SEPARATOR);//婚姻状况
        sb.append((data.get("highestEducation").toString().equals("99")||data.get("highestEducation").toString().equals(""))?"99":getDicConvert("highestEducation",data.get("highestEducation").toString())).append(SEPARATOR);//最高学历（字典）
        sb.append(data.get("highestDegree").toString()).append(SEPARATOR);//最高学位
        sb.append(data.get("homePhone").toString()).append(SEPARATOR);//住宅电话
        sb.append(data.get("mobilePhone").toString()).append(SEPARATOR);//手机号码
        sb.append(data.get("workTelephone").toString()).append(SEPARATOR);//单位电话
        sb.append(data.get("email").toString()).append(SEPARATOR);//电子邮箱

        String stateName = data.get("stateName").toString();
        String zoneName = data.get("zoneName").toString();
        String mailAddress = stateName + zoneName;
        sb.append(mailAddress.equals("")?"暂缺":mailAddress).append(SEPARATOR);//通讯地址
        sb.append(data.get("mailZipCode").toString()).append(SEPARATOR);//通讯地址邮政编码

        String residenceAddress = data.get("residenceAddress").toString();
        StringBuilder registerAddress = new StringBuilder();
        registerAddress.append(stateName).append(zoneName).append(residenceAddress);
        sb.append(registerAddress.toString().equals("")?"暂缺":registerAddress.toString()).append(SEPARATOR);//户籍地址

        //当婚姻状态为未婚时，配偶的信息都为空
        if (maritalStatus.equals("1")){
            sb.append("").append(SEPARATOR);//配偶姓名
            sb.append("").append(SEPARATOR);//配偶证件类型
            sb.append("").append(SEPARATOR);//配偶证件号码
            sb.append("").append(SEPARATOR);//配偶工作单位
            sb.append("");//配偶联系电话
        }else {
            sb.append(data.get("spouseName").toString()).append(SEPARATOR);//配偶姓名
            sb.append(data.get("spouseIdentityType").toString()).append(SEPARATOR);//配偶证件类型
            sb.append(data.get("spouseIdentityNo").toString()).append(SEPARATOR);//配偶证件号码
            sb.append(data.get("spouseCompany").toString()).append(SEPARATOR);//配偶工作单位
            sb.append(data.get("spouseMobilePhone").toString());//配偶联系电话
        }
        return sb.toString();
    }
    public static void main(String[] args) {

        GrGrsfxxTransformer transformer = new GrGrsfxxTransformer();
        String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\附表\\grsfxx数据.sql";
        String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\附表\\grsfxx数据_out.sql";
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
                data.put("sex",split[2]);
                data.put("birthday",split[3]);
                data.put("maritalStatus",split[4]);
                data.put("highestEducation",split[5]);
                data.put("highestDegree",split[6]);
                data.put("homePhone",split[7]);
                data.put("mobilePhone",split[8]);
                data.put("workTelephone",split[9]);
                data.put("email",split[10]);
                data.put("stateName",split[11]);
                data.put("zoneName",split[12]);
                data.put("mailZipCode",split[13]);
                data.put("residenceAddress",split[14]);
                data.put("spouseName",split[15]);
                data.put("spouseIdentityType",split[16]);
                data.put("spouseIdentityNo",split[17]);
                data.put("spouseCompany",split[18]);
                data.put("spouseMobilePhone",split[19]);

                GrGrxxJcTransformer jc = new GrGrxxJcTransformer();
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
