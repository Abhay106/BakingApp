package com.example.boris.bakingapp.dagger.modules;

import android.content.Context;

import com.example.boris.bakingapp.dagger.scopes.WidgetProviderScope;
import com.example.boris.bakingapp.mvp.model.data.http.ApiService;
import com.example.boris.bakingapp.mvp.model.repository.recipeListFragment.RecipeListRepository;
import com.example.boris.bakingapp.mvp.model.data.prefs.Prefs;
import com.example.boris.bakingapp.utils.WidgetDataHelper;

import dagger.Module;
import dagger.Provides;

@WidgetProviderScope
@Module
public class WidgetProviderModule {

    @WidgetProviderScope
    @Provides
    public Prefs providePrefs(Context context){
        return new Prefs(context);
    }

    @WidgetProviderScope
    @Provides
    public RecipeListRepository provideRecipeListRepository(ApiService apiService, Prefs prefs){
        return new RecipeListRepository(apiService, prefs);
    }

    @WidgetProviderScope
    @Provides
    public WidgetDataHelper provideWidgetDataHelper(RecipeListRepository recipeListRepository){
        return new WidgetDataHelper(recipeListRepository);
    }

}
