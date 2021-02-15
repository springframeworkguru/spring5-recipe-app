package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        return Recipe.builder()
                .id(recipeCommand.getId())
                .description(recipeCommand.getDescription())
                .cookTime(recipeCommand.getCookTime())
                .prepTime(recipeCommand.getPrepTime())
                .servings(recipeCommand.getServings())
                .notes(notesConverter.convert(recipeCommand.getNotes()))
                .categories(recipeCommand.getCategories()
                        .stream()
                        .map(categoryConverter::convert)
                        .collect(Collectors.toSet()))
                .difficulty(recipeCommand.getDifficulty())
                .directions(recipeCommand.getDirections())
                .ingredients(recipeCommand.getIngredients()
                        .stream()
                        .map(ingredientConverter::convert)
                        .collect(Collectors.toSet()))
                .source(recipeCommand.getSource())
                .url(recipeCommand.getUrl())
                .build();
    }
}
