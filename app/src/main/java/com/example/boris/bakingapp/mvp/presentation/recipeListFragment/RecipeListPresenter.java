package com.example.boris.bakingapp.mvp.presentation.recipeListFragment;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.boris.bakingapp.RecipesApplication;
import com.example.boris.bakingapp.constants.Contract;
import com.example.boris.bakingapp.dagger.components.DaggerRecipeListComponent;
import com.example.boris.bakingapp.dagger.modules.RecipeListModule;
import com.example.boris.bakingapp.entity.RecipeModel;
import com.example.boris.bakingapp.mvp.model.interactor.recipeListFragment.RecipeListInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

@InjectViewState
public class RecipeListPresenter extends MvpPresenter<RecipeListView> {

    @Inject
    RecipeListInteractor recipeListInteractor;

    public RecipeListPresenter() {
        DaggerRecipeListComponent.builder()
                .appComponent(RecipesApplication.component)
                .recipeListModule(new RecipeListModule())
                .build()
                .inject(this);
    }

    public void getData() {
        Log.d(Contract.TAG_WORK_CHECKING, "RecipeListPresenter - getData");

        recipeListInteractor.getData().doOnNext(new Consumer<List<RecipeModel>>() {
            @Override
            public void accept(List<RecipeModel> recipeModels) {
                getViewState().setList(recipeModels);
            }
        })
                .subscribe();
    }
}
