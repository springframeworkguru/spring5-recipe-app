package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipe(@PathVariable("id") String id, Model model) {
        Long recipeId = getValidatedId(id);
        model.addAttribute("recipe", recipeService.findById(recipeId));
        return "recipe/show";
    }


    @GetMapping("/recipe/new")
    public String showForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand,
                               BindingResult result) {
        if (result.hasErrors()) {
            log.error("Validation Error on Fields: ");
            result.getAllErrors().forEach(objectError -> log.info(objectError.toString()));
            return "recipe/recipeform";
        }
        log.info("Saving the recipe command object to repository" + recipeCommand);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }


    @GetMapping("/recipe/{id}/update")
    public String viewUpdateForm(@PathVariable("id") String id, Model model) {
        log.info("fetching recipe command object from repository with ID: " + id);
        Long recipeId = getValidatedId(id);
        RecipeCommand command = recipeService.findCommandById(recipeId);
        log.info("fetching again command object from repository with ID " + id);
        model.addAttribute("recipe", command);
        log.info("command is  " + command);
        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable("id") String id) {
        log.info("Deleting recipe by: " + id);

        Long recipeId = getValidatedId(id);
        recipeService.deleteById(recipeId);
        log.info("Deleted " + id);
        return "redirect:/recipes";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception exception) {
        log.error("Handling Not Found exception");
        log.error(exception.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("exceptionNotFound");
        mav.addObject("exception", exception);
        return mav;
    }


    public Long getValidatedId(String id) {
        Long recipeId;
        try {
            recipeId = Long.valueOf(id);
        } catch (NumberFormatException nx) {
            throw new NumberFormatException("Id '" + id + "' is invalid." +
                    " Please enter a valid Recipe Id in the URL");
        }
        return recipeId;
    }
}
