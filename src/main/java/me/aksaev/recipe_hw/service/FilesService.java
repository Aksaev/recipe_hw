package me.aksaev.recipe_hw.service;

public interface FilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
