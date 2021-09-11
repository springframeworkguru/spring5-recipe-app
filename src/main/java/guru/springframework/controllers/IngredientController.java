package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.ingredient.IngredientService;
import guru.springframework.services.recipe.RecipeService;
import guru.springframework.services.unitofmeasure.UomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;
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

    @GetMapping("/recipe/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model){
        log.debug("Getting ingredient list for recipe id: "+ id);
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/ingredient/list";
    }
    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String getOneIngredient(@PathVariable String recipeId,
                                   @PathVariable String id,
                                   Model model){
        log.debug("Getting ingredient with id " + id + " from recipe with id "+ recipeId);

        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String getUpdateIngredient(@PathVariable String recipeId,
                                      @PathVariable String id,
                                      Model model){
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

        model.addAttribute("uomList", uomService.findAll());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String postUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String getAddNewIngredient(@PathVariable String recipeId, Model model){
        //todo raise exception if null.
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList", uomService.findAll());
        return "recipe/ingredient/newingredient";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String getDeleteIngredient(@PathVariable String recipeId,
                                      @PathVariable String id){
        ingredientService.deleteIngredientFromRecipe(recipeId, id);

        return "redirect:/recipe/"+recipeId+"/ingredients";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling not found error");

        return new ModelAndView("404error", "exception", exception);
    }
}
