package guru.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String URL;
    @Lob
    private String direction;
    @Lob
    private Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "recipe_category",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>() ;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public  Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.getIngredients().add(ingredient);
        return this;
    }
}
