package com.project.tk.currencycalculator.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintSet;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.project.tk.currencycalculator.R;
import com.project.tk.currencycalculator.databinding.FragmentMainBinding;
import com.project.tk.currencycalculator.model.Nation;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private FragmentMainBinding b;
    private ConstraintSet set1, set2;
    private Transition transition;
    private MainFragAdapter mAdapter;
    private ArrayList<Nation> items;
    public static int mainPot=10;
    public static String mainValue="20";
    private TextView selectedTv;

    private int no = 0;
//    private View includeView;

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



        set1 = new ConstraintSet();
        set2 = new ConstraintSet();
        set1.clone(b.constraint);
        set2.clone(b.constraint);

        transition = new AutoTransition();
        transition.setDuration(500);
        transition.setInterpolator(new DecelerateInterpolator());

        b.btnTransform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToSet1();
            }
        });
        b.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToBack();
            }
        });


        initData();
    }



    private void initData() {
        items = new ArrayList<>();
        mAdapter = new MainFragAdapter(getContext(),items, new MainFragAdapter.OnTvRateListener() {
            @Override
            public void onTvRateClick(TextView v) {
                selectedTv = v;
            }
        });
        b.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        b.rv.setAdapter(mAdapter);

        for(int i=0;i<9; i++){
            Nation n = new Nation("TTF", "1,789.0"+i,"76,678,440");
            items.add(n);
            mAdapter.notifyDataSetChanged();
        }


    }


    private void changeToSet1() {
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

        set1.setVisibility(R.id.btn, View.VISIBLE);

        set1.applyTo(b.constraint);
    }

    private void changeToBack() {
        TransitionManager.beginDelayedTransition(b.constraint, transition);
        set2.applyTo(b.constraint);
    }

    public void onClickNo(View v){
        switch (v.getId()){
            case R.id.btn_no_1:
                selectedTv.append("1");
                break;
            case R.id.btn_no_2:
                selectedTv.append("2");
                break;
            case R.id.btn_no_3:
                b.tvRate.append("3");
                break;
            case R.id.btn_no_4:
                b.tvRate.append("4");
                break;
            case R.id.btn_no_5:
                b.tvRate.append("5");
                break;
            case R.id.btn_no_6:
                b.tvRate.append("6");
                break;
            case R.id.btn_no_7:
                b.tvRate.append("7");
                break;
            case R.id.btn_no_8:
                b.tvRate.append("8");
                break;
            case R.id.btn_no_9:
                b.tvRate.append("9");
                break;
            case R.id.btn_no_zero:
                b.tvRate.append("0");
                break;
            case R.id.btn_no_dot:
                b.tvRate.append(".");
                break;
            case R.id.btn_no_c:
                break;
            case R.id.btn_no_del:
                break;
        }

    }
}
