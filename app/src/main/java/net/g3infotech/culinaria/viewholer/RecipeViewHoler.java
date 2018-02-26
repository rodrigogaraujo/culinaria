package net.g3infotech.culinaria.viewholer;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.g3infotech.culinaria.CookingAppWidget;
import net.g3infotech.culinaria.DetailsRecipeActivity;
import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.entitie.Recipe;
import net.g3infotech.culinaria.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by g3infotech on 04/02/18.
 */

public class RecipeViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_recipe_name)
    public TextView mTvRecipe;
    private Recipe mRecipe;
    private Context mContext;

    public RecipeViewHoler(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void onBind(Recipe recipe) {
        mRecipe = recipe;
        mTvRecipe.setText(mRecipe.getName());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, DetailsRecipeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.SEND_RECIPE, (ArrayList<? extends Parcelable>) mRecipe.getSteps());
        intent.putExtras(bundle);

        Intent intentWidget = new Intent(mContext, CookingAppWidget.class);
        intentWidget.setAction(Constants.ACTION_SHOW_RECIPES);
        bundle.putParcelableArrayList(Constants.SEND_INGREDIENTS, (ArrayList<? extends Parcelable>) mRecipe.getIngredients());
        intentWidget.putExtras(bundle);
        mContext.sendBroadcast(intentWidget);

        mContext.startActivity(intent);
    }
}
