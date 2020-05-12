package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryCommandToCategoryTest {

    static final Long ID = 123L;
    static final String NAME = "name";
    CategoryCommandToCategory categoryCommandToCategory;

    @Before
    public void setUp() throws Exception {
        categoryCommandToCategory = new CategoryCommandToCategory();
    }

    @Test
    public void convert() {
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setCategoryName(NAME);
        final Category cat = categoryCommandToCategory.convert(categoryCommand);
        assertNotNull(cat);
        assertEquals(ID, cat.getId());
        assertEquals(NAME, cat.getCategoryName());
    }
}
