package guru.springframework.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipe;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
