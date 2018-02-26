package net.g3infotech.culinaria.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.StepsActivity;
import net.g3infotech.culinaria.entitie.Step;
import net.g3infotech.culinaria.listener.OnStepClickListener;
import net.g3infotech.culinaria.utils.Constants;
import net.g3infotech.culinaria.viewholer.StepViewHoler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g3infotech on 04/02/18.
 */

public class StepAdapter extends RecyclerView.Adapter<StepViewHoler>{
    List<Step> mSteps;
    Context mContext;
    OnStepClickListener mCallback;

    public StepAdapter(List<Step> steps, Context context) {
        mSteps = steps;
        mContext = context;
    }


    public void setOnStepClickListener(OnStepClickListener callback){
        mCallback = callback;
    }

    @Override
    public StepViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new StepViewHoler(inflater.inflate(R.layout.step_list, parent, false));
    }

    @Override
    public void onBindViewHolder(StepViewHoler stepViewHoler, int position) {
        stepViewHoler.onBind(mSteps, position);
        stepViewHoler.setStepClickListener(mCallback);
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }
}
