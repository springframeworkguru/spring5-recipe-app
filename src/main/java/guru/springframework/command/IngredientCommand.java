package guru.springframework.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand extends BaseEntity{

    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;

    // hidden property to reference the recipe
    private Long recipeId;
}
