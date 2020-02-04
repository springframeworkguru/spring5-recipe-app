package guru.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToOne
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient(Long id, String description, BigDecimal amount, Recipe recipe) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
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
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
