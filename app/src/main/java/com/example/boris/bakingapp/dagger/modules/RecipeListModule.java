package com.example.boris.bakingapp.dagger.modules;

import android.content.Context;

import com.example.boris.bakingapp.dagger.scopes.RecipeListScope;
import com.example.boris.bakingapp.mvp.model.data.http.ApiService;
import com.example.boris.bakingapp.mvp.model.interactor.recipeListFragment.RecipeListInteractor;
import com.example.boris.bakingapp.mvp.model.repository.recipeListFragment.RecipeListRepository;
import com.example.boris.bakingapp.mvp.model.data.prefs.Prefs;

import dagger.Module;
import dagger.Provides;

@RecipeListScope
@Module
public class RecipeListModule {

    @RecipeListScope
    @Provides
    public Prefs providePrefs(Context context){
        return new Prefs(context);
    }

    @RecipeListScope
    @Provides
    public RecipeListRepository provideRecipeListRepository(ApiService apiService, Prefs prefs){
        return new RecipeListRepository(apiService, prefs);
    }

    @RecipeListScope
    @Provides
    public RecipeListInteractor provideRecipeListInteractor(RecipeListRepository recipeListRepository){
        return new RecipeListInteractor(recipeListRepository);
    }
}
