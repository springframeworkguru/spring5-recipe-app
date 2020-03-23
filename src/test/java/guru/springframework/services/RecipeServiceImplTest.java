package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

public class RecipeServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;


    RecipeServiceImpl recipeService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Set<Recipe> recipeDatas = new HashSet<>();
        recipeDatas.add(new Recipe());

        Mockito.when(recipeRepository.findAll()).thenReturn(recipeDatas);

        Set<Recipe> recipes = recipeService.getRecipes();

        Assert.assertEquals(1, recipes.size());

        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}
