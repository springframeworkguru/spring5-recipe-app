package guru.springframework.converters;

import guru.springframework.command.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    public static final long ID = 1L;
    public static final String RECIPE_NOTES = "RECIPE NOTES";
    private NotesCommandToNotes notesCommandToNotes;
    @BeforeEach
    void setUp() {
        notesCommandToNotes = new NotesCommandToNotes();
    }

    @Test
    void testNull(){
        Notes notes = notesCommandToNotes.convert(null);
        assertNull(notes);
    }

    @Test
    void testNotNull(){
        Notes notes = notesCommandToNotes.convert(new NotesCommand());
        assertNotNull(notes);
    }

    @Test
    void testConvert(){
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        Notes notes = notesCommandToNotes.convert(notesCommand);
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}