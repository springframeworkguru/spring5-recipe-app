package guru.springframework.controller;

import guru.springframework.services.RecipeService;
import guru.springframework.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeList {

    private RecipeService recipeService;

    public RecipeList(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipes", "/recipes/"})
    public String getList(Model model){

        log.debug("Getting recipes page");

        model.addAttribute("recipes", recipeService.getRecipes());

        return "recipe/index";
    }
}
