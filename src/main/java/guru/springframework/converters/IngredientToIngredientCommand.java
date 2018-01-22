package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    UnitOfMeasureToUnitOfMeasureCommand uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source != null) {
            final IngredientCommand ingredientCommand = new IngredientCommand();
            ingredientCommand.setId(source.getId());
            ingredientCommand.setAmount(source.getAmount());
            ingredientCommand.setDescription(source.getDescription());
            ingredientCommand.setUnitOfMeasure(uomConverter.convert(source.getUom()));
            return ingredientCommand;
        } else {
            return null;
        }
    }
}
