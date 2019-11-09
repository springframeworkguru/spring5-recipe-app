package guru.springframework.controller;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<Category> category = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUnit("Cup");

        System.out.println("Cat Id is: " + category.get().getId());
        System.out.println("UOM ID is: " + unitOfMeasure.get().getId());
        return "index";
    }
}
