package com.example.boris.bakingapp;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.boris.bakingapp.dagger.components.DaggerWidgetProviderComponent;
import com.example.boris.bakingapp.dagger.modules.WidgetProviderModule;
import com.example.boris.bakingapp.entity.Ingredient;
import com.example.boris.bakingapp.utils.StringUtils;
import com.example.boris.bakingapp.utils.WidgetDataHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class RecipeWidgetService extends Service {

    @Inject
    WidgetDataHelper dataHelper;

    public RecipeWidgetService() {
        DaggerWidgetProviderComponent.builder()
                .appComponent(RecipesApplication.component)
                .widgetProviderModule(new WidgetProviderModule())
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int [] widgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);

        for (int appWidgetId: widgetIds) {
            String recipeName = dataHelper.getRecipeNameFromPrefs(startId);

            dataHelper.getIngredientsList(dataHelper.getChosenRecipePosition())
                    .take(1)
                    .doOnNext(ingredients -> updateAppWidgetContent(getApplicationContext(), manager,
                            appWidgetId, recipeName, ingredients)).subscribe();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void updateAppWidgetContent(Context context, AppWidgetManager appWidgetManager,
                                              int appWidgetId, String recipeName, List<Ingredient> ingredients) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list);
            views.setTextViewText(R.id.widget_recipe_name, recipeName);
            views.removeAllViews(R.id.widget_ingredients_container);

            for (Ingredient ingredient : ingredients) {
                RemoteViews ingredientView = new RemoteViews(context.getPackageName(),
                        R.layout.widget_item);

                String line = StringUtils.formatIngdedient(
                        context, ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getMeasure());

                ingredientView.setTextViewText(R.id.widget_ingredient_name, line);
                views.addView(R.id.widget_ingredients_container, ingredientView);
            }

            appWidgetManager.updateAppWidget(appWidgetId, views);

    }

}
