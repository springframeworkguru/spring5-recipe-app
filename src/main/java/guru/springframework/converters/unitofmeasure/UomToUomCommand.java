package guru.springframework.converters.unitofmeasure;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domains.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UomToUomCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure != null){
            final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
            command.setId(unitOfMeasure.getId());
            command.setDescription(unitOfMeasure.getDescription());

            return command;
        }

        return null;
    }
}
