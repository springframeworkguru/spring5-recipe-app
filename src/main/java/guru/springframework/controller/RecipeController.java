package guru.springframework.controller;

import guru.springframework.command.RecipeCommand;
import guru.springframework.models.Recipe;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(Model model, @PathVariable String id){

        Recipe recipe = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }

    // load an empty recipe form for creat
    @GetMapping("/recipe/new")
    public String loadNewRecipeForm(Model model){

        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    // load a filed form with recipe
    @GetMapping("/recipe/{id}/update")
    public String loadFiledRecipeFormForUpdate(Model model, @PathVariable String id){
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        model.addAttribute("recipe", recipeCommand);

        return "recipe/recipeform";
    }

    // handle the post
    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        // redirect to a specific url
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        log.debug("deleteRecipe :" + id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
