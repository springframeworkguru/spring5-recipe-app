package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Category;
import guru.springframework.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingConverter,
                                 NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingConverter = ingConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {

        if(recipe == null)
            return null;

        RecipeCommand recipeCommand = new RecipeCommand();
       // recipeCommand.setCategories(categoryConverter.convert(recipe.getCategories()));
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDiffculty(recipe.getDiffculty());
        recipeCommand.setDiretions(recipe.getDiretions());
        recipeCommand.setId(recipe.getId());
        recipeCommand.setImage(recipe.getImage());
       // recipeCommand.setIngredients( recipe.getIngredients());


        recipeCommand.setNotes( notesConverter.convert(recipe.getNotes()));
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories()
                    .forEach((Category category) -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingConverter.convert(ingredient)));
        }

        return recipeCommand;
    }
}
