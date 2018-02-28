package net.g3infotech.culinaria.viewholer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.entitie.Ingredient;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by g3infotech on 05/02/18.
 */

public class IngredientViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_description_ingredient)
    TextView mTvDescription;

    public IngredientViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void onBind(Ingredient ingredient){
        mTvDescription.setText(ingredient.getIngredient());
    }
}
