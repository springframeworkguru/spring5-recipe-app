package guru.springframework.services;

import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getCategories() {

        Set <Category> categories = new HashSet<>();

        categoryRepository.findAll().iterator().forEachRemaining(categories::add);

        return categories;
    }

    @Override
    public Category findById(Long id){

        Optional<Category> cOptional = categoryRepository.findById(id);
        if(!cOptional.isEmpty()) {
            throw new RuntimeException("Categoria não econtrada!");
        }
        return cOptional.get();
    }

}
