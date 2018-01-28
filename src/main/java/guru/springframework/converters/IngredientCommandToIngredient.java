package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source != null) {
            final Ingredient ingredient = new Ingredient();
            ingredient.setId(source.getId());
            ingredient.setAmount(source.getAmount());
            ingredient.setDescription(source.getDescription());
            ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
            return ingredient;
        } else {
            return null;
        }
    }
}
