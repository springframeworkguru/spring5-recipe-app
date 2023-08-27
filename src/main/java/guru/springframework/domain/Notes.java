package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Notes {
    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return Objects.equals(id, notes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
