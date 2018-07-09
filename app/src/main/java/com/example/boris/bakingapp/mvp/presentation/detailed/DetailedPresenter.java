package com.example.boris.bakingapp.mvp.presentation.detailed;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.boris.bakingapp.RecipesApplication;
import com.example.boris.bakingapp.dagger.components.DaggerDetailedComponent;
import com.example.boris.bakingapp.dagger.modules.DetailedModule;
import com.example.boris.bakingapp.mvp.model.interactor.detailed.DetailedInteractor;

import javax.inject.Inject;

@InjectViewState
public class DetailedPresenter extends MvpPresenter<DetailedView> {

    @Inject
    DetailedInteractor interactor;

    public DetailedPresenter() {
        DaggerDetailedComponent.builder()
                .appComponent(RecipesApplication.component)
                .detailedModule(new DetailedModule())
                .build()
                .inject(this);
    }

    public void saveChosenRecipePosition(int position){
        interactor.saveChosenRecipePosition(position);
    }

}
