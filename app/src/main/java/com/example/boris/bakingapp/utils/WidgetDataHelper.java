package com.example.boris.bakingapp.utils;

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
        return repository.getChosenRecipePosition();
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
