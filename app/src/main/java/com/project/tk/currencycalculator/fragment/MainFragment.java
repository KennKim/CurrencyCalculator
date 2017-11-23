package com.project.tk.currencycalculator.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintSet;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.project.tk.currencycalculator.R;
import com.project.tk.currencycalculator.databinding.FragmentMainBinding;
import com.project.tk.currencycalculator.model.Nation;
import com.project.tk.currencycalculator.util.Cal;
import com.project.tk.currencycalculator.util.MyParse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.project.tk.currencycalculator.util.Cal.toDecimal;

public class MainFragment extends Fragment {

    private FragmentMainBinding b;
    private ConstraintSet set1, set2;
    private Transition transition;
    private MainFragAdapter mAdapter;
    public static ArrayList<Nation> items;
    private Retrofit retrofit;
    private MyParse myParse;

    public static final int ToRate = 111;
    public static final int FromRate = 222;
    public static final int FromValue = 333;
    public static String mainValue;
    private int currentPosition;
    public static int prePosition = ToRate;
    public static TextView currentFromTv;
    public Nation nTo =new Nation();
    public Nation nFrom =new Nation();

    public static final String[] ISO = {"AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","BTN","BWP","BYN","BZD","CAD","CDF","CHF","CLP","CNY","COP","CRC","CUC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","EGP","ERN","ETB","EUR","FJD","FKP","GBP","GEL","GGP","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","IMP","INR","IQD","IRR","ISK","JEP","JMD","JOD","JPY","KES","KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO","MUR","MVR","MWK","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SLL","SOS","SPL","SRD","STD","SVC","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD","TVD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","WST","XAF","XAG","XAU","XCD","XDR","XOF","XPD","XPF","XPT","YER","ZAR","ZMW","ZWD"};
    public static final String[] NATION = {"arab_emirates","afghanistan","albania","armenia","any_nation","angola","argentina","australia","aruba","azerbaijan","bosnia_and_herzegovina","barbados","bangladesh","bulgaria","bahrain","burundi","bermuda","brunei","bolivia","brazil","bahamas","bhutan","botswana","belarus","belize","canada","congo","switzerland","chile","china","colombia","costa_rica","cuba","cuba","cape_verde","czech_republic","djibouti","denmark","dominican_republic","algeria","egypt","eritrea","ethiopia","european_union","fiji","falkland_islands","united_kingdom","georgia","guernsey","ghana","gibraltar","gambia","guinea","guatemala","guyana","hong_kong","honduras","croatia","haiti","hungary","indonesia","israel","isle_of_man","india","iraq","iran","iceland","jersey","jamaica","jordan","japan","kenya","kyrgyzstan","cambodia","comoros","korea_north","korea_south","kuwait","cayman_islands","kazakhstan","laos","lebanon","sri_lanka","liberia","lesotho","libya","morocco","moldova","madagascar","macedonia","myanmar","mongolia","macao","mauritania","mauritius","maldives","malawi","mexico","malaysia","mozambique","namibia","nigeria","nicaragua","norway","nepal","new_zealand","oman","panama","peru","papua_new_guinea","philippines","pakistan","poland","paraguay","qatar","romania","serbia","russia","rwanda","saudi_arabia","solomon_islands","seychelles","sudan","sweden","singapore","saint_helena","sierra_leone","somalia","seborga","suriname","sao_tome_and_principe","el_salvador","syria","swaziland","thailand","tajikistan","turkmenistan","tunisia","tonga","turkey","trinidad_and_tobago","tuvalu","taiwan","tanzania","ukraine","uganda","united_states_of_america","uruguay","uzbekistan","venezuela","vietnam","vanuatu","samoa","any_nation","any_nation","any_nation","any_nation","any_nation","any_nation","any_nation","any_nation","any_nation","yemen","south_africa","zambia","zimbabwe"};
    public static final String[] CURRENCY = {"United Arab Emirates dirham","Afghan afghani","Albanian lek","Armenian dram","Dutch Guilder","Angolan kwanza","Argentine peso","Australian dollar","Aruban florin","Azerbaijani manat","Bosnian Convertible Marka","Barbados dollar","Bangladeshi taka","Bulgarian lev","Bahraini dinar","Burundian franc","Bermudian dollar","Brunei dollar","Boliviano","Brazilian real","Bahamian dollar","Bhutanese ngultrum","Botswana pula","Belarusian ruble","Belize dollar","Canadian dollar","Congolese franc","Swiss franc","Chilean peso","Chinese yuan","Colombian peso","Costa Rican colon","Cuban convertible peso","Cuban peso","Cape Verde escudo","Czech koruna","Djiboutian franc","Danish krone","Dominican peso","Algerian dinar","Egyptian pound","Eritrean nakfa","Ethiopian birr","Euro","Fiji dollar","Falkland Islands pound","Pound sterling","Georgian lari","Guernsey Pound","Ghanaian cedi","Gibraltar pound","Gambian dalasi","Guinean franc","Guatemalan quetzal","Guyanese dollar","Hong Kong dollar","Honduran lempira","Croatian kuna","Haitian gourde","Hungarian forint","Indonesian rupiah","Israeli new shekel","Isle of Man Pound","Indian rupee","Iraqi dinar","Iranian rial","Icelandic króna","Jersey Pound","Jamaican dollar","Jordanian dinar","Japanese yen","Kenyan shilling","Kyrgyzstani som","Cambodian riel","Comoro franc","North Korean won","South Korean won","Kuwaiti dinar","Cayman Islands dollar","Kazakhstani tenge","Lao kip","Lebanese pound","Sri Lankan rupee","Liberian dollar","Lesotho loti","Libyan dinar","Moroccan dirham","Moldovan leu","Malagasy ariary","Macedonian denar","Myanmar kyat","Mongolian tögrög","Macanese pataca","Mauritanian ouguiya","Mauritian rupee","Maldivian rufiyaa","Malawian kwacha","Mexican peso","Malaysian ringgit","Mozambican metical","Namibian dollar","Nigerian naira","Nicaraguan córdoba","Norwegian krone","Nepalese rupee","New Zealand dollar","Omani rial","Panamanian balboa","Peruvian Sol","Papua New Guinean kina","Philippine piso","Pakistani rupee","Polish złoty","Paraguayan guaraní","Qatari riyal","Romanian leu","Serbian dinar","Russian ruble","Rwandan franc","Saudi riyal","Solomon Islands dollar","Seychelles rupee","Sudanese pound","Swedish krona/kronor","Singapore dollar","Saint Helena pound","Sierra Leonean leone","Somali shilling","Seborgan Luigino","Surinamese dollar","São Tomé and Príncipe dobra","Salvadoran colón","Syrian pound","Swazi lilangeni","Thai baht","Tajikistani somoni","Turkmenistan manat","Tunisian dinar","Tongan paʻanga","Turkish lira","Trinidad and Tobago dollar","Tuvaluan Dollar","New Taiwan dollar","Tanzanian shilling","Ukrainian hryvnia","Ugandan shilling","United States dollar","Uruguayan peso","Uzbekistan som","Venezuelan bolívar","Vietnamese đồng","Vanuatu vatu","Samoan tala","CFA franc BEAC","Silver (one troy ounce)","Gold (one troy ounce)","East Caribbean dollar","Special drawing rights","CFA franc BCEAO","Palladium (one troy ounce)","CFP franc (franc Pacifique)","Platinum (one troy ounce)","Yemeni rial","South African rand","Zambian kwacha","Zimbabwean Dollar"};
    public static final int[] IMG = {R.drawable.na_arab_emirates,R.drawable.na_afghanistan,R.drawable.na_albania,R.drawable.na_armenia,R.drawable.na_any_nation,R.drawable.na_angola,R.drawable.na_argentina,R.drawable.na_australia,R.drawable.na_aruba,R.drawable.na_azerbaijan,R.drawable.na_bosnia_and_herzegovina,R.drawable.na_barbados,R.drawable.na_bangladesh,R.drawable.na_bulgaria,R.drawable.na_bahrain,R.drawable.na_burundi,R.drawable.na_bermuda,R.drawable.na_brunei,R.drawable.na_bolivia,R.drawable.na_brazil,R.drawable.na_bahamas,R.drawable.na_bhutan,R.drawable.na_botswana,R.drawable.na_belarus,R.drawable.na_belize,R.drawable.na_canada,R.drawable.na_congo,R.drawable.na_switzerland,R.drawable.na_chile,R.drawable.na_china,R.drawable.na_colombia,R.drawable.na_costa_rica,R.drawable.na_cuba,R.drawable.na_cuba,R.drawable.na_cape_verde,R.drawable.na_czech_republic,R.drawable.na_djibouti,R.drawable.na_denmark,R.drawable.na_dominican_republic,R.drawable.na_algeria,R.drawable.na_egypt,R.drawable.na_eritrea,R.drawable.na_ethiopia,R.drawable.na_european_union,R.drawable.na_fiji,R.drawable.na_falkland_islands,R.drawable.na_united_kingdom,R.drawable.na_georgia,R.drawable.na_guernsey,R.drawable.na_ghana,R.drawable.na_gibraltar,R.drawable.na_gambia,R.drawable.na_guinea,R.drawable.na_guatemala,R.drawable.na_guyana,R.drawable.na_hong_kong,R.drawable.na_honduras,R.drawable.na_croatia,R.drawable.na_haiti,R.drawable.na_hungary,R.drawable.na_indonesia,R.drawable.na_israel,R.drawable.na_isle_of_man,R.drawable.na_india,R.drawable.na_iraq,R.drawable.na_iran,R.drawable.na_iceland,R.drawable.na_jersey,R.drawable.na_jamaica,R.drawable.na_jordan,R.drawable.na_japan,R.drawable.na_kenya,R.drawable.na_kyrgyzstan,R.drawable.na_cambodia,R.drawable.na_comoros,R.drawable.na_korea_north,R.drawable.na_korea_south,R.drawable.na_kuwait,R.drawable.na_cayman_islands,R.drawable.na_kazakhstan,R.drawable.na_laos,R.drawable.na_lebanon,R.drawable.na_sri_lanka,R.drawable.na_liberia,R.drawable.na_lesotho,R.drawable.na_libya,R.drawable.na_morocco,R.drawable.na_moldova,R.drawable.na_madagascar,R.drawable.na_macedonia,R.drawable.na_myanmar,R.drawable.na_mongolia,R.drawable.na_macao,R.drawable.na_mauritania,R.drawable.na_mauritius,R.drawable.na_maldives,R.drawable.na_malawi,R.drawable.na_mexico,R.drawable.na_malaysia,R.drawable.na_mozambique,R.drawable.na_namibia,R.drawable.na_nigeria,R.drawable.na_nicaragua,R.drawable.na_norway,R.drawable.na_nepal,R.drawable.na_new_zealand,R.drawable.na_oman,R.drawable.na_panama,R.drawable.na_peru,R.drawable.na_papua_new_guinea,R.drawable.na_philippines,R.drawable.na_pakistan,R.drawable.na_poland,R.drawable.na_paraguay,R.drawable.na_qatar,R.drawable.na_romania,R.drawable.na_serbia,R.drawable.na_russia,R.drawable.na_rwanda,R.drawable.na_saudi_arabia,R.drawable.na_solomon_islands,R.drawable.na_seychelles,R.drawable.na_sudan,R.drawable.na_sweden,R.drawable.na_singapore,R.drawable.na_saint_helena,R.drawable.na_sierra_leone,R.drawable.na_somalia,R.drawable.na_seborga,R.drawable.na_suriname,R.drawable.na_sao_tome_and_principe,R.drawable.na_el_salvador,R.drawable.na_syria,R.drawable.na_swaziland,R.drawable.na_thailand,R.drawable.na_tajikistan,R.drawable.na_turkmenistan,R.drawable.na_tunisia,R.drawable.na_tonga,R.drawable.na_turkey,R.drawable.na_trinidad_and_tobago,R.drawable.na_tuvalu,R.drawable.na_taiwan,R.drawable.na_tanzania,R.drawable.na_ukraine,R.drawable.na_uganda,R.drawable.na_united_states_of_america,R.drawable.na_uruguay,R.drawable.na_uzbekistan,R.drawable.na_venezuela,R.drawable.na_vietnam,R.drawable.na_vanuatu,R.drawable.na_samoa,R.drawable.na_any_nation,R.drawable.na_any_nation,R.drawable.na_any_nation,R.drawable.na_any_nation,R.drawable.na_any_nation,R.drawable.na_any_nation,R.drawable.na_any_nation,R.drawable.na_any_nation,R.drawable.na_any_nation,R.drawable.na_yemen,R.drawable.na_south_africa,R.drawable.na_zambia,R.drawable.na_zimbabwe};


    private MainFragAdapter.OnTvRateListener onTvRateListener = new MainFragAdapter.OnTvRateListener() {
        @Override
        public void onTvRateClick(int position) {
            currentPosition = position;
        }
    };

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        b = DataBindingUtil.bind(getView());
        b.setFragment(this);



        retrofit = new Retrofit.Builder()
                .baseUrl(MyParse.MY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myParse = retrofit.create(MyParse.class);

        initView();
        initData();
    }

    private void initView() {

        b.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="zzz...";




                String iso ="";


























                Log.i("mymymy",result);
            }
        });

        set1 = new ConstraintSet();
        set2 = new ConstraintSet();
        set1.clone(b.constraint);
        set2.clone(b.constraint);

        transition = new AutoTransition();
        transition.setDuration(200);
        transition.setInterpolator(new DecelerateInterpolator());


        items = new ArrayList<>();
        mAdapter = new MainFragAdapter(getContext(), onTvRateListener);
        b.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        b.rv.setAdapter(mAdapter);


        mainValue = b.tvFromValue.getText().toString();

        getRateBy("usd", "krw");

    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            Nation n = new Nation();
            n.setNation("CAC");
            n.setRateData("1232");
            n.setRate("7777");
            n.setBack(Nation.BACK_NORMAL);
            items.add(n);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void getRateBy(String nationA, String nationB) {

        Call<Nation> call = myParse.getRate(nationA, nationB);
        call.enqueue(new Callback<Nation>() {
            @Override
            public void onResponse(Call<Nation> call, Response<Nation> response) {
                Nation n = response.body();
                b.tvTest.setText(n.uTc);

            }

            @Override
            public void onFailure(Call<Nation> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(), R.string.try_later, Toast.LENGTH_LONG).show();

            }
        });


    }

    public void changeToSet1(View v) {
        TransitionManager.beginDelayedTransition(b.constraint, transition);

        set1.setMargin(R.id.rv, ConstraintSet.START, 0);
        set1.setMargin(R.id.rv, ConstraintSet.END, 0);
        set1.setMargin(R.id.rv, ConstraintSet.TOP, 0);
        set1.setMargin(R.id.rv, ConstraintSet.BOTTOM, 0);
//        set1.constrainHeight(R.id.include, ConstraintSet.MATCH_CONSTRAINT);

        set1.centerHorizontally(R.id.rv, R.id.constraint);
//                    set1.centerVertically(R.id.con1, R.id.main);

        set1.clear(R.id.layout_no);
        set1.connect(R.id.layout_no, ConstraintSet.LEFT, R.id.constraint, ConstraintSet.LEFT);
        set1.connect(R.id.layout_no, ConstraintSet.RIGHT, R.id.constraint, ConstraintSet.RIGHT);
        set1.connect(R.id.layout_no, ConstraintSet.BOTTOM, R.id.constraint, ConstraintSet.BOTTOM);

        set1.setVisibility(R.id.btn_trans_back, View.VISIBLE);

        set1.applyTo(b.constraint);
    }

    public void changeToBack(View v) {
        TransitionManager.beginDelayedTransition(b.constraint, transition);
        set2.applyTo(b.constraint);
    }


    // prePosition 값을 이용하여 현재의 TV를 알려주는 메서드
    public static int whichOne() {
        int result = 0;
        if (prePosition < 100)
            result = ToRate;
        else if (prePosition == FromRate)
            result = FromRate;
        else if (prePosition == FromValue)
            result = FromValue;
        return result;
    }


    // From Nation의 background 변경되는 부분----start
    public void onClickFromTv(View v) {

        switch (whichOne()) {
            case ToRate:
                Nation preNation = items.get(prePosition);
                if (preNation.back == Nation.BACK_FOCUS) {
                    preNation.setBack(Nation.BACK_MODIFY);
                    items.set(prePosition, preNation);
                    mAdapter.notifyItemChanged(prePosition);
                }
                break;
            case FromRate:
            case FromValue:
                currentFromTv.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_tv_normal));
        }

        if (v.getId() == R.id.tv_from_rate) {
            b.tvFromRate.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_tv_focus));
            prePosition = FromRate;
        } else if (v.getId() == R.id.tv_from_value) {
            b.tvFromValue.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.selector_tv_focus));
            prePosition = FromValue;
        }
        nTo.setFirstChar(true);
        currentFromTv = (TextView) v;

    }
    // From Nation의 background 변경되는 부분----end


    public void onClickNo(View v) {
        String digit = "";
        switch (v.getId()) {
            case R.id.btn_no_1:
                digit = "1";
                break;
            case R.id.btn_no_2:
                digit = "2";
                break;
            case R.id.btn_no_3:
                digit = "3";
                break;
            case R.id.btn_no_4:
                digit = "4";
                break;
            case R.id.btn_no_5:
                digit = "5";
                break;
            case R.id.btn_no_6:
                digit = "6";
                break;
            case R.id.btn_no_7:
                digit = "7";
                break;
            case R.id.btn_no_8:
                digit = "8";
                break;
            case R.id.btn_no_9:
                digit = "9";
                break;
            case R.id.btn_no_zero:
                digit = "0";
                onClickZeroOrDot(digit);
                return;
            case R.id.btn_no_dot:
                digit = ".";
                onClickZeroOrDot(digit);
                return;

        }

        switch (whichOne()) {
            case ToRate:
                writeToRate(digit);
                break;
            case FromRate:
                writeFromRate(digit);
                break;
            case FromValue:
                writeFromValue(digit);
                break;
        }
    }

    private void onClickZeroOrDot(String digit) {
        switch (whichOne()) {
            case ToRate:
                nTo = items.get(currentPosition);
                if (digit.equals("0")) {
                    if (nTo.isFirstChar()) {
                        nTo.setRate(digit);
                        nTo.setFirstChar(false);
                    } else {
                        if (nTo.getRate().equals("0") || nTo.getRate().indexOf("0") == 0) {
                            //nothing to do.
                            return;
                        } else {
                            nTo.setRate(toDecimal(nTo.rate + digit));
                        }
                    }
                    Cal.calculate(nTo.rate, mainValue);
                    nTo.setValue(Cal.value);
                    nTo.setValuePoint(Cal.valuePoint);
                    items.set(currentPosition, nTo);
                    mAdapter.notifyItemChanged(currentPosition);

                } else if (digit.equals(".")) {
                    if (!nTo.getRate().contains(".")) {
                        if (nTo.isFirstChar()) {
                            nTo.setRate("0.");
                            nTo.setFirstChar(false);
                        } else {
                            nTo.setRate(toDecimal(nTo.rate + digit));
                        }
                        Cal.calculate(nTo.rate, mainValue);
                        nTo.setValue(Cal.value);
                        nTo.setValuePoint(Cal.valuePoint);
                        items.set(currentPosition, nTo);
                        mAdapter.notifyItemChanged(currentPosition);
                        return;
                    }
                }

                break;
            case FromRate:
                nFrom.setRate(Cal.toDecimal(currentFromTv.getText().toString()));
                if (digit.equals("0")) {
                    if (nFrom.isFirstChar()) {
                        nFrom.setRate(digit);
                        nFrom.setFirstChar(false);
                    } else {
                        if (nFrom.getRate().equals("0") || nFrom.getRate().indexOf("0") == 0) {
                            //nothing to do.
                            changeAllRate("1");
                            return;
                        } else {
                            nFrom.setRate(toDecimal(nFrom.rate + digit));
                        }
                    }
                    currentFromTv.setText(nFrom.rate);
                    changeAllRate(nFrom.rate);

                } else if (digit.equals(".")) {
                    if (!nFrom.getRate().contains(".")) {
                        if (nFrom.isFirstChar()) {
                            nFrom.setRate("0.");
                            nFrom.setFirstChar(false);
                        } else {
                            nFrom.setRate(toDecimal(nFrom.rate + digit));
                        }
                        currentFromTv.setText(nFrom.rate);
                    }
                }
                break;
            case FromValue:
                nFrom.setEtc(Cal.toDecimal(currentFromTv.getText().toString()));
                if (digit.equals("0")) {
                    if (nFrom.isFirstChar()) {
                        nFrom.setEtc(digit);
                        nFrom.setFirstChar(false);
                    } else {
                        if (nFrom.getEtc().equals("0") || nFrom.getEtc().indexOf("0") == 0) {
                            //nothing to do.
                            changeAllRate("1");
                            return;
                        } else {
                            nFrom.setEtc(toDecimal(nFrom.etc + digit));
                        }
                    }
                    currentFromTv.setText(nFrom.etc);
                    mainValue = nFrom.etc;
                    changeAllValue();
                } else if (digit.equals(".")) {
                    if (!nFrom.getEtc().contains(".")) {
                        if (nFrom.isFirstChar()) {
                            nFrom.setEtc("0.");
                            nFrom.setFirstChar(false);
                        } else {
                            nFrom.setEtc(toDecimal(nFrom.etc + digit));
                        }
                        currentFromTv.setText(nFrom.etc);
                    }
                }
        }
    }

    public void onClickCorD(View v) {
        switch (whichOne()) {
            case ToRate:
                nTo = items.get(currentPosition);
                nTo.setFirstChar(false);
                if (v.getId() == R.id.btn_no_c) {
                    nTo.setRate("0");
                    nTo.setValue("0.");
                    nTo.setValuePoint("0000");
                } else if (v.getId() == R.id.btn_no_del) {
                    nTo.setRate(nTo.getRate().substring(0, nTo.getRate().length() - 1));
                    if (TextUtils.isEmpty(nTo.getRate())) {
                        nTo.setRate("0");
                    }
                    Cal.calculate(nTo.rate, mainValue);
                    nTo.setValue(Cal.value);
                    nTo.setValuePoint(Cal.valuePoint);
                }
                items.set(currentPosition, nTo);
                mAdapter.notifyItemChanged(currentPosition);
                break;
            case FromRate:
                nFrom.setRate(Cal.toDecimal(currentFromTv.getText().toString()));
                nFrom.setFirstChar(false);
                if (v.getId() == R.id.btn_no_c) {
                    nFrom.setRate(Cal.toDecimal("0"));
                    currentFromTv.setText(nFrom.rate);
                    changeAllRate("1");
                } else if (v.getId() == R.id.btn_no_del) {
                    nFrom.setRate(Cal.toDecimal(nFrom.getRate().substring(0, nFrom.getRate().length() - 1)));
                    if (TextUtils.isEmpty(nFrom.getRate())) {
                        nFrom.setRate(Cal.toDecimal("0"));
                    }
                    currentFromTv.setText(nFrom.rate);
                    changeAllRate(nFrom.rate);
                }
                break;
            case FromValue:
                nFrom.setEtc(Cal.toDecimal(currentFromTv.getText().toString()));
                nFrom.setFirstChar(false);
                if (v.getId() == R.id.btn_no_c) {
                    nFrom.setEtc(Cal.toDecimal("0"));
                    currentFromTv.setText(nFrom.etc);
                    mainValue = nFrom.etc;
                    changeAllValue();
                } else if (v.getId() == R.id.btn_no_del) {
                    nFrom.setEtc(nFrom.getEtc().substring(0, nFrom.getEtc().length() - 1));
                    if (TextUtils.isEmpty(nFrom.getEtc())) {
                        nFrom.setEtc(Cal.toDecimal("0"));
                    }
                    currentFromTv.setText(nFrom.etc);
                    mainValue = nFrom.etc;
                    changeAllValue();
                }

        }
    }

    private void writeToRate(String digit) {

        nTo = items.get(currentPosition);

        if (nTo.isFirstChar()) {
            nTo.setRate(digit);
            nTo.setFirstChar(false);
        } else {
            nTo.setRate(toDecimal(nTo.rate + digit));
        }

        if (nTo.getRate().length() > 7) {
            Toast.makeText(getContext().getApplicationContext(), R.string.no_anymore, Toast.LENGTH_LONG).show();
            return;
        }
        Cal.calculate(nTo.rate, mainValue);
        nTo.setValue(Cal.value);
        nTo.setValuePoint(Cal.valuePoint);
        items.set(currentPosition, nTo);
        mAdapter.notifyItemChanged(currentPosition);

    }

    private void writeFromRate(String digit) {

        nFrom.setRate(Cal.toDecimal(currentFromTv.getText().toString()));

        if (nFrom.isFirstChar()) {
            nFrom.setRate(digit);
            nFrom.setFirstChar(false);
        } else {
            nFrom.setRate(toDecimal(nFrom.rate + digit));
        }

        if (nFrom.getRate().length() > 8) {
            Toast.makeText(getContext().getApplicationContext(), R.string.no_anymore, Toast.LENGTH_LONG).show();
            return;
        }
        currentFromTv.setText(nFrom.rate);
        changeAllRate(nFrom.rate);

    }

    private void writeFromValue(String digit) {
        nFrom.setEtc(Cal.toDecimal(currentFromTv.getText().toString()));

        if (nFrom.isFirstChar()) {
            nFrom.setEtc(digit);
            nFrom.setFirstChar(false);
        } else {
            nFrom.setEtc(toDecimal(nFrom.etc + digit));
        }

        currentFromTv.setText(nFrom.etc);
        mainValue = nFrom.etc;
        changeAllValue();
    }

    private void changeAllRate(String fromRate) {

        for (Nation nation : items) {
            nation.setRate(Cal.calculateForRate(nation.rateData, fromRate));

            Cal.calculate(nation.rate, mainValue);
            nation.setValue(Cal.value);
            nation.setValuePoint(Cal.valuePoint);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void changeAllValue() {

//        int index =0;
        for (Nation nation : items) {
            Cal.calculate(nation.rate, mainValue);
            nation.setValue(Cal.value);
            nation.setValuePoint(Cal.valuePoint);
//            mAdapter.notifyItemChanged(index);
//            index++;
        }
        mAdapter.notifyDataSetChanged();
    }
}
