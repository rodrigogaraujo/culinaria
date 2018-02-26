package net.g3infotech.culinaria;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.RemoteViews;

import net.g3infotech.culinaria.entitie.Ingredient;
import net.g3infotech.culinaria.service.WidgetService;
import net.g3infotech.culinaria.utils.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of App Widget functionality.
 */
public class CookingAppWidget extends AppWidgetProvider {

    private List<Ingredient> mIngredients = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Constants.ACTION_SHOW_RECIPES.equals(intent.getAction())){
            Bundle bundle = intent.getExtras();
            if(bundle.getParcelableArrayList(Constants.SEND_INGREDIENTS) != null) {
                mIngredients = intent.getExtras().getParcelableArrayList(Constants.SEND_INGREDIENTS);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisWidget = new ComponentName(context, CookingAppWidget.class);
                RemoteViews views =  new RemoteViews(context.getPackageName(), R.layout.cooking_app_widget);
                    views.setRemoteAdapter(R.id.widget_list, intent);
                //onUpdate(context, appWidgetManager, appWidgetIds);
                appWidgetManager.updateAppWidget(thisWidget, views);
            }
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views =  new RemoteViews(context.getPackageName(), R.layout.cooking_app_widget);
            Intent widget = new Intent(context, WidgetService.class);

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Constants.SEND_INGREDIENTS, (ArrayList<? extends Parcelable>) mIngredients);
            widget.putExtras(bundle);
            widget.setData(Uri.parse(widget.toUri(Intent.URI_INTENT_SCHEME)));

            views.setRemoteAdapter(R.id.widget_list, widget);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

