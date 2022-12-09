package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

  private RecipeService recipeService;
  public IndexController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }


  @RequestMapping({"", "/", "index", "index.html"})
  public String getIndexPage(Model model) {
    log.debug("Getting Index page");
    model.addAttribute("recipes", recipeService.getRecipes());
    return "index";
  }
}
