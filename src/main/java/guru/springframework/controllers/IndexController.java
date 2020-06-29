package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @RequestMapping({"/", "/index", "/index.html"})
    public String getIndexPage() {

        Optional<Category> optionalCategory = this.categoryRepository.findCategoryByDescription("Italian");
        Optional<UnitOfMeasure> optionalUnitOfMeasure = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");

        log.debug("Category id " + optionalCategory.get().getId());
        log.debug("UOM id " + optionalUnitOfMeasure.get().getId());

        return "index";

    }
}
