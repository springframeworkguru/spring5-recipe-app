package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final CategoryToCategoryCommand categoryCommandConverter;
    private final IngredientToIngredientCommand ingredientCommandConverter;
    private final NotesToNotesCommand notesCommandConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryCommandConverter,
                                 IngredientToIngredientCommand ingredientCommandConverter,
                                 NotesToNotesCommand notesCommandConverter) {
        this.categoryCommandConverter = categoryCommandConverter;
        this.ingredientCommandConverter = ingredientCommandConverter;
        this.notesCommandConverter = notesCommandConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        final RecipeCommand target = new RecipeCommand();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setPrepTime(source.getPrepTime());
        target.setCookTime(source.getCookTime());
        target.setServings(source.getServings());
        target.setSource(source.getSource());
        target.setUrl(source.getUrl());
        target.setDirections(source.getDirections());
        target.setDifficulty(source.getDifficulty());
        target.setNotes(notesCommandConverter.convert(source.getNotes()));
        setCategoryCommandsToTarget(source, target);
        setIngredientCommandsToTarget(source, target);
        return target;
    }

    private void setIngredientCommandsToTarget(Recipe source, RecipeCommand target) {
        Set<IngredientCommand> ingredientCommands = source.getIngredients()
                .stream()
                .map(ingredientCommandConverter::convert)
                .collect(Collectors.toSet());
        target.getIngredients().addAll(ingredientCommands);
    }

    private void setCategoryCommandsToTarget(Recipe source, RecipeCommand target) {
        Set<CategoryCommand> categoryCommands = source.getCategories()
                .stream()
                .map(categoryCommandConverter::convert)
                .collect(Collectors.toSet());
        target.getCategories().addAll(categoryCommands);
    }

}
