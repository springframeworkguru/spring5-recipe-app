package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.RecipeNotFoundException;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public String postRecipe(@Valid @ModelAttribute("recipe") RecipeCommand command,
                             BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().stream().map(ObjectError::toString).forEach(log::debug);
            return "recipe/recipeform";
        }
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
}
