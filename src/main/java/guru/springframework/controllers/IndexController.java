package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public String indexPage(Model model) {

        String categoryDescription = categoryRepository.findByDescription("Italian").map(Category::getDescription).orElse(null);
        String uomDesc = unitOfMeasureRepository.findByDescription("Teaspoon").map(UnitOfMeasure::getDescription).orElse(null);

        System.out.println("Cat desc: " + categoryDescription);
        System.out.println("Uom desc: " + uomDesc);

        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
