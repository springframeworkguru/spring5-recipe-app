package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.lang.Nullable;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Nullable
    @Synchronized
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }
        final NotesCommand target = new NotesCommand();
        target.setId(source.getId());
        target.setRecipeNotes(source.getRecipeNotes());
        return target;
    }
}
