package com.example.boris.bakingapp.mvp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boris.bakingapp.OnItemClickListener;
import com.example.boris.bakingapp.R;
import com.example.boris.bakingapp.constants.Contract;
import com.example.boris.bakingapp.entity.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipesViewHolder> {

    private List<RecipeModel> recipesList = new ArrayList<>();
    private ItemClickListener mClickListener;
    OnItemClickListener.OnItemClickCallback onItemClickCallback;

    public RecipeAdapter(OnItemClickListener.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setData(List<RecipeModel> recipesList){
        this.recipesList = recipesList;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipesViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        holder.nameRecipe.setText(recipesList.get(position).getName());
        holder.nameRecipe.setOnClickListener(new OnItemClickListener(position, onItemClickCallback));
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameRecipe;

        public RecipesViewHolder(View itemView) {
            super(itemView);
            nameRecipe = (TextView) itemView.findViewById(R.id.item_recipe_name);
        }

        @Override
        public void onClick(View v) {
            Log.d(Contract.TAG_WORK_CHECKING, "RecipeAdapter - onClick");
        }
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
