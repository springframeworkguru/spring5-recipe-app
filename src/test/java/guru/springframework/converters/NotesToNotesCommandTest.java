package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    private static final Long idValue = 1L;
    private static final String recipeNotes = "Notes";
    NotesToNotesCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() throws Exception {
        Notes notes = new Notes();
        notes.setId(idValue);
        notes.setRecipeNotes(recipeNotes);

        NotesCommand notesCommand = converter.convert(notes);

        assertEquals(idValue,notesCommand.getId());
        assertEquals(recipeNotes,notesCommand.getRecipeNotes());
    }
}