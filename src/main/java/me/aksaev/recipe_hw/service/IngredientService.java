package me.aksaev.recipe_hw.service;

import me.aksaev.recipe_hw.model.Ingredient;

public interface IngredientService {
    Ingredient add(Ingredient ingredient);
    Ingredient get(long id);
    Ingredient update(long id, Ingredient ingredient);
    Ingredient remove(long id);
}