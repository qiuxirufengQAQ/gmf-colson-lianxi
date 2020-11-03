package com.colson.fileReader;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TranslateTextAndExcel {
    public static void main(String[] args) throws Exception {
        String excelPath="F:\\IdeaTestData\\testData\\中>>朝关系.xls";
        String sheetName="中朝关系";
        String [] headNames={"word","weight","pos","freq"};
        String txtPath="F:\\IdeaTestData\\testData\\Txt2>>Excel_txt.txt";
        txt_To_excel(excelPath,sheetName,headNames,txtPath);
    }

    public static void txt_To_excel(String excelPath,String sheetName,String [] headNames,String txtPath){
        //Excel
        HSSFWorkbook wb = new HSSFWorkbook();
        //sheet表
        HSSFSheet sheet = wb.createSheet(sheetName);
        //标题行
        HSSFRow head=sheet.createRow(0);
        //定义样式
        HSSFCellStyle style = wb.createCellStyle();
        //字体样式
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.BLUE.index);//字体颜色
        style.setFont(font);
        for (int i =0;i<=headNames.length-1;i++){
            //标题列
            HSSFCell headCell = head.createCell(i);
            //标题内容
            headCell.setCellValue(headNames[i]);
            //应用样式
            headCell.setCellStyle(style);
        }
        //行内容
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(txtPath)));
            Integer lineNum=1;
            String line="";
            while ((line = bufferedReader.readLine()) != null) {
                //创建行
                HSSFRow row = sheet.createRow(lineNum);
                String[] cells = line.trim().split("\\|");
                for (int i =0;i<=cells.length-1;i++){
                    HSSFCell cell = row.createCell(i);
                    cell.setCellValue(cells[i]);
                }
                lineNum+=1;
            }
            FileOutputStream os = new FileOutputStream(excelPath);
            wb.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
