package guru.springframework.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity

public class Ingredient {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient() {

    }
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }


}
