package com.project.tk.currencycalculator.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.tk.currencycalculator.R;
import com.project.tk.currencycalculator.databinding.LayoutNationBinding;
import com.project.tk.currencycalculator.model.Nation;

import java.util.ArrayList;

/**
 * Created by conscious on 2017-11-14.
 */

public class MainFragAdapter extends RecyclerView.Adapter<MainFragAdapter.ViewHolderMainFrag> {
    interface OnTvRateListener{
        void onTvRateClick(TextView v);
    }

    private OnTvRateListener listener;
    private Context mContext;
    private ArrayList<Nation> items;

    public MainFragAdapter(Context mContext,ArrayList<Nation> items, OnTvRateListener listener) {
        this.mContext = mContext;
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ViewHolderMainFrag onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutNationBinding binding = LayoutNationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolderMainFrag(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ViewHolderMainFrag h, int position) {

        h.b.setNation(items.get(position));

//        setTv2(h);

        h.b.tvRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTvRateClick((TextView) v);
                v.setBackgroundColor(ContextCompat.getColor(mContext.getApplicationContext(), R.color.color_pink));
            }
        });


    }
    public static void setTv2(ViewHolderMainFrag h){
//                h.b.tvValue.setText(Cal.calculate((String) s, MainFragment.mainValue));
//        h.b.tvValue.setText(String.valueOf(Integer.parseInt(h.b.tvRate.getText().toString())*MainFragment.mainPot));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolderMainFrag extends RecyclerView.ViewHolder {

        private LayoutNationBinding b;

        public ViewHolderMainFrag(View itemView) {
            super(itemView);
            b = DataBindingUtil.bind(itemView);
        }
    }
}
