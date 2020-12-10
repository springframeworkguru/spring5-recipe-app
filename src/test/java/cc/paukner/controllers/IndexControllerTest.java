package cc.paukner.controllers;

import cc.paukner.domain.Category;
import cc.paukner.domain.UnitOfMeasure;
import cc.paukner.repositories.CategoryRepository;
import cc.paukner.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(categoryRepository, unitOfMeasureRepository);
    }

    @Test
    public void getIndexPage() {
        final String american = "American";
        final String teaspoon = "Teaspoon";
        when(categoryRepository.findByDescription(american)).thenReturn(Optional.of(Category.builder().id(4L).build()));
        when(unitOfMeasureRepository.findByDescription(teaspoon)).thenReturn(Optional.of(UnitOfMeasure.builder().id(5L).build()));

        String view = indexController.getIndexPage(model);
        assertEquals("index", view);
        verify(categoryRepository, times(1)).findByDescription(american);
        verify(unitOfMeasureRepository, times(1)).findByDescription(teaspoon);
        verify(model, times(1)).addAttribute(eq("cat_american_id"), anyLong());
        verify(model, times(1)).addAttribute(eq("uom_teaspoon_id"), anyLong());
    }
}
