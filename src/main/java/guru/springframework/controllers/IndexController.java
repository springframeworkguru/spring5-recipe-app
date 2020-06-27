package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
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

    @RequestMapping({"/", "/index", "/index.html"})
    public String getIndexPage() {

        Optional<Category> optionalCategory = this.categoryRepository.findCategoryByDescription("Italian");
        Optional<UnitOfMeasure> optionalUnitOfMeasure = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");

        System.out.println("Category id " + optionalCategory.get().getId());
        System.out.println("UOM id " + optionalUnitOfMeasure.get().getId());

        return "index";

    }
}
