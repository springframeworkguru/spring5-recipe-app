package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {

    private IngredientService ingredientService;
    private UnitOfMeasureService uomService;

    public IngredientController(IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String getUpdateRecipe(@PathVariable("ingredientId") Long id, @PathVariable("recipeId") Long recipeId, Model model) {

        model.addAttribute("ingredient", ingredientService.findByIngredientId(id));
        model.addAttribute("uomList", uomService.findAll());

        return "ingredient/ingredient_form";
    }

    @PostMapping("/recipe/{recipeId}/ingredient/{ingredientId}")
    public String saveIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand);

        return "redirect:/recipe/" + savedIngredient.getRecipeId() + "/show";
    }
}
