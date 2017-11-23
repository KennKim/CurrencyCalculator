package com.project.tk.currencycalculator.model;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.project.tk.currencycalculator.R;

/**
 * Created by conscious on 2017-11-07.
 */

public class Nation {

    public String iso;
    public String nation;
    public String currency;
    public int img;
    public String sign;

    public String rateData;
    public String rate;
    public String value;
    public String valuePoint;
    public String uTc;
    public String serverTime;
    public String etc;

    public boolean firstChar;
    public int back;

    public static final int BACK_NORMAL = 0;
    public static final int BACK_MODIFY = 1;
    public static final int BACK_FOCUS = 2;

    public Nation() {
    }

    public Nation(String nation, String rate, String value) {
        this.nation = nation;
        this.rate = rate;
        this.value = value;
    }


    @BindingAdapter({"android:myback"})
    public static void setBack(TextView tv, int sort) {
        switch (sort) {
            case Nation.BACK_NORMAL:
                tv.setBackgroundColor(ContextCompat.getColor(tv.getContext(), android.R.color.transparent));
                break;
            case Nation.BACK_MODIFY:
                tv.setBackground(ContextCompat.getDrawable(tv.getContext(), R.drawable.selector_tv_modify));
                break;
            case Nation.BACK_FOCUS:
                tv.setBackground(ContextCompat.getDrawable(tv.getContext(), R.drawable.selector_tv_focus));
                break;

        }
    }

    @BindingAdapter({"android:myremove"})
    public static void setRemove(ImageButton btn, int sort) {
        switch (sort) {
            case Nation.BACK_NORMAL:
                btn.setVisibility(View.INVISIBLE);
                break;
            case Nation.BACK_MODIFY:
            case Nation.BACK_FOCUS:
                btn.setVisibility(View.VISIBLE);
        }
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
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

    public String getRateData() {
        return rateData;
    }

    public void setRateData(String rateData) {
        this.rateData = rateData;
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

    public String getValuePoint() {
        return valuePoint;
    }

    public void setValuePoint(String valuePoint) {
        this.valuePoint = valuePoint;
    }

    public String getuTc() {
        return uTc;
    }

    public void setuTc(String uTc) {
        this.uTc = uTc;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public boolean isFirstChar() {
        return firstChar;
    }

    public void setFirstChar(boolean firstChar) {
        this.firstChar = firstChar;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public static int getBackNormal() {
        return BACK_NORMAL;
    }

    public static int getBackModify() {
        return BACK_MODIFY;
    }

    public static int getBackFocus() {
        return BACK_FOCUS;
    }





}
