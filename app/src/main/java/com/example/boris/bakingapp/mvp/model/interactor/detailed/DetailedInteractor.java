package com.example.boris.bakingapp.mvp.model.interactor.detailed;

import android.util.Log;

import com.example.boris.bakingapp.mvp.model.repository.recipeListFragment.RecipeListRepository;

public class DetailedInteractor {

    RecipeListRepository repository;

    public DetailedInteractor(RecipeListRepository repository) {
        this.repository = repository;
    }

    public void saveChosenRecipePosition(int position){
        Log.d("CheckWidget", "In DetailedInteractor");
        repository.saveChosenRecipePosition(position);
    }

}
