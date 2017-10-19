package guru.springframework.controller;

import guru.springframework.model.Recipe;
import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }



    @GetMapping({"","/","/recipes"})
    public String getIndexPage(Model model){
        log.debug("Currently Retriving Index Page");
        /*ArrayList<Recipe>*/ Set<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes",recipes);



        return "index";
    }

}
