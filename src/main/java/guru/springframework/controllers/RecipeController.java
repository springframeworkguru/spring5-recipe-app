package guru.springframework.controllers;

import guru.springframework.dtos.RecipeDto;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/show/{id}")
    public String showById(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @RequestMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeDto());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("")
    public String newOrUpdate(@ModelAttribute RecipeDto recipeDto){
        RecipeDto savedRecipeDto = recipeService.saveRecipeDto(recipeDto);
        return "redirect:/recipe/show/" + savedRecipeDto.getId();
    }

}


