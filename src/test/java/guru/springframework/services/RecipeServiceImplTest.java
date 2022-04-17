package guru.springframework.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	private RecipeServiceImpl recipeService;
	@Mock
	private RecipeRepository recipeRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void testGetRecipes() {

		Set<Recipe> expectedRecipes = new HashSet<>();

		Recipe recipe = new Recipe();
		expectedRecipes.add(recipe);

		when(recipeService.getRecipes()).thenReturn(expectedRecipes);
		Set<Recipe> recipes = recipeService.getRecipes();

		assertTrue(recipes.size() > 0);
		verify(recipeRepository, times(1)).findAll();
	}

}
