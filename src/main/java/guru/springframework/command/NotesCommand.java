package guru.springframework.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NotesCommand extends BaseEntity{
    private String recipeNotes;
}
