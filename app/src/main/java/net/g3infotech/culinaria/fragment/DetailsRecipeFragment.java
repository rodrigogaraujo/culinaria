package net.g3infotech.culinaria.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import net.g3infotech.culinaria.IngredientActivity;
import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.adapter.StepAdapter;
import net.g3infotech.culinaria.entitie.Ingredient;
import net.g3infotech.culinaria.entitie.Step;
import net.g3infotech.culinaria.listener.OnStepClickListener;
import net.g3infotech.culinaria.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsRecipeFragment extends Fragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private OnStepClickListener mCallback;
    private List<Step> steps;
    private List<Ingredient> mIngredients;
    @BindView(R.id.bt_ingredients)
    Button mBtIngredients;

    public DetailsRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_recipe, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView = view.findViewById(R.id.rv_steps);

        steps = getActivity().getIntent().getExtras().getParcelableArrayList(Constants.SEND_RECIPE);
        mIngredients = getActivity().getIntent().getExtras().getParcelableArrayList(Constants.SEND_INGREDIENTS);

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
        mBtIngredients.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == mBtIngredients.getId()){
            Intent i = new Intent(getContext(), IngredientActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Constants.SEND_INGREDIENTS, (ArrayList<? extends Parcelable>) mIngredients);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}
