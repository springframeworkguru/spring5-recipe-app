package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
    private static final Long idValue = 1L;
    public static final String recipeNotes = "Notes";
    NotesCommandToNotes converter;
    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(idValue);
        notesCommand.setRecipeNotes(recipeNotes);

        Notes notes = converter.convert(notesCommand);

        assertNotNull(notes);
        assertEquals(idValue,notes.getId());
        assertEquals(recipeNotes,notes.getRecipeNotes());
    }
}