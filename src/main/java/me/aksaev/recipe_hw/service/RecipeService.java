package me.aksaev.recipe_hw.service;


import me.aksaev.recipe_hw.model.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe add(Recipe recipe);
    Recipe get(long id);
    Recipe update(long id, Recipe recipe);
    Recipe remove(long id);
    List<Recipe> getAll();
}
