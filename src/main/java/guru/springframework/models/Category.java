package guru.springframework.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category extends BaseEntity{

    private String description;

    @ManyToMany(mappedBy = "categories") // mappedBy = "categories" , the name of the attribute in Recipe
    private Set<Recipe> recipes;

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
