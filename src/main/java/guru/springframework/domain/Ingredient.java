package guru.springframework.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( exclude = {"recipe"})
@Entity
public class Ingredient implements Comparable<Ingredient>{
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private UnitOfMeasure unitOfMeasure;
    @ManyToOne
    private Recipe recipe;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
        this.recipe = recipe;
    }

    @Override
    public int compareTo(Ingredient o) {
        if( this.getId() < o.getId() )
            return -1;
        else
            return 1;
    }
}
