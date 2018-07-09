package com.example.boris.bakingapp;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.boris.bakingapp.mvp.ui.detailedFragment.DetailedFragment;
import com.example.boris.bakingapp.mvp.ui.recipeListFragment.RecipeListFragment;
import com.example.boris.bakingapp.mvp.ui.stepDescriptionFragment.StepDescriptionFragment;

import java.util.ArrayList;

import static com.example.boris.bakingapp.constants.Contract.TAG_WORK_CHECKING;

public class MainActivity600 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main600);
        openFragment();

    }


    public void openFragment() {
        Log.d(TAG_WORK_CHECKING,"NAME IS ---------->" + getIntent().getExtras().getString("name"));
        Bundle bundle = new Bundle();
        bundle.putInt("position", getIntent().getExtras().getInt("position"));
        bundle.putString("image", getIntent().getExtras().getString("image"));
        bundle.putString("name", getIntent().getExtras().getString("name"));
        bundle.putParcelableArrayList("ingredients", new ArrayList<Parcelable>(getIntent().getExtras().getParcelableArrayList("ingredients")));
        bundle.putParcelableArrayList("steps", new ArrayList<Parcelable>(getIntent().getExtras().getParcelableArrayList("steps")));
        DetailedFragment detailedFragment = new DetailedFragment();
        detailedFragment.setArguments(bundle);

        StepDescriptionFragment stepDescriptionFragment =
                StepDescriptionFragment.getInstance(getIntent().getExtras().getParcelableArrayList("steps"), 0);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_frame_list_600_detailed, detailedFragment)
                .add(R.id.main_frame_list_600_step, stepDescriptionFragment)
                .addToBackStack(null)
                .commit();
    }
}
