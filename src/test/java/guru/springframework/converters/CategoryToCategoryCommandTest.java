package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final String CATEGORY_DESCRIPTION = "Test";
    public static final Long CATEGORY_ID = 1L;
    CategoryToCategoryCommand converter;

    @Before
    public void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void convertWithNullCategory_ShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertWithCategory_ShouldConvertToCategoryCommand() {
        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setDescription(CATEGORY_DESCRIPTION);

        CategoryCommand command = converter.convert(category);

        assertNotNull(command);
        assertEquals(CATEGORY_ID, command.getId());
        assertEquals(CATEGORY_DESCRIPTION, command.getDescription());
    }
}