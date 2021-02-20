package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService uomService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService uomService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String getUpdateIngredient(@PathVariable("ingredientId") Long id, @PathVariable("recipeId") Long recipeId, Model model) {

        model.addAttribute("ingredient", ingredientService.findByIngredientId(id));
        model.addAttribute("uomList", uomService.findAll());

        return "ingredient/ingredient_form";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String getCreateIngredient(@PathVariable("recipeId") String recipeId, Model model) {

        //TODO: transfer those in service
        //TODO: raise exception if recipe command is not found
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        ingredientCommand.setRecipe(recipeService.findCommandById(Long.valueOf(recipeId)));

        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", uomService.findAll());

        return "ingredient/ingredient_form";
    }

    @PostMapping("/recipe/{recipeId}/ingredient/{ingredientId}")
    public String saveIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand);

        return "redirect:/recipe/" + savedIngredient.getRecipeId() + "/show";
    }
}
