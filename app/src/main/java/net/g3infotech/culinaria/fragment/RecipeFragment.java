package net.g3infotech.culinaria.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.adapter.RecipeAdapter;
import net.g3infotech.culinaria.entitie.Recipe;
import net.g3infotech.culinaria.listener.OnStepClickListener;
import net.g3infotech.culinaria.task.RecipeTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {

    private RecyclerView mRecyclerRecipe;
    private ProgressBar mPbRecipe;

    public RecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        mPbRecipe = view.findViewById(R.id.pb_recipe);
        mRecyclerRecipe = view.findViewById(R.id.rv_recipe);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerRecipe.setHasFixedSize(true);
        mRecyclerRecipe.setLayoutManager(manager);

        try {
            RecipeTask task = new RecipeTask(mPbRecipe);
            RecipeAdapter adapter = new RecipeAdapter(task.execute().get(), getContext());
            mRecyclerRecipe.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        return view;
    }

}
