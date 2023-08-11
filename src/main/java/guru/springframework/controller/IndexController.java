package guru.springframework.controller;

import guru.springframework.model.Category;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }
    @RequestMapping({"/",""})
    public String indexHandler(){
        Optional<Category> american = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        System.out.println(american.get().getId());
        System.out.println(teaspoon.get().getId());
        return "index";
    }
}
