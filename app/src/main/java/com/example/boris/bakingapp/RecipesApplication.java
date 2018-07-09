package com.example.boris.bakingapp;

import android.app.Application;

import com.example.boris.bakingapp.dagger.components.AppComponent;
import com.example.boris.bakingapp.dagger.components.DaggerAppComponent;
import com.example.boris.bakingapp.dagger.modules.ContextModule;
import com.example.boris.bakingapp.dagger.modules.RetrofitModule;

public class RecipesApplication extends Application {
    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildAppComponent();
    }

    public AppComponent buildAppComponent(){
        return DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
    }
}
