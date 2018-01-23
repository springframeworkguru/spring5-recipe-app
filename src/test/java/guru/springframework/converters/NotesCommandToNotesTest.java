package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

  private NotesCommandToNotes notesCommandToNotes;
  private final Long ID = 1L;
  private final String TEST_NOTES = "Test notes";

  @Before
  public void setUp() throws Exception {
    notesCommandToNotes = new NotesCommandToNotes();
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    Notes notes = notesCommandToNotes.convert(null);

    // then
    assertNull(notes);
  }

  @Test
  public void shouldConvertEmptySource() {
    // when
    Notes notes = notesCommandToNotes.convert(new NotesCommand());

    // then
    assertNotNull(notes);
  }

  @Test
  public void shouldConvertSource() {
    // given
    NotesCommand command = new NotesCommand();
    command.setId(ID);
    command.setRecipeNotes(TEST_NOTES);

    // when
    Notes notes = notesCommandToNotes.convert(command);

    // then
    assertNotNull(notes);
    assertEquals(ID, notes.getId());
    assertEquals(TEST_NOTES, notes.getRecipeNotes());
  }
}