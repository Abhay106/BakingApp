package com.example.boris.bakingapp.utils;

import android.util.Log;

import com.example.boris.bakingapp.entity.Ingredient;
import com.example.boris.bakingapp.mvp.model.repository.recipeListFragment.RecipeListRepository;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public class WidgetDataHelper {

    private RecipeListRepository repository;

    public WidgetDataHelper(RecipeListRepository repository) {
        this.repository = repository;
    }

    public Set<String> getRecipeNamesFromPrefs() {
        return repository.getRecipeNamesList();
    }

    public Observable<List<Ingredient>> getIngredientsList(int position){
        return repository.getIngredients(position);
    }

    public int getChosenRecipePosition(){
        Log.d("CheckWidget", "In WidgetDataHelper");
        int position = repository.getChosenRecipePosition();
        Log.d("CheckWidget", "In WidgetDataHelper and position is " + position);
        return position;
    }

    public void deleteRecipeFromPrefs(int widgetId) {
        repository.deleteRecipeName(widgetId);
    }

    public void saveRecipeNameToPrefs(int appWidgetId, String name) {
        repository.saveChosenRecipeName(appWidgetId, name);
    }

    public String getRecipeNameFromPrefs(int appWidgetId) {
        return repository.getChosenRecipeName(appWidgetId);
    }

}
