package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode( exclude = {"recipes"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany( mappedBy = "categories" )
    private Set<Recipe> recipes;

}
