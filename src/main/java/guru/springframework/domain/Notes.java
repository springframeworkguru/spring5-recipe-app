package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //relación con Recipe
    @OneToOne
    private Recipe recipe;
    //hay que permitir que se acepte mas que el limite de caracteres (255) con @Lob
    @Lob
    private String recipeNotes;

}
