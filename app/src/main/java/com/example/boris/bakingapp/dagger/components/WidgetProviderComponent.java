package com.example.boris.bakingapp.dagger.components;

import com.example.boris.bakingapp.RecipesWidgetProvider;
import com.example.boris.bakingapp.dagger.modules.WidgetProviderModule;
import com.example.boris.bakingapp.dagger.scopes.WidgetProviderScope;

import dagger.Component;

@WidgetProviderScope
@Component(modules = WidgetProviderModule.class, dependencies = AppComponent.class)
public interface WidgetProviderComponent {

    void inject(RecipesWidgetProvider recipesWidgetProvider);

}
