package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@ToString(exclude = "recipe")
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @OneToOne
    private UnitOfMeasure uom;
    @ManyToOne
    private Recipe recipe;

}
