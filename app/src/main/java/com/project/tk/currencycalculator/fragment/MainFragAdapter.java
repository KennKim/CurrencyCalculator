package com.project.tk.currencycalculator.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.tk.currencycalculator.R;
import com.project.tk.currencycalculator.databinding.ViewholderFromNationBinding;
import com.project.tk.currencycalculator.model.Nation;

import static com.project.tk.currencycalculator.fragment.MainFragment.FromRate;
import static com.project.tk.currencycalculator.fragment.MainFragment.FromValue;
import static com.project.tk.currencycalculator.fragment.MainFragment.ToRate;
import static com.project.tk.currencycalculator.fragment.MainFragment.items;
import static com.project.tk.currencycalculator.fragment.MainFragment.currentFromTv;
import static com.project.tk.currencycalculator.fragment.MainFragment.prePosition;
import static com.project.tk.currencycalculator.fragment.MainFragment.whichOne;

/**
 * Created by conscious on 2017-11-14.
 */

public class MainFragAdapter extends RecyclerView.Adapter<MainFragAdapter.ViewHolderMainFrag> {
    interface OnTvRateListener {
        void onTvRateClick(int position);
    }

    private OnTvRateListener listener;
    private Context mContext;

    public MainFragAdapter(Context mContext, OnTvRateListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public ViewHolderMainFrag onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewholderFromNationBinding binding = ViewholderFromNationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolderMainFrag(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ViewHolderMainFrag h, final int position) {

        final Nation n = items.get(position);

        h.b.setNation(n);

        h.b.tvToRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 전에 선택된 Tv의 background를 원래대로 변경-------start
                switch (whichOne()) {
                    case ToRate:
                        if (prePosition != position && prePosition < 100) {
                            Nation preNation = items.get(prePosition);
                            if (items.get(prePosition).back == Nation.BACK_FOCUS) {
                                preNation.setBack(Nation.BACK_MODIFY);
                                items.set(prePosition, preNation);
                                notifyItemChanged(prePosition);
                            }
                        }
                        break;
                    case FromRate:
                    case FromValue:
                        currentFromTv.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.selector_tv_normal));
                }
                // 전에 선택된 Tv의 background를 원래대로 변경-------end

                listener.onTvRateClick(position);

                n.setBack(Nation.BACK_FOCUS);
                n.setFirstChar(true);
                items.set(position, n);
                notifyItemChanged(position);
                prePosition = position;

            }
        });

        h.b.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n.setBack(Nation.BACK_NORMAL);
                items.set(position, n);
                notifyItemChanged(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolderMainFrag extends RecyclerView.ViewHolder {

        private ViewholderFromNationBinding b;

        public ViewHolderMainFrag(View itemView) {
            super(itemView);
            b = DataBindingUtil.bind(itemView);
        }

    }
}
