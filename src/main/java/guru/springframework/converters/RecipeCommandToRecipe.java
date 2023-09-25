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
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesCommandToNotes;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;


    @Override
    @Synchronized
    @Nullable
    public Recipe convert(RecipeCommand source) {
        if( source == null ){
            return null;
        }
        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficlulty());
        recipe.setDescription(source.getDescription());
        recipe.setServings(source.getServings());
        recipe.setUrl(source.getUrl());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setSource(source.getSource());
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
        if( source.getCategories() != null ){
            source.getCategories().forEach((categoryCommand -> {
                recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand));
            }));
        }
        if(source.getIngredients() != null){
            source.getIngredients().forEach((ingredientCommand -> {
                recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
            }));
        }
        return recipe;
    }
}
