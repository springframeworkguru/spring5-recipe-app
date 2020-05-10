package guru.springframework.domain;

import javax.persistence.*;

@lombok.Data
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "notes")
    private Recipe recipe;
    @Lob
    private String recipeNotes;

}
