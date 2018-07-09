package com.example.boris.bakingapp.mvp.ui.stepDescriptionFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.boris.bakingapp.R;
import com.example.boris.bakingapp.entity.Step;

import java.util.List;
import java.util.Locale;

public class RecipeStepPageAdapter extends FragmentStatePagerAdapter {

    private List<Step> steps;
    private String tabTitle;

    public RecipeStepPageAdapter(FragmentManager fm, List<Step> steps, Context context) {
        super(fm);
        setSteps(steps);
        tabTitle = context.getResources().getString(R.string.recipe_step_tab_label);
    }

    public void setSteps(@NonNull List<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return StepFragment.newInstance(
                steps.get(position).getDescription(),
                steps.get(position).getVideoURL()
        );
    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.format(Locale.US, tabTitle, position+1);
    }
}
