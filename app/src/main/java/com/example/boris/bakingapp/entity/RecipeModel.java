
package com.example.boris.bakingapp.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<com.example.boris.bakingapp.entity.Ingredient> ingredients = null;
    @SerializedName("steps")
    @Expose
    private List<com.example.boris.bakingapp.entity.Step> steps = null;
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<com.example.boris.bakingapp.entity.Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<com.example.boris.bakingapp.entity.Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<com.example.boris.bakingapp.entity.Step> getSteps() {
        return steps;
    }

    public void setSteps(List<com.example.boris.bakingapp.entity.Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
