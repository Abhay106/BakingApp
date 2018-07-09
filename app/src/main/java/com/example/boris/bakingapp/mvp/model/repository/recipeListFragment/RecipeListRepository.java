package com.example.boris.bakingapp.mvp.model.repository.recipeListFragment;

import com.example.boris.bakingapp.entity.Ingredient;
import com.example.boris.bakingapp.entity.RecipeModel;
import com.example.boris.bakingapp.mvp.model.data.http.ApiService;
import com.example.boris.bakingapp.mvp.model.data.prefs.Prefs;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipeListRepository {
    ApiService apiService;
    Prefs prefs;


    public RecipeListRepository(ApiService apiService, Prefs prefs) {
        this.apiService = apiService;
        this.prefs = prefs;
    }

    public Observable<List<RecipeModel>> getData() {
        return apiService.getRecipes()
                .doOnNext(models -> prefs.saveRecipesNameList(models))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Ingredient>> getIngredients(int position){
        return apiService.getRecipes()
                .map(list -> list.get(position).getIngredients())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public String getChosenRecipeName(int keySuffix){
        return prefs.getChosenRecipeName(keySuffix);
    }

    public void saveChosenRecipeName(int keySuffix, String name){
        prefs.saveChosenRecipeName(keySuffix, name);
    }

    public void saveChosenRecipePosition(int position){
        prefs.saveChosenRecipePosition(position);
    }

    public int getChosenRecipePosition(){
        return prefs.getChosenRecipePosition();
    }

    public void deleteRecipeName(int keySuffix){
        prefs.deleteRecipeName(keySuffix);
    }

    public Set<String> getRecipeNamesList(){
        return prefs.getRecipesNameList();
    }

}

