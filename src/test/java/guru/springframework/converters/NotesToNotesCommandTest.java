package guru.springframework.converters;

import guru.springframework.command.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final long ID = 1L;
    public static final String RECIPE_NOTES = "RECIPE_NOTES";
    private NotesToNotesCommand notesToNotesCommand;
    @BeforeEach
    void setUp() {
        notesToNotesCommand = new NotesToNotesCommand();
    }

    @Test
    void testNull(){
        NotesCommand notesCommand = notesToNotesCommand.convert(null);
        assertNull(notesCommand);
    }

    @Test
    void testNotNull(){
        NotesCommand notesCommand = notesToNotesCommand.convert(new Notes());
        assertNotNull(notesCommand);
    }

    @Test
    void testConvert(){
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        NotesCommand notesCommand = notesToNotesCommand.convert(notes);
        assertNotNull(notesCommand);
        assertEquals(ID, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}