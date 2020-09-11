package guru.springframework.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
//@Entity
public class Ingredient {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id = UUID.randomUUID().toString();

    private String description;
    private BigDecimal amount;

//    @OneToOne(fetch = FetchType.EAGER)
    @DBRef
    private UnitOfMeasure uom;


    public Ingredient() {
    }

    public Ingredient(BigDecimal amount, UnitOfMeasure uom,
                      String description){
        this.amount = amount;
        this.description = description;
        this.uom = uom;

    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
       // this.recipe = recipe;
    }


}
