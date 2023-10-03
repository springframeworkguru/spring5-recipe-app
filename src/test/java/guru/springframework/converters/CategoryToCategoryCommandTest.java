package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    public static final Long idValue = 1L;
    public static final String description = "description";
    CategoryToCategoryCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() throws Exception {
        Category category = new Category();
        category.setId(idValue);
        category.setDescription(description);

        CategoryCommand categoryCommand = converter.convert(category);

        assertEquals(idValue,categoryCommand.getId());
        assertEquals(description,categoryCommand.getDescription());
    }
}