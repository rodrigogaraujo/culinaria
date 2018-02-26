package net.g3infotech.culinaria.task;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.g3infotech.culinaria.entitie.Recipe;
import net.g3infotech.culinaria.utils.Constants;
import net.g3infotech.culinaria.utils.Utilities;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by g3infotech on 04/02/18.
 */

public class RecipeTask extends AsyncTask<List<Recipe>, Void, List<Recipe>> {

    private ProgressBar mProgressBar;
    public RecipeTask(ProgressBar progressBar){
        mProgressBar = progressBar;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(mProgressBar != null)
            mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected List<Recipe> doInBackground(List<Recipe>... recipes) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        List<Recipe> recipeFromJson = null;
        try {
            recipeFromJson = gson.fromJson(Utilities.run(Constants.BASE_URL), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipeFromJson;
    }

    @Override
    protected void onPostExecute(List<Recipe> recipe) {
        super.onPostExecute(recipe);
        if(mProgressBar != null)
            mProgressBar.setVisibility(View.INVISIBLE);
    }

}
