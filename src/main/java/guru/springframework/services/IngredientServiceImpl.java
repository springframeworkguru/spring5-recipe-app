package guru.springframework.services;

import guru.springframework.Converters.IngredientCommandToIngredient;
import guru.springframework.Converters.IngredientToIngredientCommand;
import guru.springframework.command.IngredientCommand;
import guru.springframework.models.Ingredient;
import guru.springframework.models.Recipe;
import guru.springframework.models.UnitOfMeasure;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            // ToDo
            throw new RuntimeException("Recipe not found");
        }

        Recipe recipe = recipeOptional.get();

        // 1
        Set<Ingredient> ingredientSet = recipe.getIngredients();

        // 2
        Stream<Ingredient> ingredientStream = ingredientSet.stream();

        // 3
        Stream<Ingredient> filter = ingredientStream.filter(ingredient -> ingredient.getId().equals(ingredientId));

        // 4
        Optional<Ingredient> optionalIngredient = filter.findFirst();
        if(!optionalIngredient.isPresent()){
            // ToDo
            throw new RuntimeException("Ingredient not found");
        }

        Ingredient ingredient = optionalIngredient.get();
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);

        return ingredientCommand;
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        if(!recipeOptional.isPresent()){
            // ToDo
            log.error("Recipe not found for id: " + command.getRecipeId());
            throw new RuntimeException("Recipe not found");
        } else {

            // get the recipe
            Recipe recipe = recipeOptional.get();

            // find if the ingredient exist
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient1 -> ingredient1.getId().equals(command.getId()))
                    .findFirst();
            if(ingredientOptional.isPresent()){
                // update

                Ingredient foundIngredient = ingredientOptional.get();
                foundIngredient.setDescription(command.getDescription());
                foundIngredient.setAmount(command.getAmount());

                // load UOM
                Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findById(foundIngredient.getUom().getId());
                foundIngredient.setUom(unitOfMeasureOptional.get());

            } else {
                // add new
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            // return the ingredientCommand
            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                    .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                    .filter(ingredient -> ingredient.getUom().getId().equals(command.getUom().getId()))
                    .findFirst();
            if(!savedIngredientOptional.isPresent()){
                // ToDo
                log.error("Ingredient not found for id: " + command.getRecipeId());
                throw new RuntimeException("Recipe not found");
            } else {
                return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
            }
        }
    }

    @Override
    public void deleteById(Long recipeId, Long ingredientId) {

        log.debug("delete ingredient :" + ingredientId);

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if(!optionalRecipe.isPresent()){
            // todo
            throw new RuntimeException("recipe not found by recipeId :" + recipeId);
        }

        Recipe recipe = optionalRecipe.get();

        // find the ingredient
        Optional<Ingredient> optionalIngredient = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();

        if(!optionalIngredient.isPresent()){
            // todo
            log.error("Ingredient not found id:" + ingredientId);
        }

        Ingredient ingredient = optionalIngredient.get();
        // set the relationship to null -> this will caouse JPA to delete the ingredient
        ingredient.setRecipe(null);

        recipe.getIngredients().remove(ingredient);

        // update the recipe
        recipeRepository.save(recipe);
    }
}
