package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;

        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(long recipeID,
                                                           long ingredientID) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeID);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe not found" + recipeOptional.get());
        }
        log.info("Recipe found " + recipeOptional.get());
        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommand =
                recipe.getIngredients()
                        .stream()
                        .filter(ingredient -> ingredient.getId() == (ingredientID))
                        .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                        .findFirst();


        if (!ingredientCommand.isPresent()) {
            log.error("");
            throw new RuntimeException("Ingredient not found in this recipe with ID: " + ingredientID);
        }

        return ingredientCommand.get();
    }

    @Transactional
    @Override
    public IngredientCommand saveOrUpdateIngredient(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOpt =
                recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!recipeOpt.isPresent()) {
            log.error("Recipe for ingredient not found in Database: with id: "
                    + ingredientCommand.getRecipeId());
            throw new RuntimeException("Recipe for ingredient not found in Database: with id: "
                    + ingredientCommand.getRecipeId());
        } else {
            Recipe recipe = recipeOpt.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId() == ingredientCommand.getId())
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                //Update the found ingredient
                Ingredient ingredient = ingredientOptional.get();
                ingredient.setDescription(ingredientCommand.getDescription());
                ingredient.setAmount(ingredientCommand.getAmount());
                ingredient.setUom(unitOfMeasureRepository
                        .findById(ingredientCommand.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("Unit not found")));

            } else {
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }
            Recipe saved = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional =
                    saved.getIngredients().stream()
                            .filter(ingredient -> ingredient.getId() == ingredientCommand.getId())
                            .findFirst();
//if ingredient does not have an id then match each value
            if (!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = saved.getIngredients().stream()
                        .filter(recipeIngredient -> recipeIngredient.getDescription()
                                .equals(ingredientCommand.getDescription()))
                        .filter(recipeIngredient -> recipeIngredient.getAmount()
                                .equals(ingredientCommand.getAmount()))
                        .filter(recipeIngredient -> recipeIngredient.getUom().getId()
                                .equals(ingredientCommand.getUom().getId()))
                        .findFirst();

            }
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }

    }

    @Override
    public void deleteIngredientById(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (!recipe.isPresent()) {
            throw new RuntimeException("Recipe not found with id: " + recipeId);
        }

        Optional<Ingredient> ingredientOptional = recipe.get().getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId() == ingredientId)
                .findFirst();


        if (!ingredientOptional.isPresent()) {
            throw new RuntimeException("Ingredient not found with id: " + ingredientId);
        }
        Ingredient ingredient = ingredientOptional.get();
        ingredient.setRecipe(null);

        recipe.get()
                .getIngredients()
                .removeIf(ing -> ing.getId() == ingredientId);

        recipeRepository.save(recipe.get());
    }


}

