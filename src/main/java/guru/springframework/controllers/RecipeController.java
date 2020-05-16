package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.RecipeNotFoundException;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequestMapping("/recipe")
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}/show")
    public String showRecipeById(@PathVariable Long id, Model model) {
        final Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping("")
    public String postRecipe(@ModelAttribute RecipeCommand command) {
        final RecipeCommand savedCommand = recipeService.saveRecipeFromCommandObject(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        final RecipeCommand recipeCommand = recipeService.findCommandById(id);
        model.addAttribute("recipe", recipeCommand);
        return "recipe/recipeform";
    }

    @GetMapping("/{id}/delete")
    public String deleteRecipeById(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecipeNotFoundException.class)
    public ModelAndView fourOfourHandler(Exception exception) {
        final ModelAndView modelAndView = new ModelAndView("errors/404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView numberFormatExceptionHandler(Exception exception) {
        final ModelAndView modelAndView = new ModelAndView("errors/400error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
