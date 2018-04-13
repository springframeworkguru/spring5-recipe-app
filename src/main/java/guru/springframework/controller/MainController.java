package guru.springframework.controller;

import guru.springframework.model.Category;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.service.RecipeService;
import guru.springframework.service.RecipeServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class MainController {
    private RecipeServiceInter recipeService;
    private List<Ingredient> ingredients=new ArrayList<>();

    @RequestMapping("/all")
    public String all(Model model){
        List<Recipe> recipes=new ArrayList<>();
        recipes=recipeService.getListRecipe();
        model.addAttribute("recipes",recipes);
        return "index";
    }

    @RequestMapping("/")
    public ModelAndView welcome() {
        ModelAndView modelAndView=new ModelAndView();
        Ingredient ingredient1=new Ingredient();
        ingredient1.setDescription("salt");
        Ingredient ingredient2=new Ingredient();
        ingredient2.setDescription("cumen");
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        modelAndView.addObject("ingredients",ingredients);
        modelAndView.setViewName("hello");

        return modelAndView;
    }

    @Autowired
    public void setRecipeService(RecipeServiceInter recipeService) {
        this.recipeService = recipeService;
    }
}
