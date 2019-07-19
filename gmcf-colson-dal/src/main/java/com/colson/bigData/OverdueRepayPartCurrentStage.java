package com.colson.bigData;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.colson.bigData.TestBigData.insertSize;
import static com.colson.bigData.Utils.addOneMonth;
import static com.colson.bigData.Utils.addThreeDays;
import static com.colson.bigData.Utils.genSimpleUUID;

public class OverdueRepayPartCurrentStage {
	//逾期还部分当期
	public static List<String> overdueRepayPartCurrentStage(){

		String nowTime = DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
		String nullTime = "0000-00-00 00:00:00";
		List<String> dealLastMonthList = new ArrayList<>(100);


		//bec数据
		String biddingNo = "gmcfbec";
		Integer shortNo = 1090000000;
		Integer biddingStatus = 3;
		String loanNo = "gmcfloc";
		String productName = "国美易卡-额度产品";
		String productCode = "5551";
		String businessGroupCode = "MJ";
		String productMirrorNo = "a8409c1cd0b74cc8b86da4922d31b54c";
		String productPricingNo = "226b0138edaf47d0a2f2a830981bd79b";
		String productPricingMirrorNo = "3f404e60b94a41c6bea2fc4b3c5bad0a";
		String repaymentFormulaNo = "7c596f0c075941dcacb0a0d4edcbb8e7";
		String repaymentFormulaName = "南京银行等额本息债务计算";
		BigDecimal biddingAmount = new BigDecimal(12000);
		String currencyCode = "CNY";
		String applyDateTime = nowTime;
		String auditApproveDateTime = nowTime;
		String fullBiddingDatetime = nowTime;
		String settleDateTime = nullTime;
		String signingDatetime = "2019-02-10 11:10:45";
		String dealDatetime = "2019-02-10 11:30:45";
		String cancelDatetime = nullTime;
		String remainRightCapitalCode = "0000";
		String remainRightOwnerNo = "4bfbe1bd2fee4513a0aa3df699fb679c";
		String clearingFormulaNo = "a93940ecd4e2423cb76edbdc068c457a";
		String profitDistributionNo = "";
		String repaymentAllocationRuleNo = "e33b46a06204485090d7b0862083462b";
		String derateAllocationRuleNo = "7361c772b03311e896f8529269fb1459";
		String masterBorrowerNo = "a8399587b89848b8844fe6b24549b347";
		Integer masterBorrowerType = 2;
		String master_borrower_name = "李盟珍";
		BigDecimal yearRate = new BigDecimal(0.080000);
		BigDecimal monthRate = new BigDecimal(0.006670);
		BigDecimal dayRate = new BigDecimal(0.000220);
		Integer originTotalStage = 12;
		Integer totalStage = 12;
		Integer currentStage = 6;
		String startDate = "2019-02-10 00:00:00";
		String endDate = "2020-02-10 00:00:00";
		String payoutBankCardNo = "f2c1051968154b359486ef4f8d5e2c40";
		String repaymentBankCardNo = "f2c1051968154b359486ef4f8d5e2c40";
		Integer cooperationType = 1;
		Integer isOverdue = 0;
		Integer isOffBalance = 0;
		String cbdStartDate = nullTime;
		Integer continuousOverdueStages = 0;
		Integer maxContinuousOverdueStages = 0;
		Integer ageAccount = 0;
		String channelSource = "10000000";
		String createdTime = "DEFAULT";
		String modifiedTime = "DEFAULT";
		String nextMustPayDate = "2019-08-10 11:30:45";
		Integer extCurrentStage = 6;
		String repaymentStatusArray = "*NNNN1NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN";
		String maxContinuousStartDate = nullTime;
		Integer isGuaranteed = 0;
		String capitalCodes = "0000,C5001";
		String beforeMustPayDate = "2019-07-10 11:30:45";

		//出资人表
		String gomeCapitalNo = "49f739f0df9c4fb68903b04541b5a78a";
		String otherCapitalNo = "2e9e55710b9443288b7344b413904d6e";
		String gomeCapitalCode = "0000";
		String otherCapitalCode = "C5001";
		String gomeCapitalMemberNo = "4bfbe1bd2fee4513a0aa3df699fb679c";
		String otherCapitalMemberNo = "02f7e7844f0442b7b9675ea08a9659f5";
		String gomeCapitalMirrorNo = "e87260ee571b4d37a04f7ca227ab714a";
		String otherCapitalMirrorNo = "c3762c73d8da41d5952c597f2b1776aa";
		String gomeCapitalPricingNo = "7ee8e935b17b4b6991a3de9a92dca589";
		String otherCapitalPricingNo = "433dc06500b4affa2e55f87837c7756c";
		String gomeCapitalPricingMirrorNo = "d0fefab9f42d4ae1bd0c5e0a95e84aff";
		String otherCapitalPricingMirrorNo = "9966bb1a0d7d46b3b8bc35effe29157c";
		String gomeCapitalName = "国美金融";
		String otherCapitalName = "南京银行";
		BigDecimal gomeInverstment = new BigDecimal(0.010000);
		BigDecimal otherInverstment = new BigDecimal(0.990000);
		BigDecimal gomeInverstmentAmount = new BigDecimal(120);
		BigDecimal otherInverstementAmount = new BigDecimal(11880);
		String investmentDatetime = "2019-02-10 17:51:48";
		Integer isEffective = 1;

		//dec债务表
		BigDecimal zero = new BigDecimal(0);

		String debitNo = "gmcfdec";
		//		String bidding_no =
		//		String loan_no
		String firstDebitMemberNo = "a8399587b89848b8844fe6b24549b347";
		Integer firstDebitMemberType = 2;
		String lastDebitMemberNo = "a8399587b89848b8844fe6b24549b347";
		Integer lastDebitMemberType = 2;
		Integer decCurrentStage =1;
		Integer debitStatus = 1;
		BigDecimal mustPayPrincipal = new BigDecimal(1000);
		BigDecimal actualPayPrincipal = zero;
		BigDecimal deratePrincipal = zero;
		BigDecimal mustPayInterest = zero;
		BigDecimal actualPayInterest = zero;
		BigDecimal derateInterest = zero;
		BigDecimal mustPayPenaltyPnterest =zero;
		BigDecimal actualPayPenaltyInterest = zero;
		BigDecimal deratePenaltyInterest = zero;
		BigDecimal mustPayTotalFee = zero;
		BigDecimal actualPayTotalFee = zero;
		BigDecimal derateTotalFee =zero;
		String feeDetailJson = "{\"ACCOUNT_MANAGE_FEE\": {\"feeCode\": \"ACCOUNT_MANAGE_FEE\", \"derateAmount\": 0, \"mustPayAmount\": 2.44, \"actualPayAmount\": 0}}";
		String valueDate = "2019-02-10 11:30:45";
		String dueDate = "2019-03-10 11:30:45";
		String mustPayDate = "2019-02-10 11:30:45";
		String actualPayDate = nullTime;
		//		String graceDate = addOneMonth("2019-07-17 11:30:45");
		Integer overdueDay = 0;


		//初始化ofc表数据
		String userId = "a8399587b89848b8844fe6b24549b347";
		String userEntryJson = "{\"extend\":{\"EXTEND_USER_TYPE\":\"B\",\"EXTEND_NAME\":\"李盟珍\",\"EXTEND_51GJJ_ORDERSN\":\"\",\"EXTEND_ID_TYPE\":\"身份证\",\"EXTEND_PRODUCT_RATE\":\"A\",\"EXTEND_COMPANY_PROVINCE_CODE\":\"110000\",\"EXTEND_CONCACT_TWO_NAME\":\"Hh\",\"EXTEND_ID_NO\":\"130723198801142542\",\"EXTEND_COMPANY_AREA_CODE\":\"110105\",\"EXTEND_WORK_YEAR\":\"\",\"EXTEND_WORK_START_TIME\":\"\",\"EXTEND_CONCACT_TWO_RELATION\":\"C\",\"EXTEND_MOBILE_NO\":\"18843211101\",\"EXTEND_COMPANY_CONUTY_NAME\":\"国美\",\"EXTEND_COMPANY_ADDRESS\":\"北京市北京市市辖区朝阳区\",\"EXTEND_CONCACT_ONE_RELATION\":\"W\",\"EXTEND_BANK_MOBILE\":\"18101336401\",\"EXTEND_COMPANY_CITY_CODE\":\"110100\",\"EXTEND_RESIDENTIAL_ADDRESS\":\"北京市北京市市辖区东城区鹏润\",\"EXTEND_CONCACT_ONE_MOBILE\":\"13255555555\",\"EXTEND_CERTIFICATE_RESIDENCE_ADDRESS\":\"天津市红桥区勤俭道202号\",\"EXTEND_ZHIMA_CREDIT\":\"0\",\"EXTEND_GENDER\":\"O\",\"EXTEND_ACTIVE_FROM\":\"GMYKBC\",\"EXTEND_CONCACT_ONE_NAME\":\"Jfv\",\"EXTEND_CONCACT_TWO_MOBILE\":\"13562222222\",\"EXTEND_BIRTHDAY\":\"\",\"EXTEND_BANK_CARD_NO\":\"6212260200030140000\"},\"entry\":{\"ENTRY_ABODE_COMPANY_NATURE\":\"L\",\"ENTRY_LINKMAN1_RELATION\":\"W\",\"ENTRY_EMPLOY_START_TIME\":\"\",\"ENTRY_COMPANY_CITY_NAME\":\"北京市市辖区\",\"ENTRY_LINKMAN2_NAME\":\"Hh\",\"ENTRY_COMPANY_CITY\":\"110100\",\"ENTRY_COMPANY_COUNTY_NAME\":\"朝阳区\",\"ENTRY_ABODE_COUNTY\":\"110101\",\"ENTRY_COMPANY_COUNTY\":\"110105\",\"ENTRY_LINKMAN2_RELATION\":\"C\",\"ENTRY_LINKMAN1_PHONE\":\"13255555555\",\"ENTRY_LINKMAN2_RELATION_LEVER\":\"2\",\"ENTRY_MARITAL_STATUS\":\"9\",\"ENTRY_PHONE_OPERATOR_THIRD\":\"3\",\"ENTRY_LINKMAN1_RELATION_LEVER\":\"1\",\"ENTRY_ABODE_CITY_NAME\":\"北京市市辖区\",\"ENTRY_TAOBAO_THIRD\":\"3\",\"ENTRY_COMPANY_TELL\":\"\",\"ENTRY_COMPANY_NAME\":\"国美\",\"ENTRY_LINKMAN2_PHONE\":\"13562222222\",\"ENTRY_ABODE_DETAIL\":\"鹏润\",\"ENTRY_ABODE_CITY\":\"110100\",\"ENTRY_ABODE_PROVINCE_NAME\":\"北京市\",\"ENTRY_ABODE_PROVINCE\":\"110000\",\"ENTRY_COMPANY_TYPE\":\"Q\",\"ENTRY_LINKMAN1_NAME\":\"Jfv\",\"ENTRY_ABODE_COUNTY_NAME\":\"东城区\",\"ENTRY_EDUCATION_BACKGROUND\":\"university\",\"ENTRY_COMPANY_PROVINCE_NAME\":\"北京市\",\"ENTRY_GJJ_THIRD\":\"3\",\"ENTRY_COMPANY_PROVINCE\":\"110000\"},\"initData\":{\"appTerm\":12,\"gpsCity\":\"北京市市辖区\",\"gpsAreaCode\":\"110105\",\"gpsLongitude\":\"116.466574\",\"app_version\":\"3.1.5\",\"buyFlag\":\"0\",\"pdType\":\"T\",\"reqClientType\":\"APP\",\"equipmentId\":\"868800027703309\",\"gpsStateCode\":\"110000\",\"appAmount\":1111,\"loanPurpose\":\"PN04\",\"channelSource\":\"10000000\",\"productId\":\"5551\",\"bankCardNo\":\"6212260200030140000\",\"ip\":\"10.134.39.13\",\"verifyMethod\":0,\"terminalType\":\"APP\",\"market\":\"icash_main\",\"loanTerms\":\"12\",\"gpsCityCode\":\"110100\",\"fromSource\":\"icash_main\",\"verifyType\":\"Y\",\"loanType\":\"004\",\"insurerCode\":\"01\",\"bankCardId\":\"f2c1051968154b359486ef4f8d5e2c40\",\"loanMoney\":\"1111\",\"gpsLatitude\":\"39.958729\",\"gpsState\":\"北京市\",\"isReapplication\":0,\"loanUse\":\"PN04\",\"verifyCode\":\"111864\",\"appPlatform\":\"Android\",\"mobile\":\"18843211101\",\"userId\":\"a8399587b89848b8844fe6b24549b347\",\"app_platform\":\"Android\",\"isMonitor\":\"O\",\"tongdun\":\"eyJvcyI6ImFuZHJvaWQiLCJ2ZXJzaW9uIjoiMy4wLjQiLCJwYWNrYWdlcyI6ImNvbS5nb21lanIuaWNhc2hfMy4xLjUiLCJwcm9maWxlX3RpbWUiOjUxMCwiaW50ZXJ2YWxfdGltZSI6MjQ2MDk0NTcsInRva2VuX2lkIjoidWJQayt5WHgzZllBSDQwSnlaeUdUSElrS0ZGZGVYYk02Ym9lRnhheG8rRjJBV2U2ZjJ6aWtBREJ5RnFFQnNURmdXVENaTTZkeVwvdWxUcXNOUnl6bzJ3PT0ifQ==\",\"applyNo\":\"GMLOC19051614212001006\",\"app_devicetype\":\"icash_main\"}}";
		Integer linkmanId = 1000000000;
		//		String customerId = "null";
		//		String saCode = "null";
		//		String loanApplyNo = "null";

		Integer shortDebitNo = 1000000000;

		StringBuilder sb = new StringBuilder();
		StringBuilder gomeInvestor = new StringBuilder();
		StringBuilder otherInvestor = new StringBuilder();
		StringBuilder ofcSb = new StringBuilder();

		StringBuilder decSb;

		String str = "','";
		String preBecSql = "INSERT INTO `db_gmcf_bec`.`t_bec_bidding` (`f_bidding_no`, `f_bidding_short_no`, `f_bidding_status`, `f_loan_no`, `f_product_name`, `f_product_code`, `f_business_group_code`, `f_product_mirror_no`, `f_product_pricing_no`, `f_product_pricing_mirror_no`, `f_repayment_formula_no`, `f_repayment_formula_name`, `f_bidding_amount`, `f_currency_code`, `f_apply_datetime`, `f_audit_approve_datetime`, `f_full_bidding_datetime`, `f_settle_datetime`, `f_signing_datetime`, `f_deal_datetime`, `f_cancel_datetime`, `f_remain_right_capital_code`, `f_remain_right_owner_no`, `f_clearing_formula_no`, `f_profit_distribution_no`, `f_repayment_allocation_rule_no`, `f_derate_allocation_rule_no`, `f_master_borrower_no`, `f_master_borrower_type`, `f_master_borrower_name`, `f_year_rate`, `f_month_rate`, `f_day_rate`, `f_origin_total_stage`, `f_total_stage`, `f_current_stage`, `f_start_date`, `f_end_date`, `f_payout_bank_card_no`, `f_repayment_bank_card_no`, `f_cooperation_type`, `f_is_overdue`, `f_is_off_balance`, `f_cbd_start_date`, `f_continuous_overdue_stages`, `f_max_continuous_overdue_stages`, `f_age_account`, `f_channel_source`, `f_created_time`, `f_modified_time`, `f_next_must_pay_date`, `f_ext_current_stage`, `f_repayment_status_array`, `f_max_continuous_start_date`, `f_is_guaranteed`, `f_capital_codes`, `f_before_must_pay_date`) VALUES (";

		String preInvSql = "INSERT INTO `db_gmcf_bec`.`t_bec_investor` (`f_bidding_no`, `f_capital_no`, `f_capital_code`, `f_capital_member_no`, `f_capital_mirror_no`, `f_capital_pricing_no`, `f_capital_pricing_mirror_no`, `f_capital_name`, `f_investment_ratio`, `f_investment_amount`, `f_investment_datetime`, `f_is_effective`) VALUES (";

		String preDecSql = "INSERT INTO `db_gmcf_dec`.`t_dec_debit` ( `f_debit_no`, `f_bidding_no`, `f_loan_no`, `f_first_debit_member_no`, `f_first_debit_member_type`, `f_last_debit_member_no`, `f_last_debit_member_type`, `f_current_stage`, `f_debit_status`, `f_must_pay_principal`, `f_actual_pay_principal`, `f_derate_principal`, `f_must_pay_interest`, `f_actual_pay_interest`, `f_derate_interest`, `f_must_pay_penalty_interest`, `f_actual_pay_penalty_interest`, `f_derate_penalty_interest`, `f_must_pay_total_fee`, `f_actual_pay_total_fee`, `f_derate_total_fee`, `f_fee_detail_json`, `f_value_date`, `f_due_date`, `f_must_pay_date`, `f_actual_pay_date`, `f_grace_date`, `f_overdue_day`, `f_short_debit_no`) VALUES (";

		String preOfcSql = "INSERT INTO `db_gmcf_ofc`.`t_ofc_user_entry_info` (`f_user_id`,`f_loan_no`, `f_user_entry_json`, `f_linkman_id`, `f_customer_id`, `f_sa_code`, `f_loan_apply_no`) VALUES (";
		for (int i = 0; i < insertSize; i++) {

			//bec新增数据
			biddingNo = biddingNo + genSimpleUUID();
			loanNo = loanNo + genSimpleUUID();

			sb.append("'").append(biddingNo).append(str)
					.append(shortNo++).append(str)
					.append(biddingStatus).append(str)
					.append(loanNo).append(str)
					.append(productName).append(str)
					.append(productCode).append(str)
					.append(businessGroupCode).append(str)
					.append(productMirrorNo).append(str)
					.append(productPricingNo).append(str)
					.append(productPricingMirrorNo).append(str)
					.append(repaymentFormulaNo).append(str)
					.append(repaymentFormulaName).append(str)
					.append(biddingAmount).append(str)
					.append(currencyCode).append(str)
					.append(applyDateTime).append(str)
					.append(auditApproveDateTime).append(str)
					.append(fullBiddingDatetime).append(str)
					.append(settleDateTime).append(str)
					.append(signingDatetime).append(str)
					.append(dealDatetime).append(str)
					.append(cancelDatetime).append(str)
					.append(remainRightCapitalCode).append(str)
					.append(remainRightOwnerNo).append(str)
					.append(clearingFormulaNo).append(str)
					.append(profitDistributionNo).append(str)
					.append(repaymentAllocationRuleNo).append(str)
					.append(derateAllocationRuleNo).append(str)
					.append(masterBorrowerNo).append(str)
					.append(masterBorrowerType).append(str)
					.append(master_borrower_name).append(str)
					.append(yearRate).append(str)
					.append(monthRate).append(str)
					.append(dayRate).append(str)
					.append(originTotalStage).append(str)
					.append(totalStage).append(str)
					.append(currentStage).append(str)
					.append(startDate).append(str)
					.append(endDate).append(str)
					.append(payoutBankCardNo).append(str)
					.append(repaymentBankCardNo).append(str)
					.append(cooperationType).append(str)
					.append(isOverdue).append(str)
					.append(isOffBalance).append(str)
					.append(cbdStartDate).append(str)
					.append(continuousOverdueStages).append(str)
					.append(maxContinuousOverdueStages).append(str)
					.append(ageAccount).append(str)
					.append(channelSource).append("',")
					.append(createdTime).append(",")
					.append(modifiedTime).append(",'")
					.append(nextMustPayDate).append(str)
					.append(extCurrentStage).append(str)
					.append(repaymentStatusArray).append(str)
					.append(maxContinuousStartDate).append(str)
					.append(isGuaranteed).append(str)
					.append(capitalCodes).append(str)
					.append(beforeMustPayDate).append("'").append(")");

			dealLastMonthList.add(preBecSql + sb);
			sb = new StringBuilder();

			//出资人表新增数据

			//国美方
			gomeInvestor.append("'").append(biddingNo).append(str)
					.append(gomeCapitalNo).append(str)
					.append(gomeCapitalCode).append(str)
					.append(gomeCapitalMemberNo).append(str)
					.append(gomeCapitalMirrorNo).append(str)
					.append(gomeCapitalPricingNo).append(str)
					.append(gomeCapitalPricingMirrorNo).append(str)
					.append(gomeCapitalName).append("',")
					.append(gomeInverstment).append(",")
					.append(gomeInverstmentAmount).append(",'")
					.append(investmentDatetime).append("',")
					.append(isEffective).append(")");

			//其他资方
			otherInvestor.append("'").append(biddingNo).append(str)
					.append(otherCapitalNo).append(str)
					.append(otherCapitalCode).append(str)
					.append(otherCapitalMemberNo).append(str)
					.append(otherCapitalMirrorNo).append(str)
					.append(otherCapitalPricingNo).append(str)
					.append(otherCapitalPricingMirrorNo).append(str)
					.append(otherCapitalName).append("',")
					.append(otherInverstment).append(",")
					.append(otherInverstementAmount).append(",'")
					.append(investmentDatetime).append("',")
					.append(isEffective).append(")");

			dealLastMonthList.add(preInvSql + gomeInvestor);
			dealLastMonthList.add(preInvSql + otherInvestor);

			gomeInvestor = new StringBuilder();
			otherInvestor = new StringBuilder();



			//新增dec数据
			int j = 0;
			for (;j<5;j++){
				decSb = new StringBuilder();
				valueDate = addOneMonth(valueDate);
				dueDate = addOneMonth(dueDate);
				mustPayDate = addOneMonth(mustPayDate);
				if (j == 0){
					actualPayDate = "2019-03-10 00:00:00";
					mustPayInterest = new BigDecimal(13.6);
					actualPayPrincipal = new BigDecimal(1000);
					actualPayInterest = new BigDecimal(13.6);
					debitStatus = 4;
				}else if(j == 1){
					actualPayDate = "2019-04-10 00:00:00";
					mustPayInterest = new BigDecimal(13.6);
					actualPayPrincipal = new BigDecimal(1000);
					actualPayInterest = new BigDecimal(13.6);
					debitStatus = 4;
				}else if(j == 2){
					actualPayDate = "2019-05-10 00:00:00";
					mustPayInterest = new BigDecimal(13.6);
					actualPayPrincipal = new BigDecimal(1000);
					actualPayInterest = new BigDecimal(13.6);
					debitStatus = 4;
				}else if (j == 3){
					actualPayDate = "2019-06-10 00:00:00";
					mustPayInterest = new BigDecimal(13.6);
					actualPayPrincipal = new BigDecimal(1000);
					actualPayInterest = new BigDecimal(13.6);
					debitStatus = 4;
				}else {
					actualPayDate = "2019-07-18 00:00:00";
					mustPayInterest = new BigDecimal(13.6);
					actualPayPrincipal = new BigDecimal(500);
					actualPayInterest = new BigDecimal(13.6);
					debitStatus = 3;
				}

				decSb.append("'").append(debitNo+genSimpleUUID()).append(str)
						.append(biddingNo).append(str)
						.append(loanNo).append(str)
						.append(firstDebitMemberNo).append("',")
						.append(firstDebitMemberType).append(",'")
						.append(lastDebitMemberNo).append("',")
						.append(lastDebitMemberType).append(",")
						.append(decCurrentStage++).append(",")
						.append(debitStatus).append(",")
						.append(mustPayPrincipal).append(",")
						.append(actualPayPrincipal).append(",")
						.append(deratePrincipal).append(",")
						.append(mustPayInterest).append(",")
						.append(actualPayInterest).append(",")
						.append(derateInterest).append(",")
						.append(mustPayPenaltyPnterest).append(",")
						.append(actualPayPenaltyInterest).append(",")
						.append(deratePenaltyInterest).append(",")
						.append(mustPayTotalFee).append(",")
						.append(actualPayTotalFee).append(",")
						.append(derateTotalFee).append(",'")
						.append(feeDetailJson).append(str)
						.append(valueDate).append(str)
						.append(dueDate).append(str)
						.append(mustPayDate).append(str)
						.append(actualPayDate).append(str)
						.append(addThreeDays(mustPayDate)).append("',")
						.append(overdueDay).append(",")
						.append(shortDebitNo++).append(")");

				dealLastMonthList.add(preDecSql+decSb);
			}
			actualPayDate = "0000-00-00 00:00:00";
			mustPayInterest = new BigDecimal(0);
			actualPayPrincipal = new BigDecimal(0);
			actualPayInterest = new BigDecimal(0);
			debitStatus = 1;
			for (; j < 12; j++) {
				decSb = new StringBuilder();
				valueDate = addOneMonth(valueDate);
				dueDate = addOneMonth(dueDate);
				mustPayDate = addOneMonth(mustPayDate);

				decSb.append("'").append(debitNo+genSimpleUUID()).append(str)
						.append(biddingNo).append(str)
						.append(loanNo).append(str)
						.append(firstDebitMemberNo).append("',")
						.append(firstDebitMemberType).append(",'")
						.append(lastDebitMemberNo).append("',")
						.append(lastDebitMemberType).append(",")
						.append(decCurrentStage++).append(",")
						.append(debitStatus).append(",")
						.append(mustPayPrincipal).append(",")
						.append(actualPayPrincipal).append(",")
						.append(deratePrincipal).append(",")
						.append(mustPayInterest).append(",")
						.append(actualPayInterest).append(",")
						.append(derateInterest).append(",")
						.append(mustPayPenaltyPnterest).append(",")
						.append(actualPayPenaltyInterest).append(",")
						.append(deratePenaltyInterest).append(",")
						.append(mustPayTotalFee).append(",")
						.append(actualPayTotalFee).append(",")
						.append(derateTotalFee).append(",'")
						.append(feeDetailJson).append(str)
						.append(valueDate).append(str)
						.append(dueDate).append(str)
						.append(mustPayDate).append(str)
						.append(actualPayDate).append(str)
						.append(addThreeDays(mustPayDate)).append("',")
						.append(overdueDay).append(",")
						.append(shortDebitNo++).append(")");

				dealLastMonthList.add(preDecSql+decSb);
			}
			//新增ofc数据
			ofcSb = new StringBuilder();
			ofcSb.append("'").append(userId).append(str)
					.append(loanNo).append(str)
					.append(userEntryJson).append("',")
					.append(linkmanId++).append(",")
					.append("null").append(",")
					.append("null").append(",")
					.append("null").append(")");

			dealLastMonthList.add(preOfcSql+ofcSb);


			biddingNo = "gmcfbec";
			loanNo = "gmcfloc";
			valueDate = "2019-06-17 11:30:45";
			dueDate = "2019-07-17 11:30:45";
			mustPayDate = "2019-05-15 11:30:45";
			decCurrentStage = 1;
		}
		return dealLastMonthList;
	}
}
