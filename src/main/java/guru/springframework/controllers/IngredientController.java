package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable("id") String id, Model model) {
        model.addAttribute("recipe",
                recipeService
                        .findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable("recipeId") String recipeId,
                                 @PathVariable("id") String id, Model model) {
        IngredientCommand ingredientCommand = ingredientService.
                findByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                        Long.valueOf(id));

        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/ingredient/show";

    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredientForm(@PathVariable String recipeId, Model model) {

        // To make sure we have a good Id value
        RecipeCommand recipe = recipeService.findCommandById(Long.valueOf(recipeId));

        //todo exception if recipe is not found with the id


        //set up an ingredient for form
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredient/ingredientform";

    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable("recipeId") String recipeId,
                                   @PathVariable("id") String id,
                                   Model model) {

        model.addAttribute("ingredient", ingredientService
                .findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", uomService.listAllUoms());
        return "recipe/ingredient/ingredientform";

    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand saved = ingredientService.saveOrUpdateIngredient(ingredientCommand);

        return "redirect:/recipe/" + saved.getRecipeId() + "/ingredient/" + saved.getId() + "/show";

    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable("recipeId") String recipeId,
                                   @PathVariable("id") String id) {
        log.info("Deleting ingredient "+ id);
        ingredientService.deleteIngredientById(Long.valueOf(recipeId), Long.valueOf(id));

        return "redirect:/recipe/" +recipeId +"/ingredients";
    }


}
