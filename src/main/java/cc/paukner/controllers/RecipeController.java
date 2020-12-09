package cc.paukner.controllers;

import cc.paukner.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/"})
    public String listRecipes(Model model) {
        log.debug("Listing all recipes");
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "recipes/index";
    }
}
