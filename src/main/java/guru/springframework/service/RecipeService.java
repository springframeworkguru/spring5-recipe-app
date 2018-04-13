package guru.springframework.service;

import guru.springframework.bootstrap.RecipeBootstrap;
import guru.springframework.model.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService implements RecipeServiceInter{

    private RecipeRepository recipeRepository;

    @Autowired
    public void setRecipeBootstrap(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getListRecipe(){
        List<Recipe> recipes=new ArrayList<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    public String hello(){
        return "hiiiii";
    }
}
