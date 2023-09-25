package guru.springframework.converters;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @Author: harshitasain
 * @Date: 24/09/23
 **/
@Component
@AllArgsConstructor
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesToNotesCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    @Override
    @Synchronized
    @Nullable
    public RecipeCommand convert(Recipe source) {
        if( source == null )
            return null;
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficlulty(source.getDifficulty());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
        if( source.getIngredients() != null ){
            source.getIngredients().forEach((ingredient -> {
                recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient));
            }));
        }
        if( source.getCategories() != null ){
            source.getCategories().forEach((category -> {
                recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category));
            }));
        }
        return recipeCommand;
    }
}
