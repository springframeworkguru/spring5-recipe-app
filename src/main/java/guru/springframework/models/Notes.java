package guru.springframework.models;

import javax.persistence.*;

@Entity
public class Notes{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // get the id value from the DB
    private Long id;

    @OneToOne // leave empty so the Recipe entity will own this object
    private Recipe recipe; // 1-1

    @Lob // Large Object, hint JPA to expect lots of data in this filed
    private String recipeNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
