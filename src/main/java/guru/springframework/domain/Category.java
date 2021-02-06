package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public void addRecipe(Recipe recipe) {
        if (recipe == null) {
            return;
        } else if (recipes == null) {
            recipes = new HashSet<>();
        }
        recipes.add(recipe);
    }
}
