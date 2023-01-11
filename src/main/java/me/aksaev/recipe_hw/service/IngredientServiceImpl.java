package me.aksaev.recipe_hw.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.aksaev.recipe_hw.model.Ingredient;
import me.aksaev.recipe_hw.model.Recipe;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    final private FilesService filesService;
    private Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private long counter = 0;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        saveToFile();
        return ingredientMap.put(this.counter++, ingredient);
    }

    @Override
    public Ingredient get(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            saveToFile();
            return ingredientMap.put(id, ingredient);
        }
        return null;
    }

    @Override
    public Ingredient remove(long id) {
        return ingredientMap.remove(id);
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
