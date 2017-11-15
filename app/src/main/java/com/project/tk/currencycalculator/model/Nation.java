package com.project.tk.currencycalculator.model;

/**
 * Created by conscious on 2017-11-07.
 */

public class Nation {

    public String nation;
    public String currency;
    public String sign;

    public String rate;
    public String value;
    public String updatedTime;

    public Nation() {
    }

    public Nation(String nation, String rate, String value) {
        this.nation = nation;
        this.rate = rate;
        this.value = value;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
