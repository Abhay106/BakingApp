package com.example.boris.bakingapp.mvp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boris.bakingapp.OnItemClickListener;
import com.example.boris.bakingapp.R;
import com.example.boris.bakingapp.constants.Contract;
import com.example.boris.bakingapp.entity.Ingredient;
import com.example.boris.bakingapp.entity.RecipeModel;
import com.example.boris.bakingapp.entity.Step;

import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.boris.bakingapp.constants.Contract.TAG_WORK_CHECKING;

public class StepsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Step> stepsList;

    private RecipeAdapter.ItemClickListener mClickListener;
    OnItemClickListener.OnItemClickCallback onItemClickCallback;

    public StepsAdapter(OnItemClickListener.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setData(List<Step> stepsList) {
        this.stepsList = stepsList;
    }


    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_steps, parent, false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((StepsViewHolder) holder).step.setText(stepNumber(position) + " step ");
        ((StepsViewHolder) holder).shortDescription.setText(String.valueOf(stepsList.get(position).getShortDescription()));
        ((StepsViewHolder) holder).shortDescription.setOnClickListener(new OnItemClickListener(position, onItemClickCallback));
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }

    private String stepNumber(int position) {
        int i = (Integer.parseInt(String.valueOf(stepsList.get(position).getId())) + 1);
        return String.valueOf(i);
    }

    public static class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_steps_step)
        TextView step;
        @BindView(R.id.item_steps_short)
        TextView shortDescription;

        public StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG_WORK_CHECKING, "StepsAdapter - onItemClicked working well");

        }
    }

    public void setClickListener(RecipeAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
