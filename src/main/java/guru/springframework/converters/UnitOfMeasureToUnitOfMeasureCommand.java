package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure == null)
            return null;
        UnitOfMeasureCommand UomCommand = new UnitOfMeasureCommand();
        UomCommand.setId(unitOfMeasure.getId());
        UomCommand.setUom(unitOfMeasure.getUom());
        return UomCommand;
    }
}
