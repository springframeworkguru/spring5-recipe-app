package guru.springframework.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient extends BaseEntity{

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER) // Retrieve this field every time from the DB
    private UnitOfMeasure uom;

    // Map: Many Ingredient -> One Recipe , While the Recipe owns the Ingredient entity
    // No Cascade here, Deleting an Ingredient should not delete a Recipe
    @ManyToOne
    private Recipe recipe;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
