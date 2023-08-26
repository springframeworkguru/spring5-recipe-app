package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug( this.getClass().getName() + ": Entering getIndexPage.");
        Set<Recipe> recipeSet = recipeService.findAllRecipes();
        model.addAttribute("recipes", recipeSet);
        log.debug( this.getClass().getName() + ": Exiting getIndexPage.");
        return "index";

    }
}
