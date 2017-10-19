package guru.springframework.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    //For super long string!
    @Lob
    private String recipeNotes;

}
