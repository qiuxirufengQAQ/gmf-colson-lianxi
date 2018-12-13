package com.colson.dal;

import java.io.Serializable;

public class FeeDto implements Serializable{
    private String profitRate;
    private String comprehensiveRate;

    public String getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(String profitRate) {
        this.profitRate = profitRate;
    }

    public String getComprehensiveRate() {
        return comprehensiveRate;
    }

    public void setComprehensiveRate(String comprehensiveRate) {
        this.comprehensiveRate = comprehensiveRate;
    }

    @Override
    public String toString() {
        return "FeeDto{" +
                "profitRate='" + profitRate + '\'' +
                ", comprehensiveRate='" + comprehensiveRate + '\'' +
                '}';
    }
}
