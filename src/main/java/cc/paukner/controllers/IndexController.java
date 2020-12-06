package cc.paukner.controllers;

import cc.paukner.domain.Category;
import cc.paukner.domain.UnitOfMeasure;
import cc.paukner.repositories.CategoryRepository;
import cc.paukner.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping({"", "/"})
    public String getIndexPage(Model model) {
        Optional<Category> category = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category 'American' id is " + category.get().getId());
        System.out.println("Unit of measure 'Teaspoon' id is " + unitOfMeasure.get().getId());

        model.addAttribute("cat_american_id", category.get().getId());
        model.addAttribute("uom_teaspoon_id", unitOfMeasure.get().getId());

        return "index";
    }
}
