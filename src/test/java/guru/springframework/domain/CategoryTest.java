package guru.springframework.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;

    @BeforeEach
    public void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        category.setId(4L);
        assertEquals(4L,category.getId());
    }

    @Test
    void getDescription() {
        category.setDescription("Should Pass");
        assertEquals("Should Pass", category.getDescription());

        category.setDescription("Should Fail");
        assertFalse("ShouldFail".equals(category.getDescription()),
                "Test should pass as the boolean condition is false");
    }


}
