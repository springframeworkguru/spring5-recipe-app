package guru.springframework.controllers;

import guru.springframework.dtos.IngredientDto;
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

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Showing list of ingredients");
        model.addAttribute("recipe", recipeService.getRecipeDtoById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredientOfRecipe(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        log.debug("Showing ingredient of id " + ingredientId + " of recipe id " + recipeId);
        model.addAttribute("ingredient", ingredientService.getIngredientByIdOfRecipeId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String showIngredientUpdateForm(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        log.debug("Showing ingredient update form of id " + ingredientId + " of recipe id " + recipeId);
        model.addAttribute("ingredient", ingredientService.getIngredientByIdOfRecipeId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        model.addAttribute("uomList", unitOfMeasureService.getAllUom());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(@PathVariable String recipeId, @ModelAttribute IngredientDto ingredientDto){
        log.debug("Saving new or updating existing ingredient of recipe with id " + recipeId);
        IngredientDto savedIngredientDto = ingredientService.saveOrUpdateIngredient(ingredientDto);
        return "redirect:/recipe/" + savedIngredientDto.getRecipeId() + "/ingredients";
    }
}
