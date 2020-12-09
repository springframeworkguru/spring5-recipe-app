package cc.paukner.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // underlying persistence framework will generate
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING) // otherwise ints would be persisted that receive their values by order
    private Difficulty difficulty;

    @OneToMany(cascade = CascadeType.ALL /*to make this the owner*/, mappedBy = "recipe" /*property on the child class*/)
                                                                   //^^^^^^^^ Why is this mapped here and not at the other side?
    private Set<Ingredient> ingredients;

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL) // Recipe is the owner due to cascade
                                         // Use case: If recipe is deleted, also delete its notes
    private Notes notes;

    // If only @ManyToMany specified on both sides, we'll get two tables category_recipes and recipe_categories
    // with columns {category,recipes}_id vs. {categories,recipe}_id
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"), // this side
            inverseJoinColumns = @JoinColumn(name = "category_id") // coming back
    )
    private Set<Category> categories;

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(this);
        }
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }
}
