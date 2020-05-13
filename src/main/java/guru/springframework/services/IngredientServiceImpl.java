package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
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
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        return recipeRepository.findById(recipeId).flatMap(recipe -> {
            return recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredientId.equals(ingredient.getId()))
                    .findFirst();
        }).map(ingredientToIngredientCommand::convert).orElse(null);
    }

    @Override
    @Transactional
    public IngredientCommand save(IngredientCommand command) {
        //check recipe exists
        final Optional<Recipe> optRecipe = recipeRepository.findById(command.getRecipeId());

        if (!optRecipe.isPresent()) {
            log.debug("Recipe attached to ingredient could not be found.");
            return new IngredientCommand();
        }
        final Recipe recipe = optRecipe.get();
        final Optional<Ingredient> foundIngredient = recipe.getIngredients().stream().filter(ingredient -> Objects.equals(ingredient.getId(), command.getId()))
                .findFirst();
        if (foundIngredient.isPresent()) {
            final Ingredient ingredient = foundIngredient.get();
            ingredient.setAmount(command.getAmount());
            ingredient.setDescription(command.getDescription());
            final UnitOfMeasure savedUnitOfMeasure = Optional.ofNullable(command.getUom())
                    .flatMap(uomCommand -> unitOfMeasureRepository.findById(uomCommand.getId()))
                    .orElseThrow(() -> new RuntimeException("Uom could not be found"));
            ingredient.setUom(savedUnitOfMeasure);
        } else {
            final Ingredient ingredient = ingredientCommandToIngredient.convert(command);
            recipe.addIngredient(ingredient);
            ingredient.setRecipe(recipe);
        }
        log.debug("IngredientCommand to save: {}", command.toString());
        return recipeRepository.save(recipe).getIngredients().stream()
                .peek(ingredient -> log.debug(ingredient.toString()))
                .filter(ingredient ->
                        Objects.equals(ingredient.getDescription(), command.getDescription())
                                && Objects.equals(ingredient.getAmount(), command.getAmount())
                                && Objects.equals(ingredient.getUom().getId(), command.getUom().getId()))
                .findFirst()
                .map(ingredientToIngredientCommand::convert)
                .orElse(null);
    }

    @Override
    public void deleteByIngredientIdAndRecipeId(Long ingredientId, Long recipeId) {
        recipeRepository.findById(recipeId)
                .ifPresent(recipe -> {
                    recipe.getIngredients().stream()
                            .filter(ingredient -> Objects.equals(ingredientId, ingredient.getId()))
                            .findFirst().ifPresent(ingredient -> {
                        recipe.getIngredients().remove(ingredient);
                        ingredient.setRecipe(null);
                    });
                    recipeRepository.save(recipe);
                });
    }

}
