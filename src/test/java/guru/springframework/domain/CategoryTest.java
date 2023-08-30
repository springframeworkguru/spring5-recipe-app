package guru.springframework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;
    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        Long testId = 4l;
        category.setId(testId);
        assertEquals(testId, category.getId());
    }

    @Test
    void getDescription() {
        String description = "Test Description";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }
}