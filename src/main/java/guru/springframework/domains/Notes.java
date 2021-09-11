package guru.springframework.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
public class Notes {
    @Id
    private String id;
    private String recipeNotes;

    protected boolean canEqual(final Object other) {
        return other instanceof Notes;
    }

}
