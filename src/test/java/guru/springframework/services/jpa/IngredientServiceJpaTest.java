package guru.springframework.services.jpa;

import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.dtos.IngredientDto;
import guru.springframework.mappers.IngredientMapper;
import guru.springframework.mappers.IngredientMapperImpl;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceJpaTest {

    @Mock
    RecipeRepository recipeRepository;

    IngredientMapper ingredientMapper;
    IngredientServiceJpa service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientMapper = Mappers.getMapper(IngredientMapper.class);
        service = new IngredientServiceJpa(recipeRepository, ingredientMapper);
    }

    @Test
    public void getIngredientByIdOfRecipeWithId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        //when
        IngredientDto ingredientDto = service.getIngredientByIdOfRecipeId(1L, 1L);

        //then
        assertNotNull(ingredientDto);
        assertEquals(Long.valueOf(1), ingredientDto.getId());
        assertEquals(Long.valueOf(1), ingredientDto.getRecipeId());
        verify(recipeRepository, never()).findAll();
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}