package com.example.boris.bakingapp.mvp.ui.stepDescriptionFragment;


import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.boris.bakingapp.R;
import com.example.boris.bakingapp.entity.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.boris.bakingapp.constants.Contract.TAG_VAR_VALUE;
import static com.example.boris.bakingapp.constants.Contract.TAG_WORK_CHECKING;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDescriptionFragment extends MvpAppCompatFragment {

    public static final String EXTRA_RECIPE_ID = "RECIPE_ID";
    private static final int DEFAULT_RECIPE_ID = 1;

    public static final String EXTRA_STEP_ID = "STEP_ID";
    private static final int DEFAULT_STEP_ID = 0;

    @BindView(R.id.recipe_step_viewpager)
    ViewPager recipeStepViewPager;
    @BindView(R.id.recipe_step_tablayout)
    TabLayout recipeStepTabLayout;

    @BindView(R.id.main_list_toolbar)
    Toolbar toolbar;

    View view;

    private RecipeStepPageAdapter viewPagerAdapter;

    int stepId;

    public StepDescriptionFragment() {
        // Required empty public constructor
    }


    public static StepDescriptionFragment getInstance(int stepId) {

        Bundle arguments = new Bundle();
        arguments.putInt(StepDescriptionFragment.EXTRA_STEP_ID, stepId);
        StepDescriptionFragment fragment = new StepDescriptionFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    public static StepDescriptionFragment getInstance(List<Step> steps, int stepId) {

        Bundle arguments = new Bundle();
        arguments.putInt(StepDescriptionFragment.EXTRA_STEP_ID, stepId);
        arguments.putParcelableArrayList("steps", (ArrayList<? extends Parcelable>) steps);
        StepDescriptionFragment fragment = new StepDescriptionFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int x = getArguments().getInt(StepDescriptionFragment.EXTRA_STEP_ID);
        if (savedInstanceState == null) {
            Log.d(TAG_WORK_CHECKING, "StepDescriptionFragment - onCreate - savedInstanceState == null");
            stepId = getArguments().getInt(StepDescriptionFragment.EXTRA_STEP_ID);
        } else {
            Log.d(TAG_WORK_CHECKING, "StepDescriptionFragment - onCreate - else");
            stepId = savedInstanceState.getInt(StepDescriptionFragment.EXTRA_STEP_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_step_description, container, false);
        ButterKnife.bind(this, view);

        viewPagerAdapter = new RecipeStepPageAdapter(getFragmentManager(), new ArrayList<>(0), getContext());
        recipeStepViewPager.setAdapter(viewPagerAdapter);
        setUpViewPagerListener();
        recipeStepTabLayout.setupWithViewPager(recipeStepViewPager);

        viewPagerAdapter.setSteps(getArguments().getParcelableArrayList("steps"));

        Log.d(TAG_VAR_VALUE, "Step id is " + stepId);
        recipeStepViewPager.setCurrentItem(stepId);

        // Hide tabs on landscape not-twoPane mode
       /* int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE && !isTwoPane) {
            recipeStepTabLayout.setVisibility(View.GONE);
        }*/

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("name"));
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (item.getItemId() == android.R.id.home) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPagerListener() {
        recipeStepViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                stepId = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

}
