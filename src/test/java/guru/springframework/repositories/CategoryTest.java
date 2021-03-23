package guru.springframework.repositories;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.domain.Category;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;

/**
 * Created by jt on 6/17/17.
 */
@Slf4j
public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, category.getId());
        log.info("TESTE de GETID");
        
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }

}