package guru.springframework.controller;

import guru.springframework.model.Recipe;
import guru.springframework.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class IndexController {

    private RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }



    @GetMapping({"","/","/recipes"})
    public String getIndexPage(Model model){

        /*ArrayList<Recipe>*/ Set<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes",recipes);



        return "index";
    }

}
