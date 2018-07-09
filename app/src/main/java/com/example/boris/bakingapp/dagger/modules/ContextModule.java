package com.example.boris.bakingapp.dagger.modules;

import android.content.Context;

import com.example.boris.bakingapp.dagger.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@AppScope
@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    public Context provideContext(){
        return context;
    }
}
