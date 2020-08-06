package guru.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@EqualsAndHashCode(exclude = {"ingredients"})
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String diretions;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    Set<Ingredient> ingredients = new HashSet<Ingredient>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name="recipe_category",
                joinColumns = @JoinColumn(name="recipe_id"),
                inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<Category>();

    @Enumerated(EnumType.STRING)
    private Difficulty diffculty;

    public Recipe() {
    }


    public Recipe addIngredient(Ingredient ingredient){
            ingredient.setRecipe(this);
            this.getIngredients().add(ingredient);
            return this;
         }

}
