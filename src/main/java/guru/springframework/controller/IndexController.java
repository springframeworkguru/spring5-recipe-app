package guru.springframework.controller;

import guru.springframework.model.Recipe;
import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = {"/"})
    public String helloMethod(Model model){
        log.info("Conroller called");
        Iterable<Recipe> optionalRecipes = recipeService.getRecipe();
        model.addAttribute("recipes",optionalRecipes);
        return "index";
    }
}
