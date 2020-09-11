package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Recipe;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory cateGoryConverter;
    private final IngredientCommandToIngredient ingrConverter;
    private final NotesCommandToNotes noteConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory cateGoryConverter,
                                 IngredientCommandToIngredient ingrConverter,
                                 NotesCommandToNotes noteConverter) {
        this.cateGoryConverter = cateGoryConverter;
        this.ingrConverter = ingrConverter;
        this.noteConverter = noteConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        if(source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setCookTime(source.getCookTime());
        recipe.setId(source.getId());
        recipe.setDiretions(source.getDiretions());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setServings(source.getServings());
        recipe.setUrl(source.getUrl());
        recipe.setSource(source.getSource());
        recipe.setDescription(source.getDescription());

        recipe.setNotes(noteConverter.convert(source.getNotes()));
        source.getCategories()
                .forEach( categoryCommand ->
                        recipe.getCategories().add(cateGoryConverter.convert(categoryCommand)));

        source.getIngredients().
                forEach( ingredientCommand -> {
                                                recipe.getIngredients().add(ingrConverter.convert(ingredientCommand));
                     });


        return recipe;
    }
}
