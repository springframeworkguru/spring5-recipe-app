package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Iterable<Recipe> saveAll(List<Recipe> recipes) {
        return recipeRepository.saveAll(recipes);
    }


    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }


    public boolean existsById(Long id) {
        return recipeRepository.existsById(id);
    }


    public List<Recipe> findAll() {
        List recipes = new ArrayList();
        recipeRepository.findAll().forEach(recipe -> recipes.add(recipe));
        return recipes;
    }


    public Iterable<Recipe> findAllById(Iterable<Long> ids) {
        return recipeRepository.findAllById(ids);
    }


    public long count() {
        return recipeRepository.count();
    }


    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }


    public void delete(Recipe entity) {
        recipeRepository.delete(entity);
    }


    public void deleteAll(Iterable<? extends Recipe> entities) {
        recipeRepository.deleteAll(entities);
    }


    public void deleteAll() {
        recipeRepository.deleteAll();
    }

    @Override
    public Set<Recipe> getRecipes() {
       log.debug("I am in get recipe method of Recipe Service");

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }
}
