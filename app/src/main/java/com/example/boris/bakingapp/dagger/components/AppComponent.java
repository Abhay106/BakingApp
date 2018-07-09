package com.example.boris.bakingapp.dagger.components;

import android.content.Context;

import com.example.boris.bakingapp.dagger.modules.ContextModule;
import com.example.boris.bakingapp.dagger.modules.RetrofitModule;
import com.example.boris.bakingapp.dagger.scopes.AppScope;
import com.example.boris.bakingapp.mvp.model.data.http.ApiService;

import dagger.Component;

@AppScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface AppComponent {
    Context getContext();
    ApiService getApiService();
}
