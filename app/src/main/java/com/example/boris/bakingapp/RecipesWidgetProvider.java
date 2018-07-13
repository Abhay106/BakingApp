package com.example.boris.bakingapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.boris.bakingapp.dagger.components.DaggerWidgetProviderComponent;
import com.example.boris.bakingapp.dagger.modules.WidgetProviderModule;
import com.example.boris.bakingapp.entity.Ingredient;
import com.example.boris.bakingapp.utils.StringUtils;
import com.example.boris.bakingapp.utils.WidgetDataHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Implementation of App Widget functionality.
 */
public class RecipesWidgetProvider extends AppWidgetProvider {

    @Inject
    WidgetDataHelper widgetDataHelper;

    PendingIntent service;

    public RecipesWidgetProvider() {
        super();
        DaggerWidgetProviderComponent.builder()
                .appComponent(RecipesApplication.component)
                .widgetProviderModule(new WidgetProviderModule())
                .build()
                .inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(context, RecipeWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        if(service == null){
            service = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        }

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
                86400000, service);

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            widgetDataHelper.deleteRecipeFromPrefs(appWidgetId);
        }
    }

}

