package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@RequestMapping("/recipe")
@Controller
public class RecipesController {

    private final RecipeService recipeService;

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getList(Model model) {

        model.addAttribute("recipes", this.recipeService.getAll());

        log.debug("So far so good Recipes controller!");

        return "recipe/index";

    }
}
