package guru.springframework.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.HashSet;
import java.util.Set;


//@Data
//@Entity
//@EqualsAndHashCode(exclude = {"ingredients"})
@Getter
@Setter
@Document
public class Recipe {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
//    @Lob
    private String diretions;


//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    Set<Ingredient> ingredients = new HashSet<Ingredient>();

//    @Lob
    private Byte[] image;

//    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

//    @ManyToMany
//    @JoinTable(name="recipe_category",
//                joinColumns = @JoinColumn(name="recipe_id"),
//                inverseJoinColumns = @JoinColumn(name="category_id"))
    @DBRef
    private Set<Category> categories = new HashSet<Category>();

//    @Enumerated(EnumType.STRING)
    private Difficulty diffculty;

    public Recipe() {
    }

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            //notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
            //ingredient.setRecipe(this);
            this.ingredients.add(ingredient);
            return this;
         }

}
