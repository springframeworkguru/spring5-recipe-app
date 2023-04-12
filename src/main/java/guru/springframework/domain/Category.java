package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Category {

    @Id
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
