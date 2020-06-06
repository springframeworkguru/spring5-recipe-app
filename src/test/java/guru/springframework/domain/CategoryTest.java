package guru.springframework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        System.out.println("set up");
        category = new Category();
    }

    @Test
    void getId() throws Exception {
        System.out.println("test get id");
        Long idValue = 4L;
        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    void getDescription() throws Exception {
    }

    @Test
    void getRecipes() throws Exception {
    }
}