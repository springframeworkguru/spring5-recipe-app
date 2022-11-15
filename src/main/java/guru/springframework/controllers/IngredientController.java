package guru.springframework.controllers;

import guru.springframework.dtos.IngredientDto;
import guru.springframework.dtos.UnitOfMeasureDto;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Showing list of ingredients");
        model.addAttribute("recipe", recipeService.getRecipeDtoById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredientOfRecipe(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug("Showing ingredient of id " + ingredientId + " of recipe id " + recipeId);
        model.addAttribute("ingredient", ingredientService.getIngredientByIdOfRecipeId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String showIngredientUpdateForm(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug("Showing ingredient update form of id " + ingredientId + " of recipe id " + recipeId);
        model.addAttribute("ingredient", ingredientService.getIngredientByIdOfRecipeId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        model.addAttribute("uomList", unitOfMeasureService.getAllUom());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String getNewIngredientForm(@PathVariable String recipeId, Model model) {
        log.debug("Showing new ingredient form of recipe with id " + recipeId);
        //todo check if recipeId is correct
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setRecipeId(Long.valueOf(recipeId));
        ingredientDto.setUom(new UnitOfMeasureDto());
        model.addAttribute("ingredient", ingredientDto);

        model.addAttribute("uomList", unitOfMeasureService.getAllUom());
        return "recipe/ingredient/ingredientform";
    }
    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(@PathVariable String recipeId, @ModelAttribute IngredientDto ingredientDto) {
        log.debug("Saving new or updating existing ingredient of recipe with id " + recipeId);
        IngredientDto savedIngredientDto = ingredientService.saveOrUpdateIngredient(ingredientDto);
        return "redirect:/recipe/" + savedIngredientDto.getRecipeId() + "/ingredients";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String saveOrUpdateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
        log.debug("Deleting ingredient with id " + ingredientId + " of recipe with id " + recipeId);
        ingredientService.deleteIngredientWithIdOfRecipeWithId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
