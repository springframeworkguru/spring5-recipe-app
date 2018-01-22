package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    UnitOfMeasureCommandToUnitOfMeasure uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source != null) {
            final Ingredient ingredient = new Ingredient();
            ingredient.setId(source.getId());
            ingredient.setAmount(source.getAmount());
            ingredient.setDescription(source.getDescription());
            ingredient.setUom(uomConverter.convert(source.getUnitOfMeasure()));
            return ingredient;
        } else {
            return null;
        }
    }

    @Synchronized
    @Nullable
    public Set<Ingredient> covert(Set<IngredientCommand> source) {
        return source.stream()
                .map(IngredientCommandToIngredient::convert)
                .collect(Collectors.toSet());
    }
}
