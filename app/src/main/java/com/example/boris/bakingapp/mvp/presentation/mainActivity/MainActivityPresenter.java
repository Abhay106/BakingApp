package com.example.boris.bakingapp.mvp.presentation.mainActivity;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.boris.bakingapp.constants.Contract;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView>{

    public void openFragment() {
        Log.d(Contract.TAG_WORK_CHECKING, "MainActivityPresenter - openFragment");
        getViewState().openFragment();
    }
}
