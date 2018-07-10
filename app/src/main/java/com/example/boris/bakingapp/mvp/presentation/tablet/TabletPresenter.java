package com.example.boris.bakingapp.mvp.presentation.tablet;

import android.util.Log;

import com.arellomobile.mvp.MvpPresenter;
import com.example.boris.bakingapp.RecipesApplication;
import com.example.boris.bakingapp.dagger.components.DaggerDetailedComponent;
import com.example.boris.bakingapp.dagger.modules.DetailedModule;
import com.example.boris.bakingapp.mvp.model.interactor.detailed.DetailedInteractor;

import javax.inject.Inject;

public class TabletPresenter extends MvpPresenter<TabletView> {

    @Inject
    DetailedInteractor interactor;

    public TabletPresenter() {
        DaggerDetailedComponent.builder()
                .appComponent(RecipesApplication.component)
                .detailedModule(new DetailedModule())
                .build()
                .inject(this);
    }

    public void saveChosenRecipePosition(int position){
        Log.d("CheckWidget", "In presenter" + position);
        interactor.saveChosenRecipePosition(position);
    }

}
