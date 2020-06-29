package guru.springframework.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {

        final Recipe spicyChickenTacos = Recipe.builder()
                .id(2l)
                .cookTime(10)
                .description("Spicy chicken tacos")
                .servings(4)
                .prepTime(30)
                .build();

        final Recipe guacamole = Recipe.builder()
                .id(43l)
                .description("Guacamole")
                .prepTime(15)
                .cookTime(0)
                .servings(5)
                .build();

        final HashSet<Recipe> recipes = new HashSet<>();
        recipes.add(guacamole);
        recipes.add(spicyChickenTacos);

        category = Category.builder()
                .id(1l)
                .description("Mexican")
                .recipes(recipes)
                .build();
    }

    @Test
    public void getIdShouldReturn1() {
        Long expected = 1l;
        Assert.assertEquals(expected, category.getId());
    }

    @Test
    public void getDescriptionShouldReturnMexican() {
        String expected = "Mexican";
        Assert.assertEquals(expected, category.getDescription());
    }

    @Test
    public void getRecipes() {
        int expected = 2;
        Assert.assertEquals(expected, category.getRecipes().size());
    }
}
