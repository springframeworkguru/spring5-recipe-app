package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@EqualsAndHashCode( exclude = {"recipe"})
@Entity
public class Notes {
    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
