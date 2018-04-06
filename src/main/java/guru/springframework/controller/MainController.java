package guru.springframework.controller;

import guru.springframework.model.Category;
import guru.springframework.model.Recipe;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class MainController {
    private RecipeService recipeService;


    @RequestMapping("/all")
    public List<Recipe> getAllRecipes(){
        System.out.println(recipeService.getListRecipe().get(0).toString());
        return recipeService.getListRecipe();
    }
    @RequestMapping("/hi")
    public String hi(){
        return recipeService.hello();
    }


    @Autowired
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}
