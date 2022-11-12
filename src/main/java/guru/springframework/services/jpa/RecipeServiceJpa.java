package guru.springframework.services.jpa;

import guru.springframework.domain.Recipe;
import guru.springframework.dtos.RecipeDto;
import guru.springframework.mappers.RecipeMapper;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceJpa implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeServiceJpa(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public Set<Recipe> findAll() {
        log.debug("I'm in recipe service findAll method");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    @Transactional
    public RecipeDto saveRecipeDto(RecipeDto recipeDto) {
        Recipe newRecipe = recipeMapper.recipeDtoToRecipe(recipeDto);
        Recipe savedRecipe = recipeRepository.save(newRecipe);
        return recipeMapper.recipeToRecipeDto(savedRecipe);
    }


    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }
}
