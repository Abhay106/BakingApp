package com.example.boris.bakingapp.mvp.model.interactor.detailed;

import com.example.boris.bakingapp.mvp.model.repository.recipeListFragment.RecipeListRepository;

public class DetailedInteractor {

    RecipeListRepository repository;

    public DetailedInteractor(RecipeListRepository repository) {
        this.repository = repository;
    }

    public void saveChosenRecipePosition(int position){
        repository.saveChosenRecipePosition(position);
    }

}
