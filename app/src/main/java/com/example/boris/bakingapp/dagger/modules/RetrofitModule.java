package com.example.boris.bakingapp.dagger.modules;


import com.example.boris.bakingapp.dagger.scopes.AppScope;
import com.example.boris.bakingapp.mvp.model.data.http.ApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@AppScope
@Module
public class RetrofitModule {

    private final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    @AppScope
    @Provides
    public ApiService provideApiService(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }
}
