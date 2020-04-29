package guru.springframework.services;

import guru.springframework.Converters.RecipeCommandToRecipe;
import guru.springframework.Converters.RecipeToRecipeCommand;
import guru.springframework.command.RecipeCommand;
import guru.springframework.models.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().forEach(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw  new RuntimeException("Recipe Not Found");

        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {

        // detachRecipe is detached from the hibernate context
        Recipe detachRecipe = recipeCommandToRecipe.convert(command);

        // if this is new -> create
        // if exist -> merge
        Recipe savedRecipe = recipeRepository.save(detachRecipe);

        log.debug("Saved RecipeId : " + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
