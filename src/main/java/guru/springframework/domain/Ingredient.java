package guru.springframework.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String description;
    private BigDecimal ammount;

    @ManyToOne
    private Recipe recipe;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmmount() {
        return this.ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }
    

    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


}
