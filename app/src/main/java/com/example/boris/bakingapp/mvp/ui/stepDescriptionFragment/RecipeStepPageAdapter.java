package com.example.boris.bakingapp.mvp.ui.stepDescriptionFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.boris.bakingapp.R;
import com.example.boris.bakingapp.entity.Step;

import java.util.List;
import java.util.Locale;

import static com.example.boris.bakingapp.constants.Contract.TAG_VAR_VALUE;

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
        Log.d(TAG_VAR_VALUE, " GET getDescription -> " + steps.get(position).getDescription());
        Log.d(TAG_VAR_VALUE, " GET getThumbnailURL -> " + steps.get(position).getThumbnailURL());
        Log.d(TAG_VAR_VALUE, " GET getVideoURL -> " + steps.get(position).getVideoURL());
        return StepFragment.newInstance(
                steps.get(position).getDescription(),
                steps.get(position).getThumbnailURL(),
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
