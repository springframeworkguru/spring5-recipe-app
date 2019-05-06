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
        // SQL to check data was inserted
        select c.description, r.description, r.difficulty, r.cook_time, r.prep_time, r.servings, i.amount, uom.description, i.description
        from RECIPE r
        INNER JOIN recipe_category rc
        on r.id = rc.recipe_id
        INNER JOIN category c
        ON rc.category_id = c.id
        INNER JOIN ingredient i
        on r.id = i.recipe_id
        INNER JOIN unit_of_measure uom
        on i.unit_of_measure_id = uom.id
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
        // Teaspoon
        Optional<UnitOfMeasure> optionalTeaspoonUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");
        if (!optionalTeaspoonUom.isPresent()) {
            throw new RuntimeException("No Teaspoon UOM.");
        }
        UnitOfMeasure teaspoonUom = optionalTeaspoonUom.get();

        // Tablespoon
        Optional<UnitOfMeasure> optionalTablespoonUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Tablespoon");
        if (!optionalTablespoonUom.isPresent()) {
            throw new RuntimeException("No Tablespoon UOM.");
        }
        UnitOfMeasure tableSpoonUom = optionalTablespoonUom.get();

        // Dash
        Optional<UnitOfMeasure> optionalDashUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Dash");
        if (!optionalDashUom.isPresent()) {
            throw new RuntimeException("No Dash UOM.");
        }
        UnitOfMeasure dashUom = optionalDashUom.get();

        // Cup
        Optional<UnitOfMeasure> optionalCupUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Cup");
        if (!optionalCupUom.isPresent()) {
            throw new RuntimeException("No Cup UOM.");
        }
        UnitOfMeasure cupUom = optionalCupUom.get();

        // Pint
        Optional<UnitOfMeasure> optionalPintUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Pint");
        if (!optionalPintUom.isPresent()) {
            throw new RuntimeException("No Pint UOM.");
        }
        UnitOfMeasure pintUom = optionalPintUom.get();

        // Clove
        Optional<UnitOfMeasure> optionalCloveUom = unitOfMeasureRepository.findUnitOfMeasureByDescription("Clove");
        if (!optionalCloveUom.isPresent()) {
            throw new RuntimeException("No Clove UOM.");
        }
        UnitOfMeasure cloveUom = optionalCloveUom.get();

        /**
         * Recipes
         */
        // Recipe 1
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Perfect Guacamole");
        recipe1.setPrepTime(10);
        recipe1.setCookTime(10);
        recipe1.setServings(3);
        recipe1.setDifficulty(Difficulty.MODERATE);
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
        recipe1.getCategories().add(categoryAmerican);
        recipe1.getCategories().add(categoryMexican);
        Recipe savedRecipe1 = recipeRepository.save(recipe1);

        /**
         * Ingredients
         */
        Ingredient ingredient1 = new Ingredient("ripe avocados", savedRecipe1, new BigDecimal(2));
        Ingredient ingredient2 = new Ingredient("Kosher salt", savedRecipe1, new BigDecimal("0.5"), teaspoonUom);
        Ingredient ingredient3 = new Ingredient("fresh lime juice or lemon juice", savedRecipe1, new BigDecimal(1), tableSpoonUom);
        Ingredient ingredient4 = new Ingredient("minced red onion or thinly sliced green onion", savedRecipe1, new BigDecimal(2), tableSpoonUom);
        Ingredient ingredient5 = new Ingredient("serrano chiles, stems and seeds removed, minced", savedRecipe1, new BigDecimal("1.5"));
        Ingredient ingredient6 = new Ingredient("cilantro (leaves and tender stems), finally chopped", savedRecipe1, new BigDecimal("1.5"));
        Ingredient ingredient7 = new Ingredient("freshly grated black pepper", savedRecipe1, new BigDecimal(1), dashUom);
        Ingredient ingredient8 = new Ingredient("ripe tomato, seeds and pulp removed, chopped", savedRecipe1, new BigDecimal("0.5"));

        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);
        ingredientRepository.save(ingredient4);
        ingredientRepository.save(ingredient5);
        ingredientRepository.save(ingredient6);
        ingredientRepository.save(ingredient7);
        ingredientRepository.save(ingredient8);

        savedRecipe1.getIngredients().add(ingredient1);
        savedRecipe1.getIngredients().add(ingredient2);
        savedRecipe1.getIngredients().add(ingredient3);
        savedRecipe1.getIngredients().add(ingredient4);
        savedRecipe1.getIngredients().add(ingredient5);
        savedRecipe1.getIngredients().add(ingredient6);
        savedRecipe1.getIngredients().add(ingredient7);
        savedRecipe1.getIngredients().add(ingredient8);

        recipeRepository.save(savedRecipe1);
      
        // Recipe 2
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Spicy Grilled Chicken Tacos");
        recipe2.setDifficulty(Difficulty.MODERATE);
        recipe2.setPrepTime(20);
        recipe2.setCookTime(15);
        recipe2.setServings(5);
        recipe2.setSource("SimplyRecipes.com");
        recipe2.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        String directions2 = "<ul>\n" +
                "    <li>Prepare a gas or charcoal grill for medium-high, direct heat.</li>\n" +
                "    <li>Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.</li>\n" +
                "    <li>Set aside to marinate while the grill heats and you prepare the rest of the toppings.</li>\n" +
                "    <li>Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.</li>\n" +
                "    <li>4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.</li>\n" +
                "    <li>Wrap warmed tortillas in a tea towel to keep them warm until serving.</li>\n" +
                "    <li>5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.</li>\n" +
                "</ul>";
        recipe2.setDirections(directions2);
        recipe2.getCategories().add(categoryAmerican);
        recipe2.getCategories().add(categoryMexican);
        Recipe savedRecipe2 = recipeRepository.save(recipe2);

        /**
         * Ingredients
         */
        Ingredient ingredient9 = new Ingredient("ancho chili powder", savedRecipe2, new BigDecimal(2), tableSpoonUom);
        Ingredient ingredient10 = new Ingredient("dried oregano", savedRecipe2, new BigDecimal(1), teaspoonUom);
        Ingredient ingredient11 = new Ingredient("dried cumin", savedRecipe2, new BigDecimal(1), teaspoonUom);
        Ingredient ingredient12 = new Ingredient("sugar", savedRecipe2, new BigDecimal(1), teaspoonUom);
        Ingredient ingredient13 = new Ingredient("salt", savedRecipe2, new BigDecimal("1.5"), teaspoonUom);
        Ingredient ingredient14 = new Ingredient("garlic, finely chopped", savedRecipe2, new BigDecimal(1), cloveUom);
        Ingredient ingredient15 = new Ingredient("finely grated orange zest", savedRecipe2, new BigDecimal(1), tableSpoonUom);
        Ingredient ingredient16 = new Ingredient("fresh-squeezed orange juice", savedRecipe2, new BigDecimal(3), tableSpoonUom);
        Ingredient ingredient17 = new Ingredient("olive oil", savedRecipe2, new BigDecimal(2), tableSpoonUom);
        Ingredient ingredient18 = new Ingredient("skinless, boneless chicken thighs", savedRecipe2, new BigDecimal(5));
        Ingredient ingredient19 = new Ingredient("small corn tortillas", savedRecipe2, new BigDecimal(8));
        Ingredient ingredient20 = new Ingredient("packed baby arugula", savedRecipe2, new BigDecimal(8));
        Ingredient ingredient21 = new Ingredient("medium ripe avocados, sliced", savedRecipe2, new BigDecimal(2));
        Ingredient ingredient22 = new Ingredient("radishes, thinly sliced", savedRecipe2, new BigDecimal(4));
        Ingredient ingredient23 = new Ingredient("radishes, thinly slicedpint cherry tomatoes, halved", savedRecipe2, new BigDecimal("1.5"));
        Ingredient ingredient24 = new Ingredient("red onion, thinly sliced", savedRecipe2, new BigDecimal("0.25"));
        Ingredient ingredient25 = new Ingredient("chopped cilantro", savedRecipe2, new BigDecimal("0.25"));
        Ingredient ingredient26 = new Ingredient("sour cream thinned", savedRecipe2, new BigDecimal("0.5"), cupUom);
        Ingredient ingredient27 = new Ingredient("cup milk", savedRecipe2, new BigDecimal("0.25"), cupUom);
        Ingredient ingredient28 = new Ingredient("lime, cut into wedges", savedRecipe2, new BigDecimal(1));

        ingredientRepository.save(ingredient9);
        ingredientRepository.save(ingredient10);
        ingredientRepository.save(ingredient11);
        ingredientRepository.save(ingredient12);
        ingredientRepository.save(ingredient13);
        ingredientRepository.save(ingredient14);
        ingredientRepository.save(ingredient15);
        ingredientRepository.save(ingredient16);
        ingredientRepository.save(ingredient17);
        ingredientRepository.save(ingredient18);
        ingredientRepository.save(ingredient19);
        ingredientRepository.save(ingredient20);
        ingredientRepository.save(ingredient21);
        ingredientRepository.save(ingredient22);
        ingredientRepository.save(ingredient23);
        ingredientRepository.save(ingredient24);
        ingredientRepository.save(ingredient25);
        ingredientRepository.save(ingredient26);
        ingredientRepository.save(ingredient27);
        ingredientRepository.save(ingredient28);

        savedRecipe2.getIngredients().add(ingredient9);
        savedRecipe2.getIngredients().add(ingredient10);
        savedRecipe2.getIngredients().add(ingredient11);
        savedRecipe2.getIngredients().add(ingredient12);
        savedRecipe2.getIngredients().add(ingredient13);
        savedRecipe2.getIngredients().add(ingredient14);
        savedRecipe2.getIngredients().add(ingredient15);
        savedRecipe2.getIngredients().add(ingredient16);
        savedRecipe2.getIngredients().add(ingredient17);
        savedRecipe2.getIngredients().add(ingredient18);
        savedRecipe2.getIngredients().add(ingredient19);
        savedRecipe2.getIngredients().add(ingredient20);
        savedRecipe2.getIngredients().add(ingredient21);
        savedRecipe2.getIngredients().add(ingredient22);
        savedRecipe2.getIngredients().add(ingredient23);
        savedRecipe2.getIngredients().add(ingredient24);
        savedRecipe2.getIngredients().add(ingredient25);
        savedRecipe2.getIngredients().add(ingredient26);
        savedRecipe2.getIngredients().add(ingredient27);
        savedRecipe2.getIngredients().add(ingredient28);

        recipeRepository.save(savedRecipe2);
    }
}
