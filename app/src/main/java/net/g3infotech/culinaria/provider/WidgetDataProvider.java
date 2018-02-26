package net.g3infotech.culinaria.provider;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import net.g3infotech.culinaria.entitie.Ingredient;
import net.g3infotech.culinaria.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g3infotech on 23/02/18.
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory{
    List<Ingredient> mIngredients;
    Context mContext;
    Intent mIntent;
    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;
    }

    @Override
    public void onCreate() {
        getData();
    }

    private void getData() {
        mIngredients =  new ArrayList<>();
        if(mIntent.hasExtra(Constants.SEND_INGREDIENTS)) {
            mIngredients = mIntent.getExtras().getParcelableArrayList(Constants.SEND_INGREDIENTS);
        }
    }

    @Override
    public void onDataSetChanged() {
        getData();
    }

    @Override
    public void onDestroy() {
        mIngredients = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), android.R.layout.simple_list_item_1);
        if(mIngredients.size() > 0) {
            remoteViews.setTextViewText(android.R.id.text1, mIngredients.get(position).getIngredient());
        }
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
