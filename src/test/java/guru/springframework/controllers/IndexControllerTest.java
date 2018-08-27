package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest{

	IndexController indexController;

	@Mock
	Model model;

	@Mock
	RecipeServiceImpl recipeService;

	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks( this );
		indexController = new IndexController( recipeService );
	}

	@Test public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

		mockMvc.perform(get("/"))
						.andExpect( status().isOk() )
						.andExpect( view().name( "index" ) );
	}

	@Test public void getIndexPage(){

		Set recipesData = new HashSet();
		Recipe recipe;
		recipe = new Recipe();
		recipe.setId( 1L );
		recipesData.add(recipe);
		recipe = new Recipe();
		recipe.setId( 2L );
		recipesData.add(recipe);

		when(recipeService.getRecipes()).thenReturn( recipesData );
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass( Set.class );

		//when
		String result = indexController.getIndexPage( model );

		//then
		String expected_result = "index";
		assertEquals( result, expected_result );

		verify(recipeService, times(1)).getRecipes();
//		verify(model, times(1)).addAttribute( "recipes", recipesData );
		verify(model, times(1)).addAttribute( eq("recipes"), anySet() );
		verify(model, times(1)).addAttribute( eq("recipes"), argumentCaptor.capture() );
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals( 2, setInController.size() );
	}
}