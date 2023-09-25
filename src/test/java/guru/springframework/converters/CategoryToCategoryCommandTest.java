package guru.springframework.converters;

import guru.springframework.command.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final String TEST_CATEGORY = "Test Category";
    public static final long ID = 1L;
    CategoryToCategoryCommand categoryToCategoryCommand;
    @BeforeEach
    void setUp() {
        categoryToCategoryCommand = new CategoryToCategoryCommand();
    }

    @Test
    void testNull(){
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(null);
        assertNull(categoryCommand);
    }

    @Test
    void testNotNull(){
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(new Category());
        assertNotNull(categoryCommand);
    }

    @Test
    void testConvert() {
        Category category = new Category();
        category.setId(ID);
        category.setDescription(TEST_CATEGORY);
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);
        assertNotNull(categoryCommand);
        assertEquals(ID, categoryCommand.getId());
        assertEquals(TEST_CATEGORY, categoryCommand.getDescription());
    }
}