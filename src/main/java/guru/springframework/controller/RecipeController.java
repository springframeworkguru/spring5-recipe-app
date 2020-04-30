package guru.springframework.controller;

import guru.springframework.command.RecipeCommand;
import guru.springframework.models.Recipe;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //@GetMapping("/recipe/show/{id}")
    @RequestMapping("/recipe/show/{id}")
    public String showById(Model model, @PathVariable String id){

        Recipe recipe = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }

    // implement the method that will render the view
    @RequestMapping({"/recipe/new"})
    public String newRecipe(Model model){

        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    // handle the post
    @PostMapping()
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        // redirect to a specific url
        return "redirect:/recipe/show/" + savedCommand.getId();
    }

}
