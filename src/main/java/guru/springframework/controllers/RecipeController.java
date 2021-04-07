package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;


    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(new Long (id)));

        Set<Category> cat = new HashSet<>();
        Recipe r = new Recipe();
        r = recipeService.findById(new Long (id));
        cat = r.getCategories();
        model.addAttribute("categories", cat);

        Set<Ingredient> i = new HashSet<>();
        i = r.getIngredients();
        model.addAttribute("ingredients", i);

        return "recipe/show";
    }    
}
