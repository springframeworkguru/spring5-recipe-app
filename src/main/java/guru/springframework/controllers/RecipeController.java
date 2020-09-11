package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Recipe;
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

    @RequestMapping("/recipes")
    public String getRecipes(Model model){
         model.addAttribute("recipes",this.recipeService.getRecipes());
        return "index";
    }

    @RequestMapping("/recipe/{id}/show")
    public String showRecipe(@PathVariable String id,Model model){
        Recipe recipe = this.recipeService.findById(id);
        model.addAttribute("recipe",recipe);
        System.out.println(recipe.getIngredients().size());

        return "/recipe/show";
    }

    @RequestMapping({"/recipe/new","/recipe/{id}/new"})
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "/recipe/recipeForm";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id,Model model ){
        model.addAttribute("recipe",recipeService.findCommandById(id));
        return "/recipe/recipeForm";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand command = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/"+command.getId()+"/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String delete(@PathVariable String id){
        recipeService.deleteById(id);
        return "redirect:/recipes";
    }
}
