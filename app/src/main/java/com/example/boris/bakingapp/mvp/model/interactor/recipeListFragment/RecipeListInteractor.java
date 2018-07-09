package com.example.boris.bakingapp.mvp.model.interactor.recipeListFragment;

import android.util.Log;

import com.example.boris.bakingapp.constants.Contract;
import com.example.boris.bakingapp.entity.RecipeModel;
import com.example.boris.bakingapp.mvp.model.repository.recipeListFragment.RecipeListRepository;

import java.util.List;

import io.reactivex.Observable;

public class RecipeListInteractor {

    RecipeListRepository recipeListRepository;

    public RecipeListInteractor(RecipeListRepository recipeListRepository) {
        this.recipeListRepository = recipeListRepository;
    }

    public Observable<List<RecipeModel>> getData() {
        Log.d(Contract.TAG_WORK_CHECKING, "RecipeListInteractor - getData");

        return recipeListRepository.getData();
    }
}
