package guru.springframework.controllers;

import guru.springframework.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("category")
    public String getCategories(Model model) {

        model.addAttribute("categories", categoryService.getCategories());
        return "category";
    }
}
