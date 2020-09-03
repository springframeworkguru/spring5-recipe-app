package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public DataInitializer(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Recipe guacamoleRecipe = getGuacamoleRecipe();
        recipeRepository.save(guacamoleRecipe);
    }

    private Recipe getGuacamoleRecipe() throws IOException {
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setServings(2);
        String mexicanCategoryDescription = "Mexican";
        guacamoleRecipe.getCategories().add(categoryRepository.findByDescription(mexicanCategoryDescription).orElseThrow(() -> new RuntimeException("There is no such category: " + mexicanCategoryDescription)));
        guacamoleRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setImage(readBytesForImage("src/main/resources/images/guacamole.jpg"));

        Set<Ingredient> guacamoleIngredients = new HashSet<>();
        Ingredient avocado = getIngredient(2, "Ripe avocado", guacamoleRecipe);
        Ingredient salt = getIngredientWithUnitOfMeasure(0.25, "salt", "Teaspoon", guacamoleRecipe);
        Ingredient freshLimeJuice = getIngredientWithUnitOfMeasure(1, "fresh lime juice", "Tablespoon", guacamoleRecipe);
        Ingredient mincedRedOnion = getIngredientWithUnitOfMeasure(2, "minced red onion", "Tablespoon", guacamoleRecipe);
        Ingredient serranoChilli = getIngredient(2, "Serrano chilli", guacamoleRecipe);
        Ingredient cilantro = getIngredientWithUnitOfMeasure(2, "cilantro", "Tablespoon", guacamoleRecipe);
        Ingredient blackPepper = getIngredientWithUnitOfMeasure(1, "freshly grated black pepper", "Dash", guacamoleRecipe);
        Ingredient tomato = getIngredient(0.5, "ripe tomato", guacamoleRecipe);
        Ingredient redRadishes = getIngredient(1, "red radishes", guacamoleRecipe);
        Ingredient tortillaChips = getIngredient(1, "tortilla chips", guacamoleRecipe);

        guacamoleIngredients.add(avocado);
        guacamoleIngredients.add(salt);
        guacamoleIngredients.add(freshLimeJuice);
        guacamoleIngredients.add(mincedRedOnion);
        guacamoleIngredients.add(serranoChilli);
        guacamoleIngredients.add(cilantro);
        guacamoleIngredients.add(blackPepper);
        guacamoleIngredients.add(tomato);
        guacamoleIngredients.add(redRadishes);
        guacamoleIngredients.add(tortillaChips);

        guacamoleRecipe.getIngredients().addAll(guacamoleIngredients);

        Notes guacamoleRecipeNotes = new Notes();
        guacamoleRecipeNotes.setRecipe(guacamoleRecipe);
        guacamoleRecipeNotes.setRecipeNotes("Guacamole! Did you know that over 2 billion " +
                "pounds of avocados are consumed each year in the U.S.? (Google it.) That’s over 7" +
                " pounds per person. I’m guessing that most of those avocados go into what has become America’s" +
                " favorite dip, guacamole.");
        guacamoleRecipe.setNotes(guacamoleRecipeNotes);
        return guacamoleRecipe;
    }

    private Ingredient getIngredientWithUnitOfMeasure(double amount, String description, String unitOfMeasure, Recipe recipe) {
        Ingredient ingredient = getIngredient(amount, description, recipe);
        ingredient.setUnitOfMeasure(getUnitOfMeasure(unitOfMeasure));
        return ingredient;
    }

    private UnitOfMeasure getUnitOfMeasure(String unitOfMeasure) {
        if (unitOfMeasureRepository.findByDescription(unitOfMeasure).isPresent()) {
            return unitOfMeasureRepository.findByDescription(unitOfMeasure).get();
        } else {
            throw new RuntimeException("There is no such unit of measure: " + unitOfMeasure);
        }
    }

    private byte[] readBytesForImage(String pathname) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(pathname));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private Ingredient getIngredient(double amount, String description, Recipe recipe) {
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(amount);
        ingredient.setDescription(description);
        ingredient.setRecipe(recipe);
        return ingredient;
    }
}
