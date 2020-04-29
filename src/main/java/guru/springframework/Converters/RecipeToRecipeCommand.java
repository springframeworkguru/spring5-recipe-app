package guru.springframework.Converters;


import guru.springframework.command.CategoryCommand;
import guru.springframework.command.IngredientCommand;
import guru.springframework.command.RecipeCommand;
import guru.springframework.models.Category;
import guru.springframework.models.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private CategoryToCategoryCommand categoryToCategoryCommand;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand, IngredientToIngredientCommand ingredientToIngredientCommand, NotesToNotesCommand notesToNotesCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source==null)
            return null;

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setImage(source.getImage());
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));

        Set<CategoryCommand> categoryCommandSet = new HashSet<>();
        recipeCommand.setCategories(categoryCommandSet);;

        Set<IngredientCommand> ingredientCommandSet = new HashSet<>();
        recipeCommand.setIngredients(ingredientCommandSet);;

        if(source.getCategories() != null && source.getCategories().size() > 0){

            source.getCategories()
                    .forEach(category -> {
                        recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category));
                    });
        }

        if(source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> {
                        recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient));
                    });
        }

        return recipeCommand;
    }

}
