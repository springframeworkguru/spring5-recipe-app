package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.lang.Nullable;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure toUnitOfMeasureConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure toUnitOfMeasureConverter) {
        this.toUnitOfMeasureConverter = toUnitOfMeasureConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }
        final Ingredient target = new Ingredient();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setAmount(source.getAmount());
        target.setUnitOfMeasure(toUnitOfMeasureConverter.convert(source.getUnitOfMeasure()));
        return target;
    }
}
