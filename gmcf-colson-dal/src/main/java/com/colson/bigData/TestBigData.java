package com.colson.bigData;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;


import static com.colson.bigData.AdvanceRepayment.advanceRepayment;

import static com.colson.bigData.NomalRepayment.nomalRepayment;
import static com.colson.bigData.OverdueCurrentStage.overdueCurrentStage;
import static com.colson.bigData.OverdueEarlySettle.overdueEarlySettle;
import static com.colson.bigData.OverdueNotSettle.overdueNotSettle;
import static com.colson.bigData.OverdueRepayCurrentStage.overdueRepayCurrentStage;
import static com.colson.bigData.OverdueRepayLostsStages.overdueRepayLostsStages;
import static com.colson.bigData.OverdueRepayPartCurrentStage.overdueRepayPartCurrentStage;
import static com.colson.bigData.PreRepayCurrentStage.preRepayCurrentStage;
import static com.colson.bigData.PreRepayLostsStages.preRepayLostsStages;

public class TestBigData {
	public static Integer insertSize = 100;

	public static void main(String[] args) throws Exception {

		//--------------------------------连接数据库----------------------
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://10.159.41.19:3306/db_gmcf_bec?useUnicode=true&characterEncoding=utf8";
		String user="root";
		String password="123456";

		//1、新建驱动
		Driver driverInstance = (Driver) Class.forName(driver).newInstance();
		//2、注册驱动
		DriverManager.registerDriver(driverInstance);
		//3、获取连接
		Connection conn = DriverManager.getConnection(url, user, password);

		//----------------------对数据库进行操作-------------------
		//记录开始时间
		Long begin=System.currentTimeMillis();
		//-----------插入数据----------
		//sql语句前缀

		//设置事务为非自动提交
		conn.setAutoCommit(false);
		//使用PrepareStatement更好
		PreparedStatement pstate = conn.prepareStatement("");

		//--------------------------十万条数据-------------
		//设置外循环，总提交事务的次数
		List<String> workList = new ArrayList<>(1000);

		List<String> dealLastMonthList = DealLastMonth.dealLastMonth();
		List<String> nomalRepaymentList = nomalRepayment();
		List<String> advanceRepaymentList = advanceRepayment();
		List<String> preRepayCurrentStageList = preRepayCurrentStage();
		List<String> preRepayLostsStagesList = preRepayLostsStages();
		List<String> overdueCurrentStageList = overdueCurrentStage();
		List<String> earlySettleList = EarlySettle.earlySettle();
		List<String> overdueRepayCurrentStageList = overdueRepayCurrentStage();
		List<String> overdueRepayPartCurrentStageList = overdueRepayPartCurrentStage();
		List<String> overdueRepayLostsStagesList = overdueRepayLostsStages();
		List<String> overdueEarlySettleList = overdueEarlySettle();
		List<String> overdueNotSettleList = overdueNotSettle();


		workList.addAll(dealLastMonthList);

		workList.addAll(nomalRepaymentList);
		workList.addAll(advanceRepaymentList);
		workList.addAll(preRepayCurrentStageList);
		workList.addAll(preRepayLostsStagesList);
		workList.addAll(overdueCurrentStageList);
		workList.addAll(earlySettleList);
//		workList.addAll(overdueRepayCurrentStageList);
//		workList.addAll(overdueRepayPartCurrentStageList);
//		workList.addAll(overdueRepayLostsStagesList);
//		workList.addAll(overdueEarlySettleList);
//		workList.addAll(overdueNotSettleList);
//		System.out.println(workList.size());

		String sql;
		for(int i=0;i<workList.size();i++){

			//构建完整的sql
			sql = workList.get(i);
			//添加sql
			pstate.addBatch(sql);
			//执行sql
			pstate.executeBatch();
			//提交事务
			conn.commit();
		}

		//大循环完毕，关闭连接
		pstate.close();
		conn.close();
		//结束时间
		Long end = System.currentTimeMillis();
		System.out.println(workList.size()+"条数据，插入数据库耗时："+(end-begin)+"ms");
	}


	@Test
	public void  test(){
		String applyDateTime = DateFormatUtils.format(new Date(),"yyyy-MM-dd");
//		System.out.println(applyDateTime);
		System.out.println(Utils.addOneMonth("2019-07-17 11:30:45"));
	}




}
