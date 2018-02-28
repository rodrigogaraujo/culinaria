package net.g3infotech.culinaria;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import net.g3infotech.culinaria.adapter.StepFragmentAdapter;
import net.g3infotech.culinaria.entitie.Step;
import net.g3infotech.culinaria.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private List<Step> mSteps;
    private int mPosition;
    @BindView(R.id.bt_prev)
    Button mBtnPrev;
    @BindView(R.id.bt_next)
    Button mBtnNext;

    public StepsActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);
        mViewPager = findViewById(R.id.steps_pager);
        if (getIntent().getExtras() != null) {
            mSteps = getIntent().getExtras().getParcelableArrayList(Constants.SEND_STEPS);
            mPosition = getIntent().getExtras().getInt(Constants.SEND_STEP);
            mViewPager.setAdapter(new StepFragmentAdapter(getSupportFragmentManager(), mSteps));
            mViewPager.setCurrentItem(mPosition);
        }
        mBtnNext.setOnClickListener(this);
        mBtnPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mBtnNext.getId()) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        } else if (id == mBtnPrev.getId()) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        }
    }
}
