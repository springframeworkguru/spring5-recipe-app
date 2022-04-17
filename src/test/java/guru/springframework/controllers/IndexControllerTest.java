package guru.springframework.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServiceImpl;

public class IndexControllerTest {

	private IndexController indexController;
	@Mock
	private RecipeServiceImpl recipeService;

	@Mock
	Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
	}
	
	@Test
	public void testMockMVC() throws Exception {
		MockMvc mockMvc=MockMvcBuilders.standaloneSetup(indexController).build() ;
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	public void testGetIndexPage() {

		// given
		Set<Recipe> recipes = new HashSet<>();
		Recipe recipe = new Recipe();
		recipe.setId(1234L);
		Recipe recipe2 = new Recipe();
		recipe2.setId(43534543L);
		recipes.add(recipe);
		recipes.add(recipe2);

		when(recipeService.getRecipes()).thenReturn(recipes);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		// when
		String result = indexController.getIndexPage(model);
		
		// then
		assertEquals("index", result);
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> recipesInController = argumentCaptor.getValue();
		assertEquals(2, recipesInController.size());

	}

}
