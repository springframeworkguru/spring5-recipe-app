package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    public NotesToNotesCommand() {
    }

    @Synchronized
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }
        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getRecipe().getId());
        notesCommand.setRecipe(recipeCommand);
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        return notesCommand;
    }
}
