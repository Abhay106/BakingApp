package com.example.boris.bakingapp.mvp.presentation.recipeListFragment;

import com.arellomobile.mvp.MvpView;
import com.example.boris.bakingapp.entity.RecipeModel;

import java.util.List;

public interface RecipeListView extends MvpView {

    void setList(List<RecipeModel> modelList);

}
