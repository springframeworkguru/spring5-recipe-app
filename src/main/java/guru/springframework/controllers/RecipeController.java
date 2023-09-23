package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {
    RecipeService recipeService;

    @RequestMapping({"/id"})
    public String getRecipeById(Model model){
        Recipe recipe = recipeService.findById();
        return "recipe/show";
    }

}
