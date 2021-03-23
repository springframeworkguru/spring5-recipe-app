package guru.springframework.controllers;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class indexControllerTest {

    RecipeService recipeService;


    @Before
    public void setUp() throws Exception{

    }

    @Test
    public void getIndexPage() throws Exception{

    }
    
}
