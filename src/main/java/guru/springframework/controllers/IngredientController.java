package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/recipe/{recipeId}")
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/ingredients")
    public String showIngredients(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "recipe/ingredient/list";
    }

    @GetMapping("/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        return "recipe/ingredient/show";
    }

    @PostMapping("/ingredient")
    public String postIngredient(@PathVariable Long recipeId, @ModelAttribute IngredientCommand command) {
        final IngredientCommand savedCommand = ingredientService.save(command);
        return String.format("redirect:/recipe/%d/ingredient/%d/show", recipeId, savedCommand.getId());

    }

    @GetMapping("/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        model.addAttribute("uomList", unitOfMeasureService.findAllCommands());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/ingredient/new")
    public String newIngredient(@PathVariable Long recipeId, Model model) {
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", unitOfMeasureService.findAllCommands());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable Long ingredientId, @PathVariable Long recipeId) {
        ingredientService.deleteByIngredientIdAndRecipeId(ingredientId, recipeId);
        return String.format("redirect:/recipe/%d/ingredients", recipeId);
    }


//TODO add error handling
}
