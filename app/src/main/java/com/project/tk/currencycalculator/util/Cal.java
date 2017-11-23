package com.project.tk.currencycalculator.util;

import java.text.DecimalFormat;

/**
 * Created by conscious on 2017-11-15.
 */

public class Cal {

    public static String value;
    public static String valuePoint;

    public static void calculate(String a, String b) {
        Double calculate = Double.parseDouble(a.replaceAll(",", "")) * Double.parseDouble(b.replaceAll(",", ""));

        DecimalFormat df = new DecimalFormat("#,##0.####");
        String strDf = df.format(calculate);
        if (strDf.contains(".")) {
        value = strDf.substring(0, strDf.indexOf(".")+1);
            valuePoint = strDf.substring(strDf.indexOf(".")+1);
        } else {
            value = strDf;
            valuePoint = ".0000";
        }

    }

    // Nation에 들어가기 위한 Decimal 작업.
    public static String toDecimal(String s) {
        String result = "z";
        DecimalFormat df = new DecimalFormat("#,##0.####");
        if (s.contains(".")) {
            String pre = s.substring(0, s.indexOf("."));
            String post = s.substring(s.indexOf("."));
            result = df.format(Double.parseDouble(pre.replaceAll(",", ""))) + post;
        } else {
            result = df.format(Double.parseDouble(s.replaceAll(",", "")));
        }
        return result;
    }

    // fromRate변경으로 인한 Items내의 모든 toRate와 toValue의 값 변경.
    public static String calculateForRate(String a, String b) {
        String result = "z";
        Double calculate = Double.parseDouble(a.replaceAll(",", "")) * Double.parseDouble(b.replaceAll(",", ""));

        DecimalFormat df = new DecimalFormat("#,##0.##");
        String strDf = df.format(calculate);
        if (strDf.startsWith("0")) {
            df.applyPattern("#,##0.####");
            result = df.format(calculate);
        }else{
            result = strDf;
        }
        return result;

    }


//
//    public static String calculate(String a, String b) {
//        return toDecimalFromDouble((toDouble(a)) * (toDouble(b)));
//    }

   /* private static Double toDouble(String s) {
        return Double.parseDouble(s.replaceAll(",", ""));
    }

    private static String toDecimalFromDouble(Double s) {
        DecimalFormat df = new DecimalFormat("#,##0.0000");
        return df.format(s);
    }


    public static String toDecimal(String s) {
        DecimalFormat df = new DecimalFormat("#,##0.0000");
        return df.format(toDouble(s));
    }*/

    // TvToValue에만 적용되는 DecimalFormat




















  /*  public static Nation calculate(String a, String b) {
        Double.parseDouble(s.replaceAll(",", ""));
        Double calculate = Double.parseDouble(a) * Double.parseDouble(b);

        DecimalFormat df = new DecimalFormat("#,##0.####");
        String strDf = df.format(calculate);
        Nation nTo = new Nation();
        if (strDf.contains(".")) {
            nTo.setValue(strDf.substring(0, strDf.indexOf(".")));
            nTo.setValuePoint(strDf.substring(strDf.indexOf(".")));
        } else {
            nTo.setValue(strDf);
        }
        return nTo;
    }

    public static String toDecimal(String s) {
        String result = "0";
        DecimalFormat df = new DecimalFormat("#,##0");
        if (s.contains(".")) {
            String pre = s.substring(0, s.indexOf("."));
            String post = s.substring(s.indexOf("."));
            result = df.format(Double.parseDouble(pre)) + post;
        } else {
            result = df.format(Double.parseDouble(s));
        }
        return result;
    }*/
}
