package guru.springframework.controllers;

import guru.springframework.command.RecipeCommand;
import guru.springframework.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: harshitasain
 * @Date: 30/09/23
 **/

@Controller
@Slf4j
@RequiredArgsConstructor
public class IngredientController {

    private final RecipeService recipeService;

    @RequestMapping("/recipe/{recipeId}/ingredients")
    @GetMapping
    public String listIngredients(@PathVariable String recipeId, Model model){
        RecipeCommand recipe = recipeService.findCommandById(Long.parseLong(recipeId));
        model.addAttribute("recipe", recipe);
        return "recipe/ingredient/list";
    }
}
