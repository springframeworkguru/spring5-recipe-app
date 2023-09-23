package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {
    RecipeService recipeService;

    @RequestMapping({"show/{id}"})
    public String getRecipeById(@PathVariable String id, Model model){
        Recipe recipe = recipeService.findById(Long.parseLong(id));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

}
