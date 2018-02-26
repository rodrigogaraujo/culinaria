package net.g3infotech.culinaria.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import net.g3infotech.culinaria.provider.WidgetDataProvider;
/**
 * Created by g3infotech on 23/02/18.
 */

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(getApplicationContext(), intent);
    }
}
