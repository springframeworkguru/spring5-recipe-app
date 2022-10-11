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
  // @JoinColumn(name = "recipe_id")//nocascadeb/c itsnottheprimaryentityb/nthetwo ifwedeleteingredientwedontwantittocascadeUPanddeletethewholerecipeassociattedwithit.
    private Recipe recipe;

   public Ingredient(){
   }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure/*, Recipe recipe*/) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
//        this.recipe = recipe; //wedontdothisnowb/c addIngredient: ingredient.setRecipe(this);=>in Recipe is doing it for us.
    }

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
