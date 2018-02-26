package net.g3infotech.culinaria.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.entitie.Recipe;
import net.g3infotech.culinaria.viewholer.RecipeViewHoler;

import java.util.List;

/**
 * Created by g3infotech on 04/02/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHoler> {
    List<Recipe> mRecipes;
    Context mContext;

    public RecipeAdapter(List<Recipe> recipes, Context context) {
        mRecipes = recipes;
        mContext = context;
    }

    @Override
    public RecipeViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new RecipeViewHoler(inflater.inflate(R.layout.recipe_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecipeViewHoler holder, int position) {
        holder.onBind(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
}
