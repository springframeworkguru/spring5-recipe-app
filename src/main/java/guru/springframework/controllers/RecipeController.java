package guru.springframework.controllers;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("")
public class RecipeController {
    RecipeService recipeService;

    @RequestMapping({"/recipe/{id}/show"})
    @GetMapping
    public String getRecipeById(@PathVariable String id, Model model){
        Recipe recipe = recipeService.findById(Long.parseLong(id));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    @GetMapping
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/save")
    @PostMapping
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" +savedRecipe.getId() +"/show" ;
    }

    @RequestMapping("/recipe/{id}/update")
    @PostMapping
    public String updateRecipe(@PathVariable String id, Model model){
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe", recipeCommand );
        return "/recipe/recipeform" ;
    }

    @RequestMapping("/recipe/{id}/delete")
    @GetMapping
    public String deleteRecipe(@PathVariable String id, Model model){
        log.debug("delete id:" + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
