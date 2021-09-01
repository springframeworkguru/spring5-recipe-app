package guru.springframework.services.ingredient;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.ingredients.IngredientCommandToIngredient;
import guru.springframework.converters.ingredients.IngredientToIngredientCommand;
import guru.springframework.domains.Ingredient;
import guru.springframework.domains.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand toIngredientCommand;
    private final IngredientCommandToIngredient fromIngredientCommand;
    private final UnitOfMeasureRepository uomRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand toIngredientCommand,
                                 IngredientCommandToIngredient fromIngredientCommand,
                                 UnitOfMeasureRepository uomRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.toIngredientCommand = toIngredientCommand;
        this.fromIngredientCommand = fromIngredientCommand;
        this.uomRepository = uomRepository;
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
    public void deleteIngredientFromRecipe(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(recipeOptional.isPresent()){
            log.debug("Found recipe with id: "+recipeId);
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipeOptional.get().getIngredients()
                    .stream().filter(ingredient -> ingredient.getId().equals(id))
                    .findFirst();

            if (ingredientOptional.isPresent()){
                log.debug("Found ingredient with id: "+ id);
                Ingredient ingredient = ingredientOptional.get();
                ingredient.setRecipe(null);
                recipe.getIngredients().remove(ingredient);
                log.debug("Removed ingredient from recipe and ingredient's recipe has been set to null.");

                recipeRepository.save(recipe);
                ingredientRepository.deleteById(ingredient.getId());
            }
        } else {
            throw new NotFoundException("Recipe with id not found: "+recipeId);
        }
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            throw new NotFoundException("Recipe with id not found: "+recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .map(toIngredientCommand::convert)
                .findFirst();

        if (!ingredientCommandOptional.isPresent())
            throw new NotFoundException("Ingredient with id " + id +" couldn't " +
                    "be found within ingredients of recipe with id "+ recipeId);

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){
            throw new NotFoundException("Recipe with id not found: "+command.getRecipeId());
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(uomRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                Ingredient ingredient = fromIngredientCommand.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                                    .findFirst();

            if (!savedIngredientOptional.isPresent()){
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                        .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                        .filter(ingredient -> ingredient.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            //to do check for fail
            return toIngredientCommand.convert(savedIngredientOptional.get());
        }

    }
}
