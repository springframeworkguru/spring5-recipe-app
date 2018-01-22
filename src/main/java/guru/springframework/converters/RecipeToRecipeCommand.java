package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

    IngredientToIngredientCommand ingredientConverter = new IngredientToIngredientCommand();

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setIngredients();
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes();
        recipeCommand.setCategories(source.);
        return recipeCommand;
    }
}
