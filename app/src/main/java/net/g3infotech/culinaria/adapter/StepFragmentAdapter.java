package net.g3infotech.culinaria.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.g3infotech.culinaria.entitie.Step;
import net.g3infotech.culinaria.fragment.StepFragment;
import net.g3infotech.culinaria.listener.OnStepClickListener;

import java.util.List;

/**
 * Created by g3infotech on 20/02/18.
 */

public class StepFragmentAdapter extends FragmentPagerAdapter {
    List<Step> mSteps;

    public StepFragmentAdapter(FragmentManager fm, List<Step> steps) {
        super(fm);
        mSteps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        return StepFragment.newInstance(mSteps.get(position));
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }
}
