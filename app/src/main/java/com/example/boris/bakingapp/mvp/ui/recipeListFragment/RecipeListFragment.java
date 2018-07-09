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
import com.example.boris.bakingapp.MainActivity600;
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

    @BindView(R.id.recycler_list_recipes) RecyclerView recyclerView;
    @BindView(R.id.main_list_toolbar) Toolbar toolbar;

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

        if(recipeList.isEmpty()) Log.d("Recipes", "The list is empty");

        //Add all data to created list
        listRecipes.addAll(recipeList);



        //Setting data to the adapter
        recipeAdapter.setData(listRecipes);

        //Refreshing UI of the recycler with new data
        recipeAdapter.notifyDataSetChanged();

        for(RecipeModel model: recipeList){
            Log.d("Recipes", model.getName());
            /*for(Step step: model.getSteps()) {
                Log.d("Recipes", step.getShortDescription());
            }*/
        }
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        toolbar.inflateMenu(R.menu.menu_main);
//    }

    private OnItemClickListener.OnItemClickCallback setOnItemClickCallback() {
        OnItemClickListener.OnItemClickCallback onItemClickCallback = new OnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                RecipeModel itemClicked = listRecipes.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("image", itemClicked.getImage());
                bundle.putString("name", itemClicked.getName());
                bundle.putParcelableArrayList("ingredients", new ArrayList<Parcelable>(itemClicked.getIngredients()));
                bundle.putParcelableArrayList("steps", new ArrayList<Parcelable>(itemClicked.getSteps()));
                bundle.putInt("swrvings", itemClicked.getServings());


                Intent dataIntent = new Intent(getContext(), MainActivity600.class);
                dataIntent.putExtras(bundle);
//                dataIntent.putExtra("image", itemClicked.getImage());
//                dataIntent.putExtra("name", itemClicked.getName());
//                dataIntent.putExtra("ingredients", new ArrayList<Parcelable>(itemClicked.getIngredients()));
//                dataIntent.putExtra("steps", new ArrayList<Parcelable>(itemClicked.getSteps()));
                startActivity(dataIntent);


//                DetailedFragment detailedFragment = new DetailedFragment();
//                detailedFragment.setArguments(bundle);

                StepDescriptionFragment stepDescriptionFragment =
                        StepDescriptionFragment.getInstance(itemClicked.getSteps(), 0);

//                if (getResources().getConfiguration().smallestScreenWidthDp >= 600) {
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//
//                    fragmentManager.beginTransaction()
//                            .add(R.id.main_frame_list_600_detailed, detailedFragment)
//                            .add(R.id.main_frame_list_600_step, stepDescriptionFragment)
//                            .addToBackStack(null)
//                            .commit();
//
//                    fragmentManager.beginTransaction()
//                            .addToBackStack(null)
//                            .commit();
//                } else {
//                    Log.d(TAG_WORK_CHECKING, "Something went wrong in DetaideFragment");
//
//                    FragmentManager manager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction transaction = manager.beginTransaction();
//                    transaction.addToBackStack(null);
//                    transaction.replace(R.id.main_frame_list_600_detailed, detailedFragment, "detailed_fragment_tag");
//                    transaction.commit();
//                }
            }
        };
        return onItemClickCallback;
    }
    @Override
    public void onItemClick(View view, int position) {

    }
}

