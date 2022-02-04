package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;


    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,
                                 IngredientCommandToIngredient ingredientConverter,
                                 NotesCommandToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }
    @Nullable
    @Synchronized
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        final Recipe target = new Recipe();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setPrepTime(source.getPrepTime());
        target.setCookTime(source.getCookTime());
        target.setServings(source.getServings());
        target.setSource(source.getSource());
        target.setUrl(source.getUrl());
        target.setDirections(source.getDirections());
        target.setDifficulty(source.getDifficulty());
        target.setNotes(notesConverter.convert(source.getNotes()));
        setCategoriesToTarget(source, target);
        setIngredientsToTarget(source, target);
        return target;
    }

    private void setCategoriesToTarget(RecipeCommand source, Recipe target) {
        if (source.getCategories() != null && source.getCategories().size() != 0) {
            source.getCategories()
                    .forEach(categoryCommand -> target.getCategories()
                            .add(categoryConverter.convert(categoryCommand)));
        }

    }

    private void setIngredientsToTarget(RecipeCommand source, Recipe target) {
        if (source.getIngredients() != null && source.getIngredients().size() != 0) {
            source.getIngredients()
                    .forEach(ingredientCommand -> target.getIngredients()
                            .add(ingredientConverter.convert(ingredientCommand)));
        }
    }
}
