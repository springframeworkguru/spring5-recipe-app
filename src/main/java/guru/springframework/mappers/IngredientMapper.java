package guru.springframework.mappers;

import guru.springframework.domain.Ingredient;
import guru.springframework.dtos.IngredientDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngredientMapper {
    Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto);
    IngredientDto ingredientToIngredientDto(Ingredient ingredient);

    Set<Ingredient> ingredientDtosToIngredients(Set<IngredientDto> ingredientDtos);
    Set<IngredientDto> ingredientsToIngredientDtos(Set<Ingredient> ingredients);
}
