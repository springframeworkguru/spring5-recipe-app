package guru.springframework.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)//wedontcascadeb/cwedontwanttodeletemeasurementsjustbecausewearedeletingwhatwearemeasuring
    private UnitOfMeasure unitOfMeasure;//eagerIsTheDefault.thisisjusttoshow
   @ManyToOne
   @JoinColumn(name = "Recipe_")//nocascadeb/c ifwedeleteingredientwedontwantittocascadeupanddeletethewholerecipeassociattedwithit.
    private Recipe recipe;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUom() {
        return unitOfMeasure;
    }

    public void setUom(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
