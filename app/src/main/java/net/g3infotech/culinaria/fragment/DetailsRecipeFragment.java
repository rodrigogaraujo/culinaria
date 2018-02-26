package net.g3infotech.culinaria.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.adapter.StepAdapter;
import net.g3infotech.culinaria.entitie.Step;
import net.g3infotech.culinaria.listener.OnStepClickListener;
import net.g3infotech.culinaria.utils.Constants;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsRecipeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private OnStepClickListener mCallback;
    private List<Step> steps;

    public DetailsRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_recipe, container, false);
        mRecyclerView = view.findViewById(R.id.rv_steps);

        steps = getActivity().getIntent().getExtras().getParcelableArrayList(Constants.SEND_RECIPE);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);

        StepAdapter adapter = new StepAdapter(steps, getContext());
        mRecyclerView.setAdapter(adapter);
        adapter.setOnStepClickListener(new OnStepClickListener() {
            @Override
            public void onStepSelected(Step step) {
                mCallback.onStepSelected(step);
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (OnStepClickListener) context;
        }catch (Exception e){

        }
    }
}
