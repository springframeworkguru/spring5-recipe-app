package guru.springframework.controllers;

import guru.springframework.model.Category;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.MasureOfRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepo;
    private MasureOfRepository measRepo;

    public IndexController(CategoryRepository categoryRepo, MasureOfRepository measRepo) {
        this.categoryRepo = categoryRepo;
        this.measRepo = measRepo;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
       Optional<Category> category = categoryRepo.findById(1L);
       Optional<UnitOfMeasure> UitOfMeasure = measRepo.findById(1L);

       System.out.println(category.get().getDescription());
        System.out.println(UitOfMeasure.get().getUom());

        // Optional<T> findById(ID var1);

        return "index";
    }
}
