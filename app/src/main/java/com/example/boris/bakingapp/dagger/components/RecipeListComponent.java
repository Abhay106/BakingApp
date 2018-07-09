package com.example.boris.bakingapp.dagger.components;

import com.example.boris.bakingapp.dagger.modules.RecipeListModule;
import com.example.boris.bakingapp.dagger.scopes.RecipeListScope;
import com.example.boris.bakingapp.mvp.presentation.recipeListFragment.RecipeListPresenter;

import dagger.Component;

@RecipeListScope
@Component(modules = RecipeListModule.class, dependencies = AppComponent.class)
public interface RecipeListComponent {
    void inject(RecipeListPresenter recipeListPresenter);
}
