package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.model.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand UomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        UomConverter = uomConverter;
    }


    @Override
    public IngredientCommand convert(Ingredient ingredient) {


        if(ingredient == null)
            return null;


        IngredientCommand ingCommand = new IngredientCommand();
//        if (ingredient.getRecipe() != null) {
//            ingCommand.setRecipeId(ingredient.getRecipe().getId());
//        }
        ingCommand.setId(ingredient.getId());
        ingCommand.setAmount(ingredient.getAmount());
        ingCommand.setDescription(ingredient.getDescription());
        ingCommand.setUom(UomConverter.convert(ingredient.getUom()));
       // ingCommand.setRecipeId(ingredient.getRecipe().getId());
        return ingCommand;
    }
}
