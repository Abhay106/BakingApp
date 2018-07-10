package com.example.boris.bakingapp.mvp.ui.recipeListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.boris.bakingapp.tablet.TabletActivity;
import com.example.boris.bakingapp.OnItemClickListener;
import com.example.boris.bakingapp.entity.RecipeModel;
import com.example.boris.bakingapp.R;
import com.example.boris.bakingapp.constants.Contract;
import com.example.boris.bakingapp.mvp.presentation.recipeListFragment.RecipeListPresenter;
import com.example.boris.bakingapp.mvp.presentation.recipeListFragment.RecipeListView;
import com.example.boris.bakingapp.mvp.ui.adapters.RecipeAdapter;
import com.example.boris.bakingapp.mvp.ui.detailedFragment.DetailedFragment;
import com.example.boris.bakingapp.mvp.ui.stepDescriptionFragment.StepDescriptionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.boris.bakingapp.constants.Contract.TAG_WORK_CHECKING;

public class RecipeListFragment extends MvpAppCompatFragment implements RecipeListView, RecipeAdapter.ItemClickListener {

    @InjectPresenter
    RecipeListPresenter recipeListPresenter;

    @BindView(R.id.recycler_list_recipes)
    RecyclerView recyclerView;
    @BindView(R.id.main_list_toolbar)
    Toolbar toolbar;

    View view;
    List<RecipeModel> listRecipes = new ArrayList<>();
    RecipeAdapter recipeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(Contract.TAG_WORK_CHECKING, "RecipeListFragment - onCreateView");

        view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);

        setRetainInstance(true);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recipeAdapter = new RecipeAdapter(setOnItemClickCallback());
        recyclerView.setAdapter(recipeAdapter);

        Log.d(Contract.TAG_WORK_CHECKING, "In onCreate()");

        recipeListPresenter.getData();

        return view;
    }

    @Override
    public void setList(List<RecipeModel> recipeList) {
        Log.d(Contract.TAG_WORK_CHECKING, "In setList");

        listRecipes = new ArrayList<>();

        if (recipeList.isEmpty()) Log.d("Recipes", "The list is empty");

        //Add all data to created list
        listRecipes.addAll(recipeList);


        //Setting data to the adapter
        recipeAdapter.setData(listRecipes);

        //Refreshing UI of the recycler with new data
        recipeAdapter.notifyDataSetChanged();

    }


    private OnItemClickListener.OnItemClickCallback setOnItemClickCallback() {
        OnItemClickListener.OnItemClickCallback onItemClickCallback = new OnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Log.d(TAG_WORK_CHECKING, "In callback");
                RecipeModel itemClicked = listRecipes.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("image", itemClicked.getImage());
                bundle.putString("name", itemClicked.getName());
                bundle.putParcelableArrayList("ingredients", new ArrayList<Parcelable>(itemClicked.getIngredients()));
                bundle.putParcelableArrayList("steps", new ArrayList<Parcelable>(itemClicked.getSteps()));
                bundle.putInt("swrvings", itemClicked.getServings());

                if (getResources().getConfiguration().smallestScreenWidthDp >= 600) {
                    Log.d(TAG_WORK_CHECKING, "It is more than 600 dp");
                    Intent dataIntent = new Intent(getContext(), TabletActivity.class);
                    dataIntent.putExtras(bundle);
                    startActivity(dataIntent);

                } else {

                    DetailedFragment detailedFragment = new DetailedFragment();
                    detailedFragment.setArguments(bundle);

                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.main_frame_list, detailedFragment);
                    transaction.commit();
                }
            }
        };
        return onItemClickCallback;
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}

