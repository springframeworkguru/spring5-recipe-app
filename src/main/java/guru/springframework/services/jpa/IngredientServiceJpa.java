package guru.springframework.services.jpa;

import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.dtos.IngredientDto;
import guru.springframework.mappers.IngredientMapper;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.IngredientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class IngredientServiceJpa implements IngredientService {
    private final RecipeRepository recipeRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientServiceJpa(RecipeRepository recipeRepository, IngredientMapper ingredientMapper) {
        this.recipeRepository = recipeRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    @Transactional
    public IngredientDto getIngredientByIdOfRecipeId(Long recipeId, Long ingredientId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (!optionalRecipe.isPresent()) {
            throw new RuntimeException("Recipe with id " + recipeId + " not found");
        }
        Recipe recipe = optionalRecipe.get();

        Optional<IngredientDto> optionalIngredientDto = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientMapper::ingredientToIngredientDto)
                .findFirst();
        if (!optionalIngredientDto.isPresent()) {
            throw new RuntimeException("Ingredient with id " + ingredientId + " not found in recipe with id " + recipeId);
        }
        return optionalIngredientDto.get();
    }
}
