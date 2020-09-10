package guru.springframework.model;

import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
//@Entity
public class Notes {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
//    @OneToOne
    private Recipe recipe;

//    @Lob
    private String recipeNotes;


}
