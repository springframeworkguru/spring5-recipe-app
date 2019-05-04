package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){

        Optional<Category> categoryOptional = categoryRepository.findCategoryByDescription("Mexican");
        //Optional<Category> categoryOptional = categoryRepository.findCategoryByDescription("Mexican2");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findUnitOfMeasureByDescription("Ounce");
        //Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findUnitOfMeasureByDescription("Ounce3");

        if (categoryOptional.isPresent()) {
            model.addAttribute("hasCategory", "1");
            categoryOptional.ifPresent(o -> model.addAttribute("category", o));
        } else {
            model.addAttribute("hasCategory", "0");
        }
        if (unitOfMeasureOptional.isPresent()) {
            model.addAttribute("hasUOM", "1");
            unitOfMeasureOptional.ifPresent(o -> model.addAttribute("unitOfMeasure", o));
        } else {
            model.addAttribute("hasUOM", "0");
        }

        return "index";
    }
}
