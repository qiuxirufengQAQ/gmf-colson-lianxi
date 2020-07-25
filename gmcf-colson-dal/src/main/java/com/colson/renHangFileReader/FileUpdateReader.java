package com.colson.renHangFileReader;


import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUpdateReader {

	@Test
	public void updateData(){
		String sourceFilePath = "D:\\a_update\\DI_GR_ZYXX.sql";

		String outPutFilePath = "D:\\a_update\\DI_GR_ZYXX_OUT.sql";

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

				StringBuilder stringBuilder = new StringBuilder();
				Map<String,Object> data = new HashMap<>();
				data.put("biddingNo",split[0]);
				stringBuilder.append(split[0]);

				if (split[1].equals("")){
					stringBuilder.append("Z");
				}else {
					stringBuilder.append(split[1]);
				}

				if ("".equals(split[2])){
					stringBuilder.append("暂缺");
				}else {
					stringBuilder.append(split[2]);
				}

				if ("".equals(split[3])){
					stringBuilder.append("Z");
				}else {
					stringBuilder.append(split[3]);
				}

				stringBuilder.append(split[4]);
				stringBuilder.append(split[5]);
				stringBuilder.append(split[6]);
				stringBuilder.append(split[7]);
				stringBuilder.append(split[8]);
				stringBuilder.append(split[9]);
				stringBuilder.append(split[10]);
				stringBuilder.append(split[11]);

				bw.write(stringBuilder.toString());
				bw.newLine();
				bw.flush();
			}
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	更新账户状态为空的数据，依据为24个月的最后一个字段是否为数字，来确认账户状态是1-正常还是2-逾期
	@Test
	public void updateAccountStatusBy24Months(){
		String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\上个月正常还款的数据_out4.sql";
		String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\上个月正常还款的数据_out4.sql";

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
			int count =0;
			int count1 =0;
			int count2 =0;
			int count3 =0;

			for (String s:contentLine) {
				String[] split = s.split("\\|");
				String biddingNo = split[3];
				String accountStatus = split[29];
				String twoYearStr = split[30];
				if (accountStatus.equals("")){
					count++;
					if(twoYearStr.substring(twoYearStr.length()-1).equals("N")){
						split[29] = "1";
					}else if(twoYearStr.substring(twoYearStr.length()-1).equals("C")){
						split[29] = "3";
					}else if(Integer.parseInt(twoYearStr.substring(twoYearStr.length()-1))>0){
						split[29] = "2";
					}
				}else{
//					System.out.println(twoYearStr);
					if(twoYearStr.substring(twoYearStr.length()-1).equals("N")){
						if(!accountStatus.equals("1")){
							count1++;
							split[29] = "1";
						}
					}else if(twoYearStr.substring(twoYearStr.length()-1).equals("C")){
						if (!accountStatus.equals("3")){
							split[29] = "3";
							count2++;
						}
					}else if(Integer.parseInt(twoYearStr.substring(twoYearStr.length()-1))>0){
						if (!accountStatus.equals("2")){
							System.out.println(accountStatus+":"+twoYearStr);
//							split[29] = "2";
							count3++;
							StringBuilder sb = new StringBuilder(twoYearStr);
							sb.replace(23,24,"N");
							split[30] = sb.toString();
						}
					}
				}
//					bw.write(biddingNo);
//					bw.newLine();
//					bw.flush();
//				StringBuilder stringBuilder = new StringBuilder();
//				for (int i = 0; i < split.length; i++) {
//					stringBuilder.append(split[i]).append("|");
//				}
//				bw.write(stringBuilder.toString());
//				bw.newLine();
//				bw.flush();
			}
			System.out.println("count:"+count);
			System.out.println("count1:"+count1);
			System.out.println("count2:"+count2);
			System.out.println("count3:"+count3);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	计算24个月字段不符合情况的条数，即月数到某一个月，不等于*后边的个数
	@Test
	public void update24Months(){
		String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\202001\\正常还款的数据_out.sql";
		String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\202001\\上个月一期结清2_out.sql";

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
			int count =0,count1=0;

			for (String s:contentLine) {
				String[] split = s.split("\\|");
				String startDay = split[5];
				String biddingNo = split[3];
				String accountStatus = split[29];
				String twoYearStr = split[30];
				Integer month = Integer.valueOf(startDay.substring(4,6));
//				表示1月份的数据
				Integer index = 14-month;
				if(twoYearStr.indexOf("*")!=(24-index)){
				    count++;
					System.out.println(biddingNo);
//					String addChar = "C";
//					if(accountStatus.equals("2")){
//						String lastChar = String.valueOf(twoYearStr.charAt(23));
//						if(lastChar.equals("N") || lastChar.equals("*")){
//							addChar = "1";
//						}else if(Integer.valueOf(lastChar)>0){
//							addChar = String.valueOf(Integer.valueOf(lastChar)+1);
//						}
//					}else if(accountStatus.equals("1")){
//						addChar = "N";
//					}else if(accountStatus.equals("3")){
//						addChar = "C";
//					}

//					twoYearStr = twoYearStr.concat(addChar);
//
//					if(twoYearStr.length()>=24){
//						split[30] = twoYearStr.substring(twoYearStr.length()-24);
//					}
//					if(split[30].indexOf('*')!=(24-index)){
//						count1++;
//					}
//					bw.write(s);
//					bw.newLine();
//					bw.flush();
				}
//				else if(twoYearStr.indexOf('*')==(24-index)-1){
//					split[30] = "/".concat(twoYearStr.substring(0,23));
//					if(split[30].indexOf('*')!=(24-index)){
//						count1++;
//					}
//				}

//				StringBuilder stringBuilder = new StringBuilder();
//				for (int i = 0; i < split.length; i++) {
//					stringBuilder.append(split[i]).append("|");
//				}
//				bw.write(stringBuilder.toString());
//				bw.newLine();
//				bw.flush();
			}
			System.out.println(count);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	更新24个月字段为空的情况，如果为空，则将这个字段设为//////////////*
@Test
public void update24MonthsWithNull(){
	String sourceFilePath = "D:\\updateData\\201909\\JC_201909_2.sql";
	String outPutFilePath = "D:\\updateData\\201909\\JC_201909_2_更新24个月字段为空的数据.sql";

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
		int count =0;

		for (String s:contentLine) {
			String[] split = s.split("\\|");
			if("".equals(split[30]) || split[30] == null){
				count++;
				split[30] = "///////////////////////*";
			}

				StringBuilder stringBuilder = new StringBuilder();
				for (int i = 0; i < split.length; i++) {
					stringBuilder.append(split[i]).append("|");
				}
				bw.write(stringBuilder.toString());
				bw.newLine();
				bw.flush();
		}
		System.out.println(count);
		bw.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	//	计算24个月字段不符合情况的条数，更新最后一个字段为C
	@Test
	public void testContainContent(){
		String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\202001\\正常还款的数据2_out.sql";
//		String containFilePath = "D:\\updateData\\201908\\JC_201908_3_contain.sql";
		String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\202001\\正常还款的数据3_out.sql";

		File inFile = new File(sourceFilePath);
//		File containFile = new File(containFilePath);
		File outFile = new File(outPutFilePath);

		try{
			if (!outFile.exists()){
				outFile.createNewFile();
			}
		}catch (IOException io){
			io.printStackTrace();
		}

		try {
			Map<String,String> containMap = new HashMap<>();

			FileOutputStream fos = new FileOutputStream(outFile,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//			List<String> containLine = Files.readAllLines(containFile.toPath(), StandardCharsets.UTF_8);
//			containLine.stream().forEach(i->containMap.put(i,i));

			List<String> contentLine = Files.readAllLines(inFile.toPath(), StandardCharsets.UTF_8);
			int count =0;

			for (String s:contentLine) {
				String[] split = s.split("\\|");
				String startDay = split[5];
//				split[26] = split[27];
//				Integer month = Integer.valueOf(startDay.substring(4,6));
				String biddingNo = split[3];
                split[29] = "3";
                Integer mustAmount = Integer.parseInt(split[17]);
                Integer actualAmount = Integer.parseInt(split[18]);
                Integer balance = Integer.parseInt(split[19]);
                split[17] = String.valueOf(mustAmount+balance);
                split[18] = String.valueOf(actualAmount+balance);
                split[19] = "0";
                split[20] = "0";
                split[21] = "0";
				String accountStatus = split[29];

				String twoYearStr = split[30];
//                StringUtils.isNotEmpty(containMap.get(biddingNo))
//                Integer.valueOf(accountStatus)==3 &&
//                !twoYearStr.substring(23).equals("C")
				if (accountStatus.equals("3") && !twoYearStr.substring(twoYearStr.length()-1).equals("C")){
					count++;
					System.out.println(biddingNo);
//					StringBuilder sb = new StringBuilder(twoYearStr);
//					sb.replace(23,24,"C");
//					split[30] = sb.toString();
//					bw.write(s);
//					bw.newLine();
//					bw.flush();
				}
//
//				StringBuilder stringBuilder = new StringBuilder();
//				for (int i = 0; i < split.length; i++) {
//					stringBuilder.append(split[i]).append("|");
//				}
//				bw.write(stringBuilder.toString());
//				bw.newLine();
//				bw.flush();
			}
			System.out.println(count);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@Test
	public void retainData(){
		String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201910\\数据订正\\GR_GRXX_JC_201910_4.txt";

		String retainFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201910\\数据修复\\账户状态和逾期字段不正确0contain.sql";

		String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201910\\数据修复\\jc_修正账户状态为空.txt";
        String outPutFilePath2 = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201910\\数据修复\\jc_修正账户状态和24个月不对应biddingNo2.txt";
        String outPutFilePath3 = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201910\\数据修复\\jc_原始数据.txt";

		File inFile = new File(sourceFilePath);

		File retainFile = new File(retainFilePath);

		File outFile = new File(outPutFilePath);
        File outFile2 = new File(outPutFilePath2);
        File outFile3 = new File(outPutFilePath3);

		try{
			if (!outFile.exists()){
				outFile.createNewFile();
			}
            if (!outFile2.exists()){
                outFile2.createNewFile();
            }
            if (!outFile3.exists()){
                outFile3.createNewFile();
            }
		}catch (IOException io){
			io.printStackTrace();
		}

		try {
			HashMap<String,String> retainMap = new HashMap<>();
			List<String> retainLines = Files.readAllLines(retainFile.toPath(), Charset.forName("UTF-8"));


			for (String retain:retainLines) {
				retainMap.put(retain,retain);
			}

			FileOutputStream fos = new FileOutputStream(outFile,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            FileOutputStream fos2 = new FileOutputStream(outFile2,true);
            BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2));

            FileOutputStream fos3 = new FileOutputStream(outFile3,true);
            BufferedWriter bw3 = new BufferedWriter(new OutputStreamWriter(fos3));

			List<String> contentLine = Files.readAllLines(inFile.toPath(), Charset.forName("UTF-8"));

			for (String s:contentLine) {
                String[] split = s.split("\\|");

                String biddingNo = split[3];
                String accountStatus = split[29];
                String twoYearStr = split[30];
                char lastMonth = twoYearStr.charAt(twoYearStr.length()-1);


				if (retainMap.containsKey(biddingNo) && "".equals(accountStatus)){
//                    bw3.write(s);
//                    bw3.newLine();
//                    bw3.flush();
//				    if(Character.isDigit(lastMonth)){
//				        split[29] = "2";
//                    }else if('N'==lastMonth){
//				        split[29] = "1";
//                    }else if('C'==lastMonth){
//				        split[29] = "3";
//                    }
//
//
//                    StringBuilder stringBuilder = new StringBuilder();
//                    for (int i = 0; i < split.length; i++) {
//                        stringBuilder.append(split[i]).append("|");
//                    }
//                    bw.write(stringBuilder.toString());
//                    bw.newLine();
//                    bw.flush();
				}else if(retainMap.containsKey(biddingNo) && "2".equals(accountStatus) && 'N'==lastMonth){
                    bw2.write(biddingNo);
                    bw2.newLine();
                    bw2.flush();
                }
			}
			bw.close();
			bw2.close();
            bw3.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
    public void test(){
        String str="hello world";

//        str=str.replace(str.charAt(str.length()-1)+"","C");

        System.out.println(str.substring(3,11));
        System.out.println(str.indexOf("l"));
        System.out.println(str.length());
    }



	@Test
	public void getCommonFromTwoFiles(){
		String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\天津\\问题\\很多月缺少立项和放款.txt";

		String retainFilePath = "D:\\国美金融\\项目\\COF备忘录\\天津\\问题\\Loan_20191203_0.txt";

//		String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\期数比较问题和24月问题\\当前大于最高取出的jc数据.sql";
//        String outPutFilePath2 = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复文件24个月差别\\结果数据201911.sql";

		File inFile = new File(sourceFilePath);

		File retainFile = new File(retainFilePath);

//		File outFile = new File(outPutFilePath);
//        File outFile2 = new File(outPutFilePath2);

//		try{
////			if (!outFile.exists()){
////				outFile.createNewFile();
////			}
////            if (!outFile2.exists()){
////                outFile2.createNewFile();
////            }
//		}catch (IOException io){
//			io.printStackTrace();
//		}

		try {
			HashMap<String,String> retainMap = new HashMap<>();
			List<String> retainLines = Files.readAllLines(retainFile.toPath(), StandardCharsets.UTF_8);
//            FileOutputStream fos = new FileOutputStream(outFile,true);
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			for (String retain:retainLines) {
			    String[] split1 = retain.split("\\|");
                retainMap.put(split1[0],retain);
			}
//            System.out.println(retainMap.size());



//            FileOutputStream fos2 = new FileOutputStream(outFile2,true);
//            BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2));

			List<String> contentLine = Files.readAllLines(inFile.toPath(), StandardCharsets.UTF_8);
			int count =0;
			String twoYearStr="";

			for (String s:contentLine) {
//			    String[] split = s.split("\\|");
			    String biddingNo = s;

				if (retainMap.containsKey(biddingNo)){
					System.out.println(retainMap.get(biddingNo));
					count++;
//				    twoYearStr = retainMap.get(biddingNo);
//				    if (!split[30].equals(twoYearStr)){
//                        count++;
//				        bw.write(s);
//                        bw.newLine();
//                        bw.flush();
//                        bw2.write(biddingNo+"|"+twoYearStr);
//                        bw2.newLine();
//                        bw2.flush();
                    }else {
//                    System.out.println(biddingNo);
                }

//				    String last = split[30].substring(split[30].length()-1);
//				    if("N".equals(last)){
//				        split[29] = "1";
//				        split[28] = "1";
//				        split[20] = "0";
//                        split[21] = "0";
//                        split[22] = "0";
//                        split[23] = "0";
//                        split[24] = "0";
//                        split[25] = "0";
//                    }

//                    StringBuilder stringBuilder = new StringBuilder();
//                    for (int i = 0; i < split.length; i++) {
//                        stringBuilder.append(split[i]).append("|");
//                    }
//                    bw.write(s);
//                    bw.newLine();
//                    bw.flush();
				}
			System.out.println(count);
//            System.out.println(retainMap);
//				count++;
//				bw.write(s);
//				bw.newLine();
//				bw.flush();
//            bw.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println();
//			bw2.close();

	}

//	替换24个月字段
	@Test
	public void updateAccountStatusAnd24Month(){
		String sourceFilePath = "D:\\updateData\\201910\\GR_GRXX_JC_201910_1.sql";

		String retainFilePath = "D:\\updateData\\201910\\GR_GRXX_JC_201910_1_contain.sql";

		String outPutFilePath = "D:\\updateData\\201910\\GR_GRXX_JC_201910_1_contain_out.sql";

		File inFile = new File(sourceFilePath);

		File retainFile = new File(retainFilePath);

		File outFile = new File(outPutFilePath);

		try{
			if (!outFile.exists()){
				outFile.createNewFile();
			}
		}catch (IOException io){
			io.printStackTrace();
		}

		try {
			HashMap<String,String> retainMap = new HashMap<>();
			List<String> retainLines = Files.readAllLines(retainFile.toPath(), Charset.forName("UTF-8"));


			for (String retain:retainLines) {
				String[] split = retain.split("\\|");

				retainMap.put(split[0],split[1]);
			}

			FileOutputStream fos = new FileOutputStream(outFile,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			List<String> contentLine = Files.readAllLines(inFile.toPath(), Charset.forName("UTF-8"));
			int count = 0;
			for (String s:contentLine) {
				String[] split = s.split("\\|");

				String biddingNo = split[3];

				if (retainMap.containsKey(biddingNo)){
					count++;
					split[30] = retainMap.get(biddingNo);
				}
				StringBuilder stringBuilder = new StringBuilder();
				for (int i = 0; i < split.length; i++) {
					stringBuilder.append(split[i]).append("|");
				}
				bw.write(stringBuilder.toString());
				bw.newLine();
				bw.flush();
			}
			System.out.println(count);
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//	校验期数之间的关系（累计逾期期数大于等于最大逾期期数大于等于当前逾期期数）
	@Test
	public void testCurrentOverdueStage(){
		String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\202001\\正常还款的数据_out.sql";

//		String retainFilePath = "D:\\updateData\\201910\\GR_GRXX_JC_201910_1_contain.sql";

		String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\202001\\正常还款的数据2_out.sql";

		File inFile = new File(sourceFilePath);

//		File retainFile = new File(retainFilePath);

		File outFile = new File(outPutFilePath);

		try{
			if (!outFile.exists()){
				outFile.createNewFile();
			}
		}catch (IOException io){
			io.printStackTrace();
		}

		try {
//			HashMap<String,String> retainMap = new HashMap<>();
//			List<String> retainLines = Files.readAllLines(retainFile.toPath(), Charset.forName("UTF-8"));

//			for (String retain:retainLines) {
//				String[] split = retain.split("\\|");
//				retainMap.put(split[0],split[1]);
//			}

			FileOutputStream fos = new FileOutputStream(outFile,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			List<String> contentLine = Files.readAllLines(inFile.toPath(), StandardCharsets.UTF_8);
			int count = 0;
			for (String s:contentLine) {
				String[] split = s.split("\\|");

				Integer totalOverdueStage = Integer.parseInt(split[26]);
				Integer maxOverdueStage = Integer.parseInt(split[27]);
				count++;
				String twoYear = split[30];
				String biddingNo = split[3];
				Integer totalStage = Integer.parseInt(split[13]);
				String newTwoYear = twoYear.substring(twoYear.indexOf("*"), Math.min(twoYear.indexOf("*") + totalStage + 1, twoYear.length()));
//                System.out.println(biddingNo+"|"+twoYear+"|"+totalStage+"|"+newTwoYear);

				totalOverdueStage = isNumeric(newTwoYear);
				maxOverdueStage = maxNumeric(newTwoYear)>'0'?maxNumeric(newTwoYear)-'0':maxNumeric(newTwoYear);

				Integer currentStage = Integer.parseInt(split[20]);
				Integer currentAmount = Integer.parseInt(split[21]);


				split[26] = totalOverdueStage.toString();
				split[27] = maxOverdueStage.toString();
//                System.out.println(biddingNo+"|"+currentStage+"|"+currentAmount+"|"+split[26]+"|"+split[27]+"|"+twoYear);

                if(currentStage>Integer.parseInt(split[27])){
                    split[20] = split[27];
                    split[21] = String.valueOf(currentAmount/currentStage*Integer.parseInt(split[27]));
                }
                System.out.println(biddingNo+"|"+split[20]+"|"+split[21]+"|"+split[26]+"|"+split[27]+"|"+twoYear);


				StringBuilder stringBuilder = new StringBuilder();
				for (int i = 0; i < split.length; i++) {
					stringBuilder.append(split[i]).append("|");
				}
				bw.write(stringBuilder.toString());
				bw.newLine();
				bw.flush();
			}
//			System.out.println(count);
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	替换当前逾期期数
	@Test
	public void updateCurrentOverdueStage(){
		String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\上个月正常还款的数据_out2.sql";

//		String retainFilePath = "D:\\updateData\\201910\\GR_GRXX_JC_201910_1_contain.sql";

		String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\修改后的数据\\上个月正常还款的数据_out3.sql";

		File inFile = new File(sourceFilePath);

//		File retainFile = new File(retainFilePath);

		File outFile = new File(outPutFilePath);

		try{
			if (!outFile.exists()){
				outFile.createNewFile();
			}
		}catch (IOException io){
			io.printStackTrace();
		}

		try {
			HashMap<String,String> retainMap = new HashMap<>();
//			List<String> retainLines = Files.readAllLines(retainFile.toPath(), Charset.forName("UTF-8"));

//			for (String retain:retainLines) {
//				String[] split = retain.split("\\|");
//				retainMap.put(split[0],split[1]);
//			}

			FileOutputStream fos = new FileOutputStream(outFile,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			List<String> contentLine = Files.readAllLines(inFile.toPath(), StandardCharsets.UTF_8);
			int count = 0;
			for (String s:contentLine) {
				String[] split = s.split("\\|");

				Integer totalOverdueStage = 0;
				Integer maxOverdueStage = 0;

				String twoYear = split[30];

				totalOverdueStage = isNumeric(twoYear);
                maxOverdueStage = maxNumeric(twoYear)-'0';


                split[26] = totalOverdueStage.toString();
				split[27] = maxOverdueStage.toString();

				StringBuilder stringBuilder = new StringBuilder();
				for (int i = 0; i < split.length; i++) {
					stringBuilder.append(split[i]).append("|");
				}
				bw.write(stringBuilder.toString());
				bw.newLine();
				bw.flush();
			}
			System.out.println(count);
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public static int isNumeric(String str) {
        char[] ch = str.toCharArray();
        //创建计数器
        int count = 0;
        for( int i = 0 ; i < str.length() ; i++) {
            if(ch[i]>='0'&& ch[i]<='9') {
                count++;
            }
        }
        return count;
    }
    public static int maxNumeric(String str) {
        char[] ch = str.toCharArray();
        //创建最大数值
        int count = 0;
        for( int i = 0 ; i < str.length() ; i++) {
            if(ch[i]>='0'&& ch[i]<='9') {
                if(Integer.valueOf(ch[i])>count){
                    count = Integer.valueOf(ch[i]);
                }else if(Integer.valueOf(ch[i]) ==7){
                	count++;
				}
            }
        }
        return count;
    }



    //	给标的号前后加上引号和在后边加上逗号
    @Test
    public void updateBiddingNo(){
        String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\22万人行数据问题\\报送问题数据.txt";

        String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\22万人行数据问题\\报送问题数据_biddingNo.txt";

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

            List<String> contentLine = Files.readAllLines(inFile.toPath(), StandardCharsets.UTF_8);
            StringBuilder stringBuilder;
            for (String s:contentLine) {
                stringBuilder = new StringBuilder();
                stringBuilder = stringBuilder.append("\'").append(s).append("\',");
                bw.write(stringBuilder.toString());
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    @Test
    public void updateBiddingNo24Month(){
        String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201910\\账户状态和24个月不对应.sql";

        String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201910\\账户状态和24个月不对应_out.sql";

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

            List<String> contentLine = Files.readAllLines(inFile.toPath(), StandardCharsets.UTF_8);
            int count = 0;

            for (String s:contentLine) {
                boolean isOverdue;

                String[] split = s.split("\\|");
                String biddingNo = split[0];
                String actualPay = split[1];
                String status = split[2];
                String months = split[3];
                Integer mothsInt = Integer.parseInt(months);
                String twoYear = split[4];
                if("".equals(actualPay)){
                    isOverdue = true;
                }else {
                    if("1".equals(status)){
                        isOverdue = false;
                    }else if("2".equals(status)){
                        isOverdue = true;
                    }
                }
                char[] twoYearChar = twoYear.toCharArray();
                StringBuilder sb = new StringBuilder(twoYear);
                if('N'== twoYearChar[Integer.parseInt(months)-1] &&
                        Character.isDigit(twoYearChar[Integer.parseInt(months)]) &&
                        Integer.parseInt(String.valueOf(twoYearChar[Integer.parseInt(months)])) >1){
                    sb.replace(mothsInt-1,mothsInt,String.valueOf(Integer.parseInt(String.valueOf(twoYearChar[Integer.parseInt(months)])) -1));
                }
                if("".equals(actualPay)){
                    status = "2";
                }

                sb = new StringBuilder(sb.substring(0,mothsInt));
                int num1 = 24-sb.toString().length();
                for (int i=0;i<num1;i++){
                    sb.insert(0,"/");
                }
                if (("N".equals(sb.substring(sb.length() - 1)) && status.equals("2"))) {
                    count++;
                    System.out.println(biddingNo);
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder = stringBuilder.append(biddingNo).append("|").append(sb);

                bw.write(stringBuilder.toString());
                bw.newLine();
                bw.flush();
            }
            System.out.println(count);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    生成更新sql
    @Test
    public void genUpdateSql(){
        String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\期数比较问题和24月问题\\更新的数据2.sql";

//        String containFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201911\\问题\\469条数据和账单日.tsv";
        String outPutFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\期数比较问题和24月问题\\更新的数据2_sql.sql";

        File inFile = new File(sourceFilePath);
        File outFile = new File(outPutFilePath);

//        File containFile = new File(containFilePath);

        try{
            if (!outFile.exists()){
                outFile.createNewFile();
            }
        }catch (IOException io){
            io.printStackTrace();
        }
        try {

//			HashMap<String,String> retainMap = new HashMap<>();
            FileOutputStream fos = new FileOutputStream(outFile,true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

//			List<String> containLine = Files.readAllLines(containFile.toPath(), StandardCharsets.UTF_8);
//			for(String contain:containLine){
//				String[] split = contain.split("\\|");
//				retainMap.put(split[0],split[1]);
//			}

            List<String> contentLine = Files.readAllLines(inFile.toPath(), StandardCharsets.UTF_8);

//            stringBuilder = stringBuilder.append("select * from GR_GRXX_JC T WHERE ");
            int count = 0;
            for (String s:contentLine) {

                String[] split = s.split("\\|");
                String biddingNo = split[0];
                String currentStage = split[1];
                String currentAmount = split[2];
                String totalOverdue = split[3];
                String maxOverdue = split[4];
                String twoYear = split[5];
//				UPDATE GR_GRXX_JC T SET T.JSYHKRQ='',T.BYYHKJE='',T.BYSJHKJE='',T.LJYQQS='',T.ZGYQQS='' WHERE T.YWH='' AND T.BYSJHKRQ='';

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder = stringBuilder.append("UPDATE GR_GRXX_JC SET DQYQQS='").append(currentStage).append("',DQYQZE='").append(currentAmount).append("',LJYQQS='").append(totalOverdue).append("',ZGYQQS='").append(maxOverdue)
                        .append("',HKZT_24='").append(twoYear).append("' WHERE YWH='")
                        .append(biddingNo).append("' AND SUBSTR(JSYHKRQ,1,6)='201912';");
                bw.write(stringBuilder.toString());
                bw.newLine();
                bw.flush();
//                String actualMoney = split[18];
//                String totalOverdue = split[26];
//                String maxOverdue = split[27];
//                stringBuilder = stringBuilder.append("(T.YWH='").append(biddingNo).append("' AND T.BYSJHKRQ='").append(actualPay).append("') or ");
            }

			System.out.println(count);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testTwoData(){
        String sourceFilePath = "D:\\国美金融\\项目\\COF备忘录\\人行\\修复数据201912\\结清的数据.sql";
        File inFile = new File(sourceFilePath);
        HashMap<String,String> retainMap = new HashMap<>();
        try {
            List<String> retainLines = Files.readAllLines(inFile.toPath(), StandardCharsets.UTF_8);

            for (String retain:retainLines) {
//			    String[] split1 = retain.split("\\|");
                if(retainMap.containsKey(retain)){
                    System.out.println(retain);
                }else {
                    retainMap.put(retain,retain);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
