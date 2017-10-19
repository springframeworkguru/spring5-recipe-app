package guru.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories") //mapped by means, it is mapped by the "categories" property of the main object
    private Set<Recipe> recipes;

}
