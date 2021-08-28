package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.ingredient.IngredientService;
import guru.springframework.services.recipe.RecipeService;
import guru.springframework.services.unitofmeasure.UomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

@Slf4j
@Controller
public class IngredientController {
    RecipeService recipeService;
    IngredientService ingredientService;
    UomService uomService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UomService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
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

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String getUpdateIngredient(@PathVariable String recipeId,
                                      @PathVariable String id,
                                      Model model){
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        model.addAttribute("uomList", uomService.findAll());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String postUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String getAddNewIngredient(@PathVariable String recipeId, Model model){
        //todo raise exception if null.
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList", uomService.findAll());
        return "recipe/ingredient/newingredient";
    }
}
