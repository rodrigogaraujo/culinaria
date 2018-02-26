package net.g3infotech.culinaria.viewholer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.StepsActivity;
import net.g3infotech.culinaria.entitie.Step;
import net.g3infotech.culinaria.listener.OnStepClickListener;
import net.g3infotech.culinaria.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by g3infotech on 04/02/18.
 */

public class StepViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_description_step)
    TextView mTvDescription;
    private Step mStep;
    private Context mContext;
    private List<Step> mSteps;
    private int mPosition;
    private OnStepClickListener mCallback;

    public StepViewHoler(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mContext = itemView.getContext();
    }

    public void onBind(List<Step> steps, int position) {
        mSteps = steps;
        mPosition = position;
        mStep = mSteps.get(position);
        mTvDescription.setText(mStep.getShortDescription());
    }

    public void setStepClickListener(OnStepClickListener listener){
        mCallback = listener;
    }

    @Override
    public void onClick(View v) {
        /*Intent intent = new Intent(mContext, StepsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.SEND_STEPS, (ArrayList<? extends Parcelable>) mSteps);
        bundle.putInt(Constants.SEND_POSITION, mPosition);
        intent.putExtras(bundle);
        mContext.startActivity(intent);*/
        mCallback.onStepSelected(mStep);
    }
}
