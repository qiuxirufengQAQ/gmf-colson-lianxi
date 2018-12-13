package com.colson.dal;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelRead {
    public static void main(String[] args) throws Exception {
        Map<String, String> map = readXls();
        for (String key:map.keySet()) {
            System.out.println(key+":"+map.get(key));
        }
        Map<String,String> newMap = new HashMap<>();
        newMap.put("0.1","1.2");
        System.out.println(newMap.get("0.10"));

    }
    public static Map<String, String> readXls() throws Exception {
        InputStream is = new FileInputStream("/参数表.xlsx");
        XSSFWorkbook excel = new XSSFWorkbook(is);
        Map<String,String> feeMap = new HashMap<String, String>();

        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
            XSSFSheet sheet = excel.getSheetAt(numSheet);
            if (sheet == null)
                continue;
            // 循环行Row
            for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {
                XSSFRow row = sheet.getRow(rowNum);
                String profitRate = null;
                String comprehensiveRate = null;
                if (row == null)
                    continue;
                XSSFCell cell0 = row.getCell(0);
                if (cell0 == null)
                    continue;
                cell0.setCellType(1);
                profitRate = cell0.getStringCellValue();
                XSSFCell cell1 = row.getCell(1);
                if (cell1 == null)
                    continue;
                cell1.setCellType(1);
                comprehensiveRate = cell1.getStringCellValue();
                feeMap.put(profitRate,comprehensiveRate);
            }
        }

        return feeMap;
    }
}
