package guru.springframework.services;

import guru.springframework.domain.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getCategories();

    Category findById(Long id);
}
