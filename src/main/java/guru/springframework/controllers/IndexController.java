package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {
/*
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;*/
    private final RecipeService recipeService;

    public IndexController(/*CategoryRepository categoryRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository,*/
                           RecipeService recipeService) {
      /*  this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;*/
        this.recipeService = recipeService;
    }


    @GetMapping({"/recipes", "/"})
    public String getRecipes(Model model) {
        log.info("In index controller, Getting recipes using recipe service");
        Set<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
    }


    /*public String getIndexPage(){
        Optional<Category> categoryOptional =
                categoryRepository.findByDescription("American");

        Optional<UnitOfMeasure> unitOfMeasureOptional =
                unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category id is: " + categoryOptional.get().getId());
        System.out.println("Unit Of Measure id is: " + unitOfMeasureOptional.get().getId());

        return "index";
    }*/
}
