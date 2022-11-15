package guru.springframework.controllers;

import guru.springframework.dtos.RecipeDto;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/{id}/show")
    public String showById(@PathVariable Long id, Model model){
        log.debug("Showing details of recipe with id " + id);
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }


    @GetMapping("/new")
    public String newRecipe(Model model){
        log.debug("Returning form to create new recipe");
        model.addAttribute("recipe", new RecipeDto());
        return "recipe/recipeform";
    }


    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        log.debug("Retrieving from db recipe to update in form");
        model.addAttribute("recipe", recipeService.getRecipeDtoById(Long.valueOf(id)));
        return "recipe/recipeform";
    }


    @PostMapping("")
    public String newOrUpdate(@ModelAttribute RecipeDto recipeDto){
        log.debug("Creating new or updating existing recipe");
        RecipeDto savedRecipeDto = recipeService.saveRecipeDto(recipeDto);
        return "redirect:/recipe/" + savedRecipeDto.getId() + "/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}


