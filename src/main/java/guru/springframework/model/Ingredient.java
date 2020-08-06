package guru.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(BigDecimal amount, UnitOfMeasure uom,
                      String description){
        this.amount = amount;
        this.description = description;
        this.uom = uom;
       // this.recipe = guacamoleRecp;
    }



}
