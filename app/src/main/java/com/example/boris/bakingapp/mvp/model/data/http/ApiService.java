package com.example.boris.bakingapp.mvp.model.data.http;

import com.example.boris.bakingapp.entity.RecipeModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<RecipeModel>> getRecipes();
}
