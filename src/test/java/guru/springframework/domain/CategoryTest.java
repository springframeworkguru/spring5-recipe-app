package guru.springframework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        Long id = 4L;
        category.setId(id);
        assertEquals(id, category.getId());
    }

    @Test
    void getDescription() {
        String description = "Some Text";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

    @Test
    void getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        category.setRecipes(recipes);
        Set<Recipe> extractedRecipes = category.getRecipes();
        assertAll("recipes", ()-> assertEquals(0, extractedRecipes.size()));
    }
}