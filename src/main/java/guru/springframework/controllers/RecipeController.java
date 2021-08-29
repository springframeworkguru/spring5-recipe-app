package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.recipe.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"recipe/show/{id}", "/recipe/{id}/show"})
    public String getRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.getById(Long.valueOf(id)));

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model m){
        m.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/show/" + savedCommand.getId();
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

    @GetMapping("/recipe/{id}/imageupload")
    public String getUpdateRecipeImage(@PathVariable String id){
        return "recipe/imageupload";
    }


}
