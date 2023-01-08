package me.aksaev.recipe_hw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.aksaev.recipe_hw.model.Recipe;
import me.aksaev.recipe_hw.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @Operation(
            summary = "Получение списка всех рецептов",
            description = "Можно получить список всех рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public List<Recipe> getAll() {
        return this.recipeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта по id",
            description = "Можно получить рецепт по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт был найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Recipe getRecipe(@PathVariable("id") long id) {
        return recipeService.get(id);
    }

    @PostMapping
    @Operation(
                summary = "Добавление рецепта",
            description = "Можно добавить рецепт"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getName())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение рецепта по id",
            description = "Можно изменить рецепт по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт был изменен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Recipe updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.update(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта по id",
            description = "Можно удалить рецепт по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт был удален",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }
}