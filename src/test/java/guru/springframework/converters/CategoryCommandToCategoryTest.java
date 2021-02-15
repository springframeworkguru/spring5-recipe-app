package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final Long CATEGORY_ID = 1L;
    CategoryCommandToCategory converter;

    @Before
    public void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void convertWhenNullCategoryCommand_ShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertCategoryCommand_ShouldReturnCategory() {
        CategoryCommand command = new CategoryCommand();
        command.setId(CATEGORY_ID);
        command.setDescription("Test");

        Category category = converter.convert(command);

        assertNotNull(category);
        assertEquals(CATEGORY_ID, category.getId());
        assertEquals("Test", category.getDescription());
    }
}