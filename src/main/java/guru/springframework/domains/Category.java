package guru.springframework.domains;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;


}
