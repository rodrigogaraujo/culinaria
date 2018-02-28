package net.g3infotech.culinaria.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.entitie.Ingredient;
import net.g3infotech.culinaria.viewholer.IngredientViewHolder;

import java.util.List;

/**
 * Created by g3infotech on 04/02/18.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientViewHolder> {
    List<Ingredient> mIngredient;
    Context mContext;

    public IngredientAdapter(List<Ingredient> ingredients, Context context) {
        mIngredient = ingredients;
        mContext = context;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new IngredientViewHolder(inflater.inflate(R.layout.ingredient_list, parent, false));
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.onBind(mIngredient.get(position));
    }

    @Override
    public int getItemCount() {
        return mIngredient.size();
    }
}
