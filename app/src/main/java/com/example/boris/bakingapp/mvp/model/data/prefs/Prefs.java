package com.example.boris.bakingapp.mvp.model.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.boris.bakingapp.entity.RecipeModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prefs {

    private SharedPreferences prefs;

    public Prefs(Context context) {
        prefs = context.getSharedPreferences(Key.PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Class for keeping all the keys in one place for prefs
     */

    public class Key {
        public static final String RECIPE_NAME_KEY = "recipe_name_key";
        public static final String RECIPE_POSITION_KEY = "recipe_position_key";
        public static final String NAMES_KEY = "names_key";
        public static final String PREFS_FILE_NAME = "baking_app";
    }

    public void saveRecipesNameList(List<RecipeModel> recipes) {

        Set<String> values = new HashSet<>();

        for (RecipeModel recipe : recipes) {
            values.add(recipe.getName());
        }

        prefs.edit().putStringSet(Key.NAMES_KEY, values).apply();
    }

    public Set<String> getRecipesNameList() {
        return prefs.getStringSet(Key.NAMES_KEY, new HashSet<String>(0));
    }

    public void saveChosenRecipePosition(int recipePosition){
        prefs.edit().putInt(Key.RECIPE_POSITION_KEY, recipePosition).apply();
    }

    public int getChosenRecipePosition(){
        return prefs.getInt(Key.RECIPE_POSITION_KEY, 0);
    }

    public String getChosenRecipeName(int keySuffix) {
        return prefs.getString(Key.RECIPE_NAME_KEY + keySuffix, "");
    }

    public void saveChosenRecipeName(int keySuffix, String name) {
        prefs.edit().putString(Key.RECIPE_NAME_KEY + keySuffix, name).apply();
    }

    public void deleteRecipeName(int keySuffix) {
        prefs.edit().remove(Key.RECIPE_NAME_KEY + keySuffix).apply();
    }

}
