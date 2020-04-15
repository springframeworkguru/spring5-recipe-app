package guru.springframework.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // get the id value from the DB
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories") // mappedBy = "categories" , the name of the attribute in Recipe
    private Set<Recipe> recipes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
