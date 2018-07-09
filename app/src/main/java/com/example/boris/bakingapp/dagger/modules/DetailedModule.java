package com.example.boris.bakingapp.dagger.modules;

import android.content.Context;

import com.example.boris.bakingapp.dagger.scopes.DetailedScope;
import com.example.boris.bakingapp.mvp.model.data.http.ApiService;
import com.example.boris.bakingapp.mvp.model.data.prefs.Prefs;
import com.example.boris.bakingapp.mvp.model.interactor.detailed.DetailedInteractor;
import com.example.boris.bakingapp.mvp.model.repository.recipeListFragment.RecipeListRepository;

import dagger.Module;
import dagger.Provides;

@DetailedScope
@Module
public class DetailedModule {

    @DetailedScope
    @Provides
    public Prefs providePrefs(Context context){
        return new Prefs(context);
    }

    @DetailedScope
    @Provides
    public RecipeListRepository provideRecipeListRepository(ApiService apiService, Prefs prefs){
        return new RecipeListRepository(apiService, prefs);
    }

    @DetailedScope
    @Provides
    public DetailedInteractor provideDetailedInteractor(RecipeListRepository repository){
        return new DetailedInteractor(repository);
    }

}
