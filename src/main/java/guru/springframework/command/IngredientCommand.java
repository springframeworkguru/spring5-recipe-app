package guru.springframework.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand implements Comparable<IngredientCommand>{
    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;

    @Override
    public int compareTo(IngredientCommand o) {
        if( this.getId() < o.getId() )
            return -1;
        else
            return 1;
    }
}
