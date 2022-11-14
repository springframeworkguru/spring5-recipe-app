package guru.springframework.mappers;

import guru.springframework.domain.Ingredient;
import guru.springframework.dtos.IngredientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngredientMapper {
    Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto);
    @Mapping(target = "recipeId", source = "recipe.id" )
    IngredientDto ingredientToIngredientDto(Ingredient ingredient);

    IngredientDto copyDto(IngredientDto newIngredientDto);

    Set<Ingredient> ingredientDtosToIngredients(Set<IngredientDto> ingredientDtos);
    Set<IngredientDto> ingredientsToIngredientDtos(Set<Ingredient> ingredients);
}
