package guru.springframework.controllers;

import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 6/1/17.
 */
@Slf4j
@Controller
public class IndexController {

    private CategoryRepository categoryRepo;
    private UnitOfMeasureRepository measRepo;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepo, UnitOfMeasureRepository measRepo, RecipeService recipeService) {
        this.categoryRepo = categoryRepo;
        this.measRepo = measRepo;
        this.recipeService = recipeService;

    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){

        model.addAttribute("recipes",recipeService.getRecipes());
        // Optional<T> findById(ID var1);

        return "index";
    }




}
