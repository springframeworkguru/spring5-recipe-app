package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

  private NotesToNotesCommand notesToNotesCommand;
  private final Long ID = 1L;
  private final String TEST_NOTES = "Test notes";

  @Before
  public void setUp() throws Exception {
    notesToNotesCommand = new NotesToNotesCommand();
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    NotesCommand notesCommand = notesToNotesCommand.convert(null);

    // then
    assertNull(notesCommand);
  }

  @Test
  public void shouldConvertEmptySource() {
    // when
    NotesCommand notesCommand = notesToNotesCommand.convert(new Notes());

    // then
    assertNotNull(notesCommand);
  }

  @Test
  public void shouldConvertSource() {
    // given
    Notes notes = new Notes();
    notes.setId(ID);
    notes.setRecipeNotes(TEST_NOTES);

    // when
    NotesCommand command = notesToNotesCommand.convert(notes);

    // then
    assertNotNull(command);
    assertEquals(ID, command.getId());
    assertEquals(TEST_NOTES, command.getRecipeNotes());
  }
}