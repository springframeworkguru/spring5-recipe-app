package guru.springframework.services.ingredient;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.ingredients.IngredientToIngredientCommand;
import guru.springframework.domains.Ingredient;
import guru.springframework.domains.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.recipe.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand toIngredientCommand;

    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 RecipeRepository recipeRepository, IngredientToIngredientCommand toIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.toIngredientCommand = toIngredientCommand;
    }

    @Override
    public Set<Ingredient> getAll() {
        Set<Ingredient> ingredients = new HashSet<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        return ingredients;
    }

    @Override
    public Ingredient getById(Long id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);

        if (!ingredientOptional.isPresent())
            throw new RuntimeException("Ingredient optional couldn't be found.");

        return ingredientOptional.get();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //@TODO: do exception handling.
            log.error("Recipe not found with id: "+recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .map(toIngredientCommand::convert)
                .findFirst();

        if (!ingredientCommandOptional.isPresent())
            log.error("Ingredient with id " + id +" couldn't be found within ingredients of recipe with id "+ recipeId);


        return ingredientCommandOptional.get();
    }
}
