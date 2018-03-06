package guru.springframework.service;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Recipe;

public interface RecipeService extends JpaRepository<Recipe, Long> {

}
