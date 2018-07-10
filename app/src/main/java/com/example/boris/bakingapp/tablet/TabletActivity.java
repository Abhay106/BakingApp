package com.example.boris.bakingapp.tablet;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.boris.bakingapp.R;
import com.example.boris.bakingapp.mvp.presentation.detailed.DetailedPresenter;
import com.example.boris.bakingapp.mvp.presentation.tablet.TabletPresenter;
import com.example.boris.bakingapp.mvp.presentation.tablet.TabletView;
import com.example.boris.bakingapp.mvp.ui.detailedFragment.DetailedFragment;
import com.example.boris.bakingapp.mvp.ui.stepDescriptionFragment.StepDescriptionFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.boris.bakingapp.constants.Contract.TAG_WORK_CHECKING;

public class TabletActivity extends MvpAppCompatActivity implements TabletView {

    @BindView(R.id.activity_toolbar_600)
    Toolbar toolbar;

    @InjectPresenter
    TabletPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getExtras().getString("name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        openFragment();

    }


    @Override
    public void openFragment() {
        Log.d(TAG_WORK_CHECKING, "NAME IS ---------->" + getIntent().getExtras().getString("name"));
        Bundle bundle = new Bundle();
        bundle.putInt("position", getIntent().getExtras().getInt("position"));
        bundle.putString("image", getIntent().getExtras().getString("image"));
        bundle.putString("name", getIntent().getExtras().getString("name"));
        bundle.putParcelableArrayList("ingredients", new ArrayList<Parcelable>(getIntent().getExtras().getParcelableArrayList("ingredients")));
        bundle.putParcelableArrayList("steps", new ArrayList<Parcelable>(getIntent().getExtras().getParcelableArrayList("steps")));
        DetailedFragment detailedFragment = new DetailedFragment();
        detailedFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (getResources().getConfiguration().smallestScreenWidthDp >= 600) {
            StepDescriptionFragment stepDescriptionFragment =
                    StepDescriptionFragment.getInstance(getIntent().getExtras().getParcelableArrayList("steps"), 0);
            transaction
                    .replace(R.id.main_frame_list_600_detailed, detailedFragment)
                    .replace(R.id.main_frame_list_600_step, stepDescriptionFragment)
                    .commit();
        } else {
            transaction.replace(R.id.main_frame_list, detailedFragment).addToBackStack(null).commit();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.add_content_to_widget:
                presenter.saveChosenRecipePosition(getIntent().getExtras().getInt("position", 0));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
