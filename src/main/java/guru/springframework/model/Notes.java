package guru.springframework.model;

import javax.persistence.*;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//can use Integer, but compared to long we will run out of numbers early.
    @OneToOne//wedonspecifycascade:b/c we wanttherecipetoownthis; ifwedeleteNotesobjectwedontwanttodeleteRecipeobject
    private Recipe recipe;//@joincolumn yasfelgal but if we dont mention itll go with jpa default.
    //blob => CLOB or BLOB
    @Lob //bcStringisverybig,butHybernatesstringis255only,sowedontusehybernatesstringweuse CLOB(charLargeObject)
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
