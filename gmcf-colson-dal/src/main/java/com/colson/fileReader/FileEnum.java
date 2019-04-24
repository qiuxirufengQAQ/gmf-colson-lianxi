package com.colson.fileReader;

public enum FileEnum {
	TRACEID("D:\\cqc_error\\1.sql","D:\\cqc_error\\traceId.sql"),
	PREOCCUPYUSED("D:\\cqc_error\\preOccupyUsed.sql","D:\\cqc_error\\preOccupyUsedOutput.sql"),
	PREOCCUPYRECOVERY("D:\\cqc_error\\preOccupyRecovery.sql","D:\\cqc_error\\preOccupyRecoveryOutput.sql"),
	OTHER("D:\\cqc_error\\other.sql","D:\\cqc_error\\otherResult.sql");


	private String sourceFilePath;
	private String resultFilePath;

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public String getResultFilePath() {
		return resultFilePath;
	}

	public void setResultFilePath(String resultFilePath) {
		this.resultFilePath = resultFilePath;
	}

	FileEnum(String sourceFilePath,String resultFilePath){
		this.sourceFilePath = sourceFilePath;
		this.resultFilePath = resultFilePath;
	}

}
