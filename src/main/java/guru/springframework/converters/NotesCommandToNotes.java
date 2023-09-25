package guru.springframework.converters;

import guru.springframework.command.NotesCommand;
import guru.springframework.domain.Notes;
import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @Author: harshitasain
 * @Date: 24/09/23
 **/

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Override
    @Synchronized
    @Nullable
    public Notes convert(NotesCommand source) {
        if( source == null )
            return null;
        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
