package guru.springframework.domain;

import lombok.Builder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 06/08/2020 - 11:26 PM
 * spring5-recipe-app
 *
 * @author cliffordbechtel
 */
public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4l;

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}