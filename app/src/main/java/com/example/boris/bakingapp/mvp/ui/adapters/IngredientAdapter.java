package com.example.boris.bakingapp.mvp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boris.bakingapp.R;
import com.example.boris.bakingapp.entity.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Ingredient> ingredientList;

    public IngredientAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public IngredientsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((IngredientsItemHolder) holder).ingredient.setText(ingredientList.get(position).getIngredient());
        ((IngredientsItemHolder) holder).quantity.setText(String.valueOf(ingredientList.get(position).getQuantity()));
        ((IngredientsItemHolder) holder).measure.setText(ingredientList.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
         return ingredientList.size();
    }

    public static class IngredientsItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_ingredient_ingredient) TextView ingredient;
        @BindView(R.id.item_ingredient_quantity) TextView quantity;
        @BindView(R.id.item_ingredient_measure) TextView measure;

        public IngredientsItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
