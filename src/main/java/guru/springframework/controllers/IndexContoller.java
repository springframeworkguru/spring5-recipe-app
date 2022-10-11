package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexContoller {

    private final RecipeService recipeService;

    public IndexContoller(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipe());

        return "index";
    }
}
