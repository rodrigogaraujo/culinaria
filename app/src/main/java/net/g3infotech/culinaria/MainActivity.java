package net.g3infotech.culinaria;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;


import com.google.android.exoplayer2.util.Util;

import net.g3infotech.culinaria.adapter.RecipeAdapter;
import net.g3infotech.culinaria.service.task.RecipeTask;
import net.g3infotech.culinaria.utils.RecipeIdlingResource;
import net.g3infotech.culinaria.utils.Utilities;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerRecipe;
    private ProgressBar mPbRecipe;

    @Nullable
    private RecipeIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {

        if (mIdlingResource == null) {
            mIdlingResource = new RecipeIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPbRecipe = findViewById(R.id.pb_recipe);
        mRecyclerRecipe = findViewById(R.id.rv_recipe);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerRecipe.setHasFixedSize(true);
        mRecyclerRecipe.setLayoutManager(manager);

        try {
            if(Utilities.isConected(this)) {
                RecipeTask task = new RecipeTask(mPbRecipe);
                RecipeAdapter adapter = new RecipeAdapter(task.execute().get(), this);
                mRecyclerRecipe.setAdapter(adapter);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
