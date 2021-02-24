package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandConverter;
    private final RecipeToRecipeCommand recipeConverter;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandConverter, RecipeToRecipeCommand recipeConverter) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandConverter = recipeCommandConverter;
        this.recipeConverter = recipeConverter;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug(getClass().getCanonicalName() + " accessed.");
        Set<Recipe> recipes = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;
    }

    @Override
    public Recipe findById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NotFoundException("Recipe not found for id " + recipeId));
    }

    @Override
    public RecipeCommand findCommandById(Long recipeId) {
        return recipeConverter.convert(findById(recipeId));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe savedRecipe = recipeRepository.save(recipeCommandConverter.convert(recipeCommand));

        return recipeConverter.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
