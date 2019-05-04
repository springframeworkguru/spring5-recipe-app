package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.aspectj.weaver.Iterators;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    private IngredientRepository ingredientRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;

    public DataLoader(IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadData();
    }

    private Iterable<Recipe> loadData() {

        /*/
        // Query to See Results
        SELECT r.DESCRIPTION, r.difficulty, r.prep_time, r.servings, i.amount, uom.description, i.description FROM RECIPE r
        INNER JOIN INGREDIENT i
            ON r.ID = i.RECIPE_ID
        LEFT JOIN UNIT_OF_MEASURE uom
           ON i.unit_of_measure_id = uom.id
        //*/

        /**
         * Categories
         */
        // Category - Mexican
        Optional<Category> optionalCategoryMexican = categoryRepository.findCategoryByDescription("Mexican");
        if (!optionalCategoryMexican.isPresent()) {
            throw new RuntimeException("Mexican Category not found");
        }
        Category categoryMexican = optionalCategoryMexican.get();

        // Category - Mexican
        Optional<Category> optionalCategoryAmerican = categoryRepository.findCategoryByDescription("American");
        if (!optionalCategoryAmerican.isPresent()) {
            throw new RuntimeException("American Category not found");
        }
        Category categoryAmerican = optionalCategoryAmerican.get();

        /**
         * Units of Measure
         */
        Optional<UnitOfMeasure> optionalTeaspoonUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");
        if (!optionalTeaspoonUom.isPresent()) {
            throw new RuntimeException("No Teaspoon UOM");
        }
        UnitOfMeasure teaspoonUom = optionalTeaspoonUom.get();

        Optional<UnitOfMeasure> optionalTablespoonUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Tablespoon");
        if (!optionalTablespoonUom.isPresent()) {
            throw new RuntimeException("No Tablespoon UOM");
        }
        UnitOfMeasure tablespoonUom = optionalTablespoonUom.get();

        Optional<UnitOfMeasure> optionalDashUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Dash");
        if (!optionalDashUom.isPresent()) {
            throw new RuntimeException("No Dash UOM");
        }
        UnitOfMeasure dashUom = optionalDashUom.get();

        Optional<UnitOfMeasure> optionalCloveUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Clove");
        if (!optionalCloveUom.isPresent()) {
            throw new RuntimeException("No Clove UOM");
        }
        UnitOfMeasure cloveUom = optionalCloveUom.get();

        Optional<UnitOfMeasure> optionalCupUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Cup");
        if (!optionalCupUom.isPresent()) {
            throw new RuntimeException("No Cup UOM");
        }
        UnitOfMeasure cupUom = optionalCupUom.get();

        Optional<UnitOfMeasure> optionalPintUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Pint");
        if (!optionalPintUom.isPresent()) {
            throw new RuntimeException("No Pint UOM");
        }
        UnitOfMeasure pintUom = optionalPintUom.get();

        /**
         * Recipe - Perfect Guacaomole
          */
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Perfect Guacamole");
        recipe1.setPrepTime(10);
        recipe1.setCookTime(10);
        recipe1.setServings(3);
        recipe1.setDifficulty(Difficulty.EASY);
        recipe1.setSource("SimplyRecipes.com");
        recipe1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        String directions = "<ul>\n" +
                "    <li>Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.</li>\n" +
                "    <li>2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)</li>\n" +
                "    <li>3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.</li>\n" +
                "    <li>Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.</li>\n" +
                "    <li>Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.</li>\n" +
                "    <li>4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.</li>\n" +
                "    <li>Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.</li>\n" +
                "</ul>";
        recipe1.setDirections(directions);
        Recipe savedRecipe1 = recipeRepository.save(recipe1);

        // Add Categories
        savedRecipe1.getCategories().add(categoryMexican);
        savedRecipe1.getCategories().add(categoryAmerican);

        // Save
        Recipe savedRecipe1_2 = recipeRepository.save(savedRecipe1);

        /**
         * Ingredients
         */
        Ingredient ingredient1 = new Ingredient("ripe avocados", new BigDecimal(2));
        Ingredient ingredient2 = new Ingredient("Kosher salt", new BigDecimal("0.5"), teaspoonUom);
        Ingredient ingredient3 = new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tablespoonUom);
        Ingredient ingredient4 = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom);
        Ingredient ingredient5 = new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal("1.5"));
        Ingredient ingredient6 = new Ingredient("cilantro (leaves and tender stems), finally chopped", new BigDecimal(2), tablespoonUom);
        Ingredient ingredient7 = new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUom);
        Ingredient ingredient8 = new Ingredient("ripe tomato, seeds and pulp remvoed, chopped", new BigDecimal("0.5"));

        // Add Recipe Relationship
        ingredient1.setRecipe(savedRecipe1_2);
        ingredient2.setRecipe(savedRecipe1_2);
        ingredient3.setRecipe(savedRecipe1_2);
        ingredient4.setRecipe(savedRecipe1_2);
        ingredient5.setRecipe(savedRecipe1_2);
        ingredient6.setRecipe(savedRecipe1_2);
        ingredient7.setRecipe(savedRecipe1_2);
        ingredient8.setRecipe(savedRecipe1_2);

        // Save Ingredients
        Ingredient savedIngredient1 = ingredientRepository.save(ingredient1);
        Ingredient savedIngredient2 = ingredientRepository.save(ingredient2);
        Ingredient savedIngredient3 = ingredientRepository.save(ingredient3);
        Ingredient savedIngredient4 = ingredientRepository.save(ingredient4);
        Ingredient savedIngredient5 = ingredientRepository.save(ingredient5);
        Ingredient savedIngredient6 = ingredientRepository.save(ingredient6);
        Ingredient savedIngredient7 = ingredientRepository.save(ingredient7);
        Ingredient savedIngredient8 = ingredientRepository.save(ingredient8);

        // Add Ingredients to Recipe
        savedRecipe1_2.getIngredients().add(savedIngredient1);
        savedRecipe1_2.getIngredients().add(savedIngredient2);
        savedRecipe1_2.getIngredients().add(savedIngredient3);
        savedRecipe1_2.getIngredients().add(savedIngredient4);
        savedRecipe1_2.getIngredients().add(savedIngredient5);
        savedRecipe1_2.getIngredients().add(savedIngredient6);
        savedRecipe1_2.getIngredients().add(savedIngredient7);
        savedRecipe1_2.getIngredients().add(savedIngredient8);

        // Save Recipe
        Recipe savedRecipe1_3 = recipeRepository.save(savedRecipe1_2);

        /**
         * Recipe - Spicy Grilled Chicken Tacos
         */
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Spicy Grilled Chicken Tacos");
        recipe2.setPrepTime(20);
        recipe2.setCookTime(15);
        recipe2.setServings(5);
        recipe2.setDifficulty(Difficulty.EASY);
        recipe2.setSource("SimplyRecipes.com");
        recipe2.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        String directions2 = "<ul>\n" +
                "   <li>Prepare a gas or charcoal grill for medium-high, direct heat.</li>\n" +
                "   <li>Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.</li>\n" +
                "   <li>Set aside to marinate while the grill heats and you prepare the rest of the toppings.</li>\n" +
                "   <li>Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.</li>\n" +
                "   <li>Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.</li>\n" +
                "   <li>Wrap warmed tortillas in a tea towel to keep them warm until serving.</li>\n" +
                "   <li>Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.</li>\n" +
                "</ul>";
        recipe2.setDirections(directions2);
        Recipe savedRecipe2 = recipeRepository.save(recipe2);

        // Add Categories
        savedRecipe2.getCategories().add(categoryMexican);
        savedRecipe2.getCategories().add(categoryAmerican);

        // Save Recipe
        Recipe savedRecipe2_2 = recipeRepository.save(savedRecipe2);

        /**
         * Ingredients
         */
        Ingredient ingredient9 = new Ingredient("ancho chile powder", new BigDecimal(2), tablespoonUom);
        Ingredient ingredient10 = new Ingredient("dried oregano", new BigDecimal(1), teaspoonUom);
        Ingredient ingredient11 = new Ingredient("dried cumin", new BigDecimal(1), teaspoonUom);
        Ingredient ingredient12 = new Ingredient("sugar", new BigDecimal(1), teaspoonUom);
        Ingredient ingredient13 = new Ingredient("salt", new BigDecimal("0.5"), tablespoonUom);
        Ingredient ingredient14 = new Ingredient("garlic, finely chopped", new BigDecimal(1), cloveUom);
        Ingredient ingredient15 = new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoonUom);
        Ingredient ingredient16 = new Ingredient("fresh squeezed orange juice", new BigDecimal(3), tablespoonUom);
        Ingredient ingredient17 = new Ingredient("olive oil", new BigDecimal(2), tablespoonUom);
        Ingredient ingredient18 = new Ingredient("skinless, boneless chicken thighs", new BigDecimal(5));
        Ingredient ingredient19 = new Ingredient("small corn tortillas", new BigDecimal(8));
        Ingredient ingredient20 = new Ingredient("packed baby arugula", new BigDecimal(3), cupUom);
        Ingredient ingredient21 = new Ingredient("medium, ripe avocados", new BigDecimal(2));
        Ingredient ingredient22 = new Ingredient("radishes, thinly sliced", new BigDecimal(4));
        Ingredient ingredient23 = new Ingredient("cherry tomatoes", new BigDecimal("0.5"), pintUom);
        Ingredient ingredient24 = new Ingredient("red onion, thinly sliced", new BigDecimal("0.25"));
        Ingredient ingredient25 = new Ingredient("roughly chopped cilantro");
        Ingredient ingredient26 = new Ingredient("sour cream", new BigDecimal("0.5"), cupUom);
        Ingredient ingredient27 = new Ingredient("milk", new BigDecimal("0.25"), cupUom);
        Ingredient ingredient28 = new Ingredient("lime, cut into wedges", new BigDecimal(1));

        // Add Recipe Relationship
        ingredient9.setRecipe(savedRecipe2_2);
        ingredient11.setRecipe(savedRecipe2_2);
        ingredient12.setRecipe(savedRecipe2_2);
        ingredient13.setRecipe(savedRecipe2_2);
        ingredient14.setRecipe(savedRecipe2_2);
        ingredient15.setRecipe(savedRecipe2_2);
        ingredient16.setRecipe(savedRecipe2_2);
        ingredient17.setRecipe(savedRecipe2_2);
        ingredient18.setRecipe(savedRecipe2_2);
        ingredient19.setRecipe(savedRecipe2_2);
        ingredient20.setRecipe(savedRecipe2_2);
        ingredient21.setRecipe(savedRecipe2_2);
        ingredient22.setRecipe(savedRecipe2_2);
        ingredient23.setRecipe(savedRecipe2_2);
        ingredient24.setRecipe(savedRecipe2_2);
        ingredient25.setRecipe(savedRecipe2_2);
        ingredient26.setRecipe(savedRecipe2_2);
        ingredient27.setRecipe(savedRecipe2_2);
        ingredient28.setRecipe(savedRecipe2_2);

        // Save Ingredients
        Ingredient savedIngredient9 = ingredientRepository.save(ingredient9);
        Ingredient savedIngredient10 = ingredientRepository.save(ingredient10);
        Ingredient savedIngredient11 = ingredientRepository.save(ingredient11);
        Ingredient savedIngredient12 = ingredientRepository.save(ingredient12);
        Ingredient savedIngredient13 = ingredientRepository.save(ingredient13);
        Ingredient savedIngredient14 = ingredientRepository.save(ingredient14);
        Ingredient savedIngredient15 = ingredientRepository.save(ingredient15);
        Ingredient savedIngredient16 = ingredientRepository.save(ingredient16);
        Ingredient savedIngredient17 = ingredientRepository.save(ingredient17);
        Ingredient savedIngredient18 = ingredientRepository.save(ingredient18);
        Ingredient savedIngredient19 = ingredientRepository.save(ingredient19);
        Ingredient savedIngredient20 = ingredientRepository.save(ingredient20);
        Ingredient savedIngredient21 = ingredientRepository.save(ingredient21);
        Ingredient savedIngredient22 = ingredientRepository.save(ingredient22);
        Ingredient savedIngredient23 = ingredientRepository.save(ingredient23);
        Ingredient savedIngredient24 = ingredientRepository.save(ingredient24);
        Ingredient savedIngredient25 = ingredientRepository.save(ingredient25);
        Ingredient savedIngredient26 = ingredientRepository.save(ingredient26);
        Ingredient savedIngredient27 = ingredientRepository.save(ingredient27);
        Ingredient savedIngredient28 = ingredientRepository.save(ingredient28);

        // Add Ingredients to Recipe
        savedRecipe2_2.getIngredients().add(savedIngredient9);
        savedRecipe2_2.getIngredients().add(savedIngredient10);
        savedRecipe2_2.getIngredients().add(savedIngredient11);
        savedRecipe2_2.getIngredients().add(savedIngredient12);
        savedRecipe2_2.getIngredients().add(savedIngredient13);
        savedRecipe2_2.getIngredients().add(savedIngredient14);
        savedRecipe2_2.getIngredients().add(savedIngredient15);
        savedRecipe2_2.getIngredients().add(savedIngredient16);
        savedRecipe2_2.getIngredients().add(savedIngredient17);
        savedRecipe2_2.getIngredients().add(savedIngredient18);
        savedRecipe2_2.getIngredients().add(savedIngredient19);
        savedRecipe2_2.getIngredients().add(savedIngredient20);
        savedRecipe2_2.getIngredients().add(savedIngredient21);
        savedRecipe2_2.getIngredients().add(savedIngredient22);
        savedRecipe2_2.getIngredients().add(savedIngredient23);
        savedRecipe2_2.getIngredients().add(savedIngredient24);
        savedRecipe2_2.getIngredients().add(savedIngredient25);
        savedRecipe2_2.getIngredients().add(savedIngredient26);
        savedRecipe2_2.getIngredients().add(savedIngredient27);
        savedRecipe2_2.getIngredients().add(savedIngredient27);

        // Save Recipe
        Recipe savedRecipe2_3 = recipeRepository.save(savedRecipe2_2);

        // Return Two Recipes
        return Arrays.asList(savedRecipe1_3, savedRecipe2_3);
    }
}
