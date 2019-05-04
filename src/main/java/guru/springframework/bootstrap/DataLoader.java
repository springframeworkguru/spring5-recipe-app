package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    private void loadData() {

        /*/
        // Query to See Results
        SELECT r.DESCRIPTION, r.difficulty, r.prep_time, r.servings, i.amount, uom.description, i.description FROM RECIPE r
        INNER JOIN INGREDIENT i
            ON r.ID = i.RECIPE_ID
        LEFT JOIN UNIT_OF_MEASURE uom
           ON i.unit_of_measure_id = uom.id
        //*/

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

        // Category - Mexican
        Optional<Category> categoryMexican = categoryRepository.findCategoryByDescription("Mexican");
        Set<Category> categories = new HashSet<>();
        Set<Recipe> recipes = new HashSet<>();
        if (categoryMexican.isPresent()) {

            categoryMexican.ifPresent(o -> categories.add(o));
            categoryMexican.ifPresent(o -> savedRecipe1.setCategories(categories));

            categoryMexican.ifPresent(o -> recipes.add(savedRecipe1));
            categoryMexican.ifPresent(o -> o.setRecipes(recipes));
        }

        // Ingredient 1
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("ripe avocados");
        BigDecimal bigDecimalAmount1 = new BigDecimal("2");
        ingredient1.setAmount(bigDecimalAmount1);
        ingredient1.setRecipe(savedRecipe1);
        ingredientRepository.save(ingredient1);

        // Ingredient 2
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setDescription("Kosher salt");
        BigDecimal bigDecimalAmount2 = new BigDecimal("0.5");
        ingredient2.setAmount(bigDecimalAmount2);
        ingredient2.setRecipe(savedRecipe1);

        Optional<UnitOfMeasure> optionalUnitOfMeasure2 = unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");
        if (optionalUnitOfMeasure2.isPresent()) {
            optionalUnitOfMeasure2.ifPresent(o -> ingredient2.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient2);


        // Ingredient 3
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setDescription("fresh lime juice or lemon juice");
        BigDecimal bigDecimalAmount3 = new BigDecimal("1");
        ingredient3.setAmount(bigDecimalAmount3);
        ingredient3.setRecipe(savedRecipe1);

        Optional<UnitOfMeasure> optionalUnitOfMeasure3 = unitOfMeasureRepository.findUnitOfMeasureByDescription("Tablespoon");
        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient3.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient3);


        // Ingredient 4
        Ingredient ingredient4 = new Ingredient();
        ingredient4.setDescription("minced red onion or thinly sliced green onion");
        ingredient4.setAmount(bigDecimalAmount3);
        ingredient4.setRecipe(savedRecipe1);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient4.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient4);


        // Ingredient 5
        Ingredient ingredient5 = new Ingredient();
        ingredient5.setDescription("serrano chiles, stems and seeds removed, minced");
        BigDecimal bigDecimalAmount5 = new BigDecimal("1.5");
        ingredient5.setAmount(bigDecimalAmount5);
        ingredient5.setRecipe(savedRecipe1);
        ingredientRepository.save(ingredient5);


        // Ingredient 6
        Ingredient ingredient6 = new Ingredient();
        ingredient6.setDescription("cilantro (leaves and tender stems), finally chopped");
        ingredient6.setAmount(bigDecimalAmount1);
        ingredient6.setRecipe(savedRecipe1);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient6.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient6);


        // Ingredient 7
        Ingredient ingredient7 = new Ingredient();
        ingredient7.setDescription("freshly grated black pepper");
        ingredient7.setAmount(bigDecimalAmount3);
        ingredient7.setRecipe(savedRecipe1);

        Optional<UnitOfMeasure> optionalUnitOfMeasure7 = unitOfMeasureRepository.findUnitOfMeasureByDescription("Dash");
        if (optionalUnitOfMeasure7.isPresent()) {
            optionalUnitOfMeasure7.ifPresent(o -> ingredient7.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient7);


        // Ingredient 8
        Ingredient ingredient8 = new Ingredient();
        ingredient8.setDescription("ripe tomato, seeds and pulp remvoed, chopped");
        ingredient8.setAmount(bigDecimalAmount5);
        ingredient8.setRecipe(savedRecipe1);
        ingredientRepository.save(ingredient8);



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

        if (categoryMexican.isPresent()) {

            categoryMexican.ifPresent(o -> savedRecipe2.setCategories(categories));

            categoryMexican.ifPresent(o -> recipes.add(savedRecipe2));
            categoryMexican.ifPresent(o -> o.setRecipes(recipes));
        }


        Ingredient ingredient9 = new Ingredient();
        ingredient9.setDescription("ancho chile powder");
        ingredient9.setAmount(bigDecimalAmount1);
        ingredient9.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure2.isPresent()) {
            optionalUnitOfMeasure2.ifPresent(o -> ingredient9.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient9);




        Ingredient ingredient10 = new Ingredient();
        ingredient10.setDescription("dried oregano");
        ingredient10.setAmount(bigDecimalAmount3);
        ingredient10.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient10.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient10);




        Ingredient ingredient11 = new Ingredient();
        ingredient11.setDescription("dried cumin");
        ingredient11.setAmount(bigDecimalAmount3);
        ingredient11.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient11.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient11);




        Ingredient ingredient12 = new Ingredient();
        ingredient12.setDescription("sugar");
        ingredient12.setAmount(bigDecimalAmount3);
        ingredient12.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient12.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient12);




        Ingredient ingredient13 = new Ingredient();
        ingredient13.setDescription("salt");
        ingredient13.setAmount(bigDecimalAmount2);
        ingredient13.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient13.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient13);


        Ingredient ingredient14 = new Ingredient();
        ingredient14.setDescription("garlic, finely chopped");
        ingredient14.setAmount(bigDecimalAmount3);
        ingredient14.setRecipe(savedRecipe2);
        Optional<UnitOfMeasure> optionalUnitOfMeasure14 = unitOfMeasureRepository.findUnitOfMeasureByDescription("Clove");
        if (optionalUnitOfMeasure14.isPresent()) {
            optionalUnitOfMeasure14.ifPresent(o -> ingredient14.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient14);



        Ingredient ingredient15 = new Ingredient();
        ingredient15.setDescription("finely grated orange zest");
        ingredient15.setAmount(bigDecimalAmount3);
        ingredient15.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient15.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient15);



        Ingredient ingredient16 = new Ingredient();
        ingredient16.setDescription("fresh squeezed orange juice");
        BigDecimal bigDecimalAmount16 = new BigDecimal("3");
        ingredient16.setAmount(bigDecimalAmount16);
        ingredient16.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient16.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient16);



        Ingredient ingredient17 = new Ingredient();
        ingredient17.setDescription("olive oil");
        ingredient17.setAmount(bigDecimalAmount1);
        ingredient17.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient17.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient17);


        Ingredient ingredient18 = new Ingredient();
        ingredient18.setDescription("skinless, boneless chicken thighs");
        BigDecimal bigDecimalAmount18 = new BigDecimal("5");
        ingredient18.setAmount(bigDecimalAmount18);
        ingredient18.setRecipe(savedRecipe2);
        ingredientRepository.save(ingredient18);


        Ingredient ingredient19 = new Ingredient();
        ingredient19.setDescription("small corn tortillas");
        BigDecimal bigDecimalAmount19 = new BigDecimal("8");
        ingredient19.setAmount(bigDecimalAmount19);
        ingredient19.setRecipe(savedRecipe2);
        ingredientRepository.save(ingredient19);


        Ingredient ingredient20 = new Ingredient();
        ingredient20.setDescription("packed baby arugula");
        ingredient20.setAmount(bigDecimalAmount16);
        ingredient20.setRecipe(savedRecipe2);

        Optional<UnitOfMeasure> optionalUnitOfMeasure20 = unitOfMeasureRepository.findUnitOfMeasureByDescription("Cup");
        if (optionalUnitOfMeasure20.isPresent()) {
            optionalUnitOfMeasure20.ifPresent(o -> ingredient20.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient20);


        Ingredient ingredient21 = new Ingredient();
        ingredient21.setDescription("medium, ripe avocados");
        ingredient21.setAmount(bigDecimalAmount1);
        ingredient21.setRecipe(savedRecipe2);
        ingredientRepository.save(ingredient21);


        Ingredient ingredient22 = new Ingredient();
        ingredient22.setDescription("radishes, thinly sliced");
        BigDecimal bigDecimalAmount22 = new BigDecimal("4");
        ingredient22.setAmount(bigDecimalAmount22);
        ingredient22.setRecipe(savedRecipe2);
        ingredientRepository.save(ingredient22);


        Ingredient ingredient23 = new Ingredient();
        ingredient23.setDescription("cherry tomatoes");
        ingredient23.setAmount(bigDecimalAmount2);
        ingredient23.setRecipe(savedRecipe2);

        Optional<UnitOfMeasure> optionalUnitOfMeasure23 = unitOfMeasureRepository.findUnitOfMeasureByDescription("Pint");
        if (optionalUnitOfMeasure23.isPresent()) {
            optionalUnitOfMeasure23.ifPresent(o -> ingredient23.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient23);


        Ingredient ingredient24 = new Ingredient();
        ingredient24.setDescription("red onion, thinly sliced");
        BigDecimal bigDecimalAmount25 = new BigDecimal("0.25");
        ingredient24.setAmount(bigDecimalAmount25);
        ingredient24.setRecipe(savedRecipe2);
        ingredientRepository.save(ingredient24);


        Ingredient ingredient25 = new Ingredient();
        ingredient25.setDescription("roughly chopped cilantro");
        ingredient25.setRecipe(savedRecipe2);
        ingredientRepository.save(ingredient25);


        Ingredient ingredient26 = new Ingredient();
        ingredient26.setDescription("sour cream");
        ingredient26.setAmount(bigDecimalAmount2);
        ingredient26.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure20.isPresent()) {
            optionalUnitOfMeasure20.ifPresent(o -> ingredient26.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient26);


        Ingredient ingredient27 = new Ingredient();
        ingredient27.setDescription("milk");
        ingredient27.setAmount(bigDecimalAmount25);
        ingredient27.setRecipe(savedRecipe2);

        if (optionalUnitOfMeasure20.isPresent()) {
            optionalUnitOfMeasure20.ifPresent(o -> ingredient27.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient27);


        Ingredient ingredient28 = new Ingredient();
        ingredient28.setDescription("lime, cut into wedges");
        ingredient28.setAmount(bigDecimalAmount3);
        ingredient28.setRecipe(savedRecipe2);
        ingredientRepository.save(ingredient28);

    }
}
