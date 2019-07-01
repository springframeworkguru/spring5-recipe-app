package com.recipe.recipeproject.repositories;

import com.recipe.recipeproject.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
