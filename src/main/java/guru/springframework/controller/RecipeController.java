package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping("/recipe/show/{id}")
    public String showById(@PathVariable Long id, Model model){
        model.addAttribute("recipe",recipeService.findById(id));
        return "recipe/show";
    }
    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";

    }
    @PostMapping("recipe")
    public String updateOrSave(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand saveRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/show/"+saveRecipeCommand.getId();
    }
    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id,Model model){
        model.addAttribute("recipe",recipeService.findCommandById(id));
        return "recipe/recipeform";

    }
    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id){
        recipeService.deleteById(id);
        return "redirect:/";

    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundHandler(Exception exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception",exception);
        return modelAndView;
    }
}
