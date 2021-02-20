package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.domain.Ingredient;
import guru.springframework.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeService recipeService;
    private final IngredientToIngredientCommand converter;
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeService recipeService, IngredientToIngredientCommand converter, UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.ingredientRepository = ingredientRepository;
        this.recipeService = recipeService;
        this.converter = converter;
        this.uomConverter = uomConverter;
    }

    @Override
    public IngredientCommand findByIngredientId(Long ingredientId) {
        return converter.convert(ingredientRepository.findById(ingredientId).orElse(new Ingredient()));
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Ingredient ingredient = ingredientRepository.findByIdAndRecipeId(command.getId(), command.getRecipeId()).orElse(null);

        if (ingredient == null) {
            ingredient = new Ingredient();
        }

        ingredient.setRecipe(recipeService.findById(command.getRecipeId()));
        ingredient.setDescription(command.getDescription());
        ingredient.setAmount(command.getAmount());
        ingredient.setUom(uomConverter.convert(command.getUom()));

        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return converter.convert(savedIngredient);
    }


}
