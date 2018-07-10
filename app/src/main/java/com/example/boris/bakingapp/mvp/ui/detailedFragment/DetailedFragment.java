package com.example.boris.bakingapp.mvp.ui.detailedFragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.boris.bakingapp.OnItemClickListener;
import com.example.boris.bakingapp.R;
import com.example.boris.bakingapp.entity.Ingredient;
import com.example.boris.bakingapp.entity.Step;
import com.example.boris.bakingapp.mvp.presentation.detailed.DetailedPresenter;
import com.example.boris.bakingapp.mvp.presentation.detailed.DetailedView;
import com.example.boris.bakingapp.mvp.ui.adapters.IngredientAdapter;
import com.example.boris.bakingapp.mvp.ui.adapters.StepsAdapter;
import com.example.boris.bakingapp.mvp.ui.stepDescriptionFragment.StepDescriptionFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.boris.bakingapp.constants.Contract.TAG_VAR_VALUE;
import static com.example.boris.bakingapp.constants.Contract.TAG_WORK_CHECKING;
import static com.example.boris.bakingapp.mvp.ui.stepDescriptionFragment.StepDescriptionFragment.EXTRA_STEP_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedFragment extends MvpAppCompatFragment implements StepsAdapter.ItemClickListener, DetailedView {

    @BindView(R.id.fragment_detailed_tv_title)
    TextView name;
    @BindView(R.id.recycler_list_ingredients)
    RecyclerView recyclerIngredients;
    @BindView(R.id.recycler_list_steps)
    RecyclerView recyclerSteps;

    Toolbar toolbar;

    @InjectPresenter
    DetailedPresenter detailedPresenter;

    IngredientAdapter ingredientAdapter;
    StepsAdapter stepsAdapter;
    List<Ingredient> ingredientsList;
    List<Step> stepsList;

    View view;

    public DetailedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG_WORK_CHECKING, "DetailedFragment - onCreateView ");

        view = inflater.inflate(R.layout.fragment_detailed, container, false);
        ButterKnife.bind(this, view);


        if (getResources().getConfiguration().smallestScreenWidthDp < 600) {
            toolbar = (Toolbar) view.findViewById(R.id.fragment_detailed_toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("name"));
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            setHasOptionsMenu(true);
        }


        /**
         * Ingredients List
         */
        recyclerIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));
        ingredientsList = new ArrayList<>();
        ArrayList<Ingredient> listIngredients = (ArrayList<Ingredient>) getArguments().getSerializable("ingredients");
        ingredientsList.addAll(listIngredients);
        ingredientAdapter = new IngredientAdapter(ingredientsList);
        recyclerIngredients.setAdapter(ingredientAdapter);

        /**
         * Steps List
         */
        stepsAdapter = new StepsAdapter(setOnItemClickCallback());
        recyclerSteps.setLayoutManager(new LinearLayoutManager(getActivity()));
        stepsList = new ArrayList<>();
        ArrayList<Step> listSteps = (ArrayList<Step>) getArguments().getSerializable("steps");
        stepsList.addAll(listSteps);
        stepsAdapter.setData(stepsList);
        stepsAdapter.notifyDataSetChanged();
        recyclerSteps.setAdapter(stepsAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        toolbar.inflateMenu(R.menu.menu_main);
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
            case R.id.add_content_to_widget:
                detailedPresenter.saveChosenRecipePosition(getArguments().getInt("position"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private OnItemClickListener.OnItemClickCallback setOnItemClickCallback() {
        OnItemClickListener.OnItemClickCallback onItemClickCallback = new OnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Step itemClicked = stepsList.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("steps", new ArrayList<Parcelable>(getArguments().getParcelableArrayList("steps")));
                bundle.putString("description", itemClicked.getDescription());
                bundle.putString("short_description", itemClicked.getShortDescription());
                bundle.putString("videoURL", itemClicked.getVideoURL());
                bundle.putString("thumbnailURL", itemClicked.getThumbnailURL());
                bundle.putInt(EXTRA_STEP_ID, position);
                bundle.putString("name", getArguments().getString("name"));
                StepDescriptionFragment stepDescriptionFragment = StepDescriptionFragment.getInstance(0);
                stepDescriptionFragment.setArguments(bundle);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                if (getResources().getConfiguration().smallestScreenWidthDp >= 600) {
                    transaction.replace(R.id.main_frame_list_600_step, stepDescriptionFragment, "detailed_fragment_tag");
                } else {
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.main_frame_list, stepDescriptionFragment);
                }
                transaction.commit();

            }
        };
        return onItemClickCallback;
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
