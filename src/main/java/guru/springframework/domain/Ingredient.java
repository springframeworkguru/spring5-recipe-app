package guru.springframework.domain;

/**
 * Created by jt on 6/13/17.
 */
import org.hibernate.annotations.Cascade;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by jt on 6/13/17.
 */
@Entity
public class Ingredient {

    public Ingredient() {

    }

    public Ingredient(String description) {
        this.description = description;
    }

    public Ingredient(String description, Recipe recipe) {
        this.description = description;
        this.recipe = recipe;
    }

    public Ingredient(String description, Recipe recipe, BigDecimal amount) {
        this.description = description;
        this.recipe = recipe;
        this.amount = amount;
    }

    public Ingredient(String description, Recipe recipe, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
        this.unitOfMeasure = unitOfMeasure;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    //private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private UnitOfMeasure unitOfMeasure;

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

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
