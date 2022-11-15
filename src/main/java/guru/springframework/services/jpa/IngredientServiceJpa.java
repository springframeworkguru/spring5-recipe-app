package guru.springframework.services.jpa;

import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.dtos.IngredientDto;
import guru.springframework.dtos.UnitOfMeasureDto;
import guru.springframework.mappers.IngredientMapper;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceJpa implements IngredientService {
    private final RecipeRepository recipeRepository;
    private final IngredientMapper ingredientMapper;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public IngredientServiceJpa(RecipeRepository recipeRepository, IngredientMapper ingredientMapper, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientMapper = ingredientMapper;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public IngredientDto getIngredientByIdOfRecipeId(Long recipeId, Long ingredientId) {
        return ingredientMapper.ingredientToIngredientDto(findIngredient(recipeId, ingredientId));
    }

    @Override
    @Transactional
    public IngredientDto saveOrUpdateIngredient(IngredientDto newIngredientDto) {
        Recipe recipe = findRecipeById(newIngredientDto.getRecipeId());

        Optional<Ingredient> optionalIngredient = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(newIngredientDto.getId()))
                .findFirst();

        Ingredient newIngredient;
        if (!optionalIngredient.isPresent()) {
            //don't want to set id from outside. Database will assign id value for new Ingredient.
            if (Objects.nonNull(newIngredientDto.getId())) {
                newIngredientDto.setId(null);
            }
            // create new Ingredient object
            newIngredient = ingredientMapper.ingredientDtoToIngredient(newIngredientDto);
            //here is newIngredient without id
            recipe.addIngredient(newIngredient);
        } else {
            //update existing Ingredient object
            newIngredient = optionalIngredient.get();
            newIngredient.setDescription(newIngredientDto.getDescription());
            newIngredient.setAmount(newIngredientDto.getAmount());

            UnitOfMeasureDto newUnitOfMeasureDto = newIngredientDto.getUom();
            if (Objects.nonNull(newUnitOfMeasureDto)) {
                newIngredient.setUom(unitOfMeasureRepository.findById(newUnitOfMeasureDto.getId()).orElse(null));
            } else {
                newIngredient.setUom(null);
            }
        }

        recipeRepository.save(recipe);

        //todo fix returning newingredient WITH ID.
        // Current returned newIngredient is without id when dont updating existing ingredient

        return ingredientMapper.ingredientToIngredientDto(newIngredient);

    }

    @Override
    @Transactional
    public void deleteIngredientWithIdOfRecipeWithId(Long recipeId, Long ingredientId) {
        Recipe recipe = findRecipeById(recipeId);
        recipe.getIngredients().removeIf(ingredient -> {
            if (Objects.equals(ingredient.getId(), ingredientId)){
                ingredient.setRecipe(null);
                return true;
            }
            return false;
        });
        recipeRepository.save(recipe);
    }

    private Ingredient findIngredient(Long recipeId, Long ingredientId) {
        Recipe recipe = findRecipeById(recipeId);

        Optional<Ingredient> optionalIngredient = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();
        if (!optionalIngredient.isPresent()) {
            throw new RuntimeException("Ingredient with id " + ingredientId + " not found in recipe with id " + recipeId);
        }
        return optionalIngredient.get();

    }

    private Recipe findRecipeById(Long recipeId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (!optionalRecipe.isPresent()) {
            throw new RuntimeException("Recipe with id " + recipeId + " not found");
        } else {
            return optionalRecipe.get();
        }
    }
}
