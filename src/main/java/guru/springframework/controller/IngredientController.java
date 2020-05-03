package guru.springframework.controller;

import guru.springframework.command.IngredientCommand;
import guru.springframework.command.RecipeCommand;
import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.models.Recipe;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

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
    public String getIngredientList(Model model, @PathVariable String recipeId){

        log.debug("Getting Ingredients for RecipeId : " + recipeId);

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String getByRecipeIdAndIngredientId(Model model, @PathVariable String recipeId, @PathVariable String ingredientId){

        log.debug("Getting Ingredient for ingredientId :" + ingredientId + " for RecipeId : " + recipeId);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        model.addAttribute("ingredient", ingredientCommand);

        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(Model model, @PathVariable String recipeId, @PathVariable String ingredientId){

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        model.addAttribute("ingredient", ingredientCommand);

        model.addAttribute("uomList", unitOfMeasureService.listAllUOM());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(Model model, @PathVariable String recipeId){

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        if(recipeCommand==null){
            // ToDo
            log.error("recipe not found for id " + recipeId);
            throw new RuntimeException("recipe not found for id " + recipeId);
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", ingredientCommand);

        model.addAttribute("uomList", unitOfMeasureService.listAllUOM());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId){

        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@PathVariable String recipeId, IngredientCommand ingredientCommand){

        ingredientService.saveIngredientCommand(ingredientCommand);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
