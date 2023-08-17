package guru.springframework.controller;

import guru.springframework.model.Category;
import guru.springframework.model.Recipe;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {
    private List<Recipe> recipeList = new ArrayList<>();
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }
    @RequestMapping({"/",""})
    public String indexHandler(){
        Optional<Category> american = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        System.out.println(american.get().getId());
        System.out.println(teaspoon.get().getId());
        return "index";
    }
    @RequestMapping("/guacamole")
    public String recipeHandler(Model model){
        recipeRepository.findAll().iterator().forEachRemaining(recipe -> {
            recipeList.add(recipe);
        });
        model.addAttribute("recipes",recipeList);
        return "index";

    }
}
