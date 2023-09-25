package guru.springframework.converters;

import guru.springframework.command.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final String TEST_CATEGORY = "Test Category";
    public static final long ID = 1L;
    CategoryCommandToCategory categoryCommandToCategory;
    @BeforeEach
    void setUp() {
        categoryCommandToCategory = new CategoryCommandToCategory();
    }

    @Test
    void testNull(){
        Category category = categoryCommandToCategory.convert(null);
        assertNull(category);
    }

    @Test
    void testNotNull(){
        Category category = categoryCommandToCategory.convert(new CategoryCommand());
        assertNotNull(category);
    }

    @Test
    void testConvert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(TEST_CATEGORY);
        Category category = categoryCommandToCategory.convert(categoryCommand);
        assertNotNull(category);
        assertEquals(ID, category.getId());
        assertEquals(TEST_CATEGORY, category.getDescription());
    }
}