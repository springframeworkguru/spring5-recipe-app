package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/","/index", "/index.html"})
    public String getIndex(Model m){
        log.debug("In getIndex method of IndexController.");
        m.addAttribute("recipes", recipeService.getAll());

        log.debug("Recipe list has been added to the mode.");
        return "index";
    }
}
