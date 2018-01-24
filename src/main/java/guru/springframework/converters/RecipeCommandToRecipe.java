package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

  private final CategoryCommandToCategory categoryConverter;
  private final IngredientCommandToIngredient ingredientConverter;
  private final NotesCommandToNotes notesConverter;

  public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter,
                               NotesCommandToNotes notesConverter) {
    this.categoryConverter = categoryConverter;
    this.ingredientConverter = ingredientConverter;
    this.notesConverter = notesConverter;
  }

  @Synchronized
  @Nullable
  @Override
  public Recipe convert(RecipeCommand source) {

    if (source != null) {
      Recipe recipe = new Recipe();
      recipe.setId(source.getId());
      recipe.setDescription(source.getDescription());
      recipe.setPrepTime(source.getPrepTime());
      recipe.setCookTime(source.getCookTime());
      recipe.setServings(source.getServings());
      recipe.setSource(source.getSource());
      recipe.setUrl(source.getUrl());
      recipe.setDirections(source.getDirections());
      recipe.setDifficulty(source.getDifficulty());
      recipe.setNotes(notesConverter.convert(source.getNotes()));

      if (source.getCategories() != null && !source.getCategories().isEmpty()) {
        source.getCategories()
            .forEach(categoryCommand -> recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
      }

      if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
        source.getIngredients()
            .forEach(ingredientCommand -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
      }

      return recipe;
    } else {
      return null;
    }
  }
}
