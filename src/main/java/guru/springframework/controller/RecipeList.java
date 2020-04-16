package guru.springframework.controller;

import guru.springframework.models.Recipe;
import guru.springframework.services.RecipeService;
import guru.springframework.services.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class RecipeList {

    private RecipeService recipeService;

    public RecipeList(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipes", "/recipes/"})
    public String getList(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());

        return "recipe/index";
    }
}
