package guru.springframework.controller;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.service.IngredientService;
import guru.springframework.service.RecipeService;
import guru.springframework.service.UnitOfMeasureService;
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
    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngredient(@PathVariable Long id, Model model){
        RecipeCommand commandById = recipeService.findCommandById(id);
        model.addAttribute("recipe",commandById);
        return "recipe/ingredient/list";
    }
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String ShowRecipeIngredient(@PathVariable Long recipeId,@PathVariable Long id,Model model){
        model.addAttribute("ingredient",
                            ingredientService.findByRecipeIdAndIngredientId(recipeId,id));
        return "recipe/ingredient/show";
    }
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(recipeId,id));
        model.addAttribute("uomList",unitOfMeasureService.findAll());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String updateOrSave(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";

    }
}
