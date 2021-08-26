package guru.springframework.controllers;

import guru.springframework.services.ingredient.IngredientService;
import guru.springframework.services.recipe.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {
    RecipeService recipeService;
    IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model){
        log.debug("Getting ingredient list for recipe id: "+ id);
        model.addAttribute("recipe", recipeService.getById(Long.valueOf(id)));

        return "recipe/ingredient/list";
    }
    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String getOneIngredient(@PathVariable String recipeId,
                                   @PathVariable String id,
                                   Model model){
        log.debug("Getting ingredient with id " + id + " from recipe with id "+ recipeId);

        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        return "recipe/ingredient/show";
    }
}
