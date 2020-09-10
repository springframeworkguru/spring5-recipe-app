package guru.springframework.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Set;


@Getter
@Setter
@Document
//@Entity
//@EqualsAndHashCode(exclude = {"recipes"})
public class Category {

//   // @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String Id;
    private String description;


//    @ManyToMany(mappedBy = "categories")
    @DBRef
    private Set<Recipe> recipes;

}
