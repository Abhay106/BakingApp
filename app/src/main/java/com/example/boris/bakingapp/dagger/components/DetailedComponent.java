package com.example.boris.bakingapp.dagger.components;

import com.example.boris.bakingapp.dagger.modules.DetailedModule;
import com.example.boris.bakingapp.dagger.scopes.DetailedScope;
import com.example.boris.bakingapp.mvp.presentation.detailed.DetailedPresenter;
import com.example.boris.bakingapp.mvp.presentation.tablet.TabletPresenter;

import dagger.Component;

@DetailedScope
@Component(modules = DetailedModule.class, dependencies = AppComponent.class)
public interface DetailedComponent {

    void inject(DetailedPresenter presenter);
    void inject(TabletPresenter presenter);

}
