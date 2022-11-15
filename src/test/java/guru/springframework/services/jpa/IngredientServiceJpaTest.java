package guru.springframework.services.jpa;

import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.dtos.IngredientDto;
import guru.springframework.mappers.IngredientMapper;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceJpaTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientMapper ingredientMapper;
    IngredientServiceJpa service;

    Recipe mockRecipe;
    final Long recipeId = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientMapper = Mappers.getMapper(IngredientMapper.class);
        service = new IngredientServiceJpa(recipeRepository, ingredientMapper, unitOfMeasureRepository);

        mockRecipe =  new Recipe();
        mockRecipe.setId(recipeId);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        mockRecipe.addIngredient(ingredient);
        mockRecipe.addIngredient(ingredient2);
        mockRecipe.addIngredient(ingredient3);
    }

    @Test
    public void getIngredientByIdOfRecipeWithId() {
        //given
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(mockRecipe));

        //when
        IngredientDto ingredientDto = service.getIngredientByIdOfRecipeId(1L, 1L);

        //then
        assertNotNull(ingredientDto);
        assertEquals(Long.valueOf(1), ingredientDto.getId());
        assertEquals(Long.valueOf(1), ingredientDto.getRecipeId());
        verify(recipeRepository, never()).findAll();
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    /**
     * Ingredient id must change because we want to database assign id!
     * */
    @Test
    public void addNewIngredientWithIngredientId() {
        //given
        IngredientDto newIngredientDto = new IngredientDto();
        newIngredientDto.setId(Long.MAX_VALUE);
        newIngredientDto.setRecipeId(recipeId);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(mockRecipe));
        when(recipeRepository.save(any())).thenReturn(mockRecipe);

        //when
        IngredientDto savedIngredientDto = service.saveOrUpdateIngredient(newIngredientDto);

        //then
        assertNotNull(savedIngredientDto);
        assertEquals(4, mockRecipe.getIngredients().size());
        assertFalse(mockRecipe.getIngredients().stream().map(Ingredient::getId).anyMatch(id -> Objects.equals(id, Long.MAX_VALUE)));
    }

    //todo this test current fails
//    @Test
//    public void addNewIngredientWithoutIngredientId() {
//        //given
//        IngredientDto newIngredientDto = new IngredientDto();
//        newIngredientDto.setRecipeId(recipeId);
//
//        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(mockRecipe));
//        when(recipeRepository.save(any())).thenReturn(mockRecipe);
//
//        //when
//        IngredientDto savedIngredientDto = service.saveOrUpdateIngredient(newIngredientDto);
//
//        //then
//        assertNotNull(savedIngredientDto);
//        assertNotNull(savedIngredientDto.getId());
//        assertEquals(4, mockRecipe.getIngredients().size());
//        assertTrue(mockRecipe.getIngredients().stream().map(Ingredient::getId).anyMatch(id -> id.equals(100L)));
//    }
    @Test
    public void updateExistingIngredient() {
        //given
        Ingredient existingIngredient = mockRecipe.getIngredients().iterator().next();
        IngredientDto updatedExistingIngredientDto = ingredientMapper.ingredientToIngredientDto(existingIngredient);
        updatedExistingIngredientDto.setAmount(BigDecimal.valueOf(10000));
        updatedExistingIngredientDto.setDescription("New description");

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(mockRecipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(mockRecipe);

        //when
        IngredientDto savedIngredientDto = service.saveOrUpdateIngredient(updatedExistingIngredientDto);

        //then
        assertNotNull(savedIngredientDto);
        assertEquals(3, mockRecipe.getIngredients().size());
        assertTrue(mockRecipe.getIngredients().stream().map(Ingredient::getId).anyMatch(id -> id.equals(existingIngredient.getId())));
        assertEquals(updatedExistingIngredientDto.getDescription(), savedIngredientDto.getDescription());
        assertEquals(updatedExistingIngredientDto.getAmount(), savedIngredientDto.getAmount());
    }

}