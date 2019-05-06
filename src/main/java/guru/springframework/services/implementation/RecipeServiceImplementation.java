package guru.springframework.services.implementation;

import guru.springframework.domain.*;
import guru.springframework.repositories.*;
import guru.springframework.services.RecipeService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Primary
public class RecipeServiceImplementation implements RecipeService {

    private IngredientRepository ingredientRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private NoteRepository noteRepository;

    public RecipeServiceImplementation(IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository, CategoryRepository categoryRepository, NoteRepository noteRepository) {
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public Set<Recipe> fetchRecipeList() {

        /*/
        // SQL to check data was inserted
        select c.description, r.description, r.difficulty, r.cook_time, r.prep_time, r.servings,
        -- n.recipe_notes,
        i.amount, uom.description, i.description
        from RECIPE r
        INNER JOIN notes n
            on r.id = n.recipe_id
        INNER JOIN recipe_category rc
        on r.id = rc.recipe_id
        INNER JOIN category c
        ON rc.category_id = c.id
        INNER JOIN ingredient i
        on r.id = i.recipe_id
        INNER JOIN unit_of_measure uom
        on i.unit_of_measure_id = uom.id
        //*/

        Set<Recipe> recipes = new HashSet<>();

        /**
         * Categories
         */
        // Category - Mexican
        Optional<Category> optionalCategoryMexican = categoryRepository.findCategoryByDescription("Mexican");
        if (!optionalCategoryMexican.isPresent()) {
            throw new RuntimeException("No Mexican Category.");
        }
        Category categoryMexican = optionalCategoryMexican.get();

        // Category - American
        Optional<Category> optionalCategoryAmerican = categoryRepository.findCategoryByDescription("American");
        if (!optionalCategoryAmerican.isPresent()) {
            throw new RuntimeException("No American Category.");
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

        String getNote = "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "Variations\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!";
        Notes notes = new Notes(getNote);
        recipe1.setNotes(notes);

        /**
         * Ingredients
         */
        Ingredient ingredient1 = new Ingredient("ripe avocados", new BigDecimal(2));
        Ingredient ingredient2 = new Ingredient("Kosher salt", new BigDecimal("0.5"), teaspoonUom);
        Ingredient ingredient3 = new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tableSpoonUom);
        Ingredient ingredient4 = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom);
        Ingredient ingredient5 = new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal("1.5"));
        Ingredient ingredient6 = new Ingredient("cilantro (leaves and tender stems), finally chopped", new BigDecimal("1.5"));
        Ingredient ingredient7 = new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUom);
        Ingredient ingredient8 = new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal("0.5"));

        recipe1.addIngredient(ingredient1);
        recipe1.addIngredient(ingredient2);
        recipe1.addIngredient(ingredient3);
        recipe1.addIngredient(ingredient4);
        recipe1.addIngredient(ingredient5);
        recipe1.addIngredient(ingredient6);
        recipe1.addIngredient(ingredient7);
        recipe1.addIngredient(ingredient8);

        recipes.add(recipe1);


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


        String getNote2 = "We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "Spicy Grilled Chicken TacosThe ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!";
        Notes notes2 = new Notes(getNote2);
        recipe2.setNotes(notes2);


        /**
         * Ingredients
         */
        Ingredient ingredient9 = new Ingredient("ancho chili powder", new BigDecimal(2), tableSpoonUom);
        Ingredient ingredient10 = new Ingredient("dried oregano", new BigDecimal(1), teaspoonUom);
        Ingredient ingredient11 = new Ingredient("dried cumin", new BigDecimal(1), teaspoonUom);
        Ingredient ingredient12 = new Ingredient("sugar", new BigDecimal(1), teaspoonUom);
        Ingredient ingredient13 = new Ingredient("salt", new BigDecimal("1.5"), teaspoonUom);
        Ingredient ingredient14 = new Ingredient("garlic, finely chopped", new BigDecimal(1), cloveUom);
        Ingredient ingredient15 = new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoonUom);
        Ingredient ingredient16 = new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom);
        Ingredient ingredient17 = new Ingredient("olive oil", new BigDecimal(2), tableSpoonUom);
        Ingredient ingredient18 = new Ingredient("skinless, boneless chicken thighs", new BigDecimal(5));
        Ingredient ingredient19 = new Ingredient("small corn tortillas", new BigDecimal(8));
        Ingredient ingredient20 = new Ingredient("packed baby arugula", new BigDecimal(8));
        Ingredient ingredient21 = new Ingredient("medium ripe avocados, sliced", new BigDecimal(2));
        Ingredient ingredient22 = new Ingredient("radishes, thinly sliced", new BigDecimal(4));
        Ingredient ingredient23 = new Ingredient("radishes, thinly slicedpint cherry tomatoes, halved", new BigDecimal("1.5"));
        Ingredient ingredient24 = new Ingredient("red onion, thinly sliced", new BigDecimal("0.25"));
        Ingredient ingredient25 = new Ingredient("chopped cilantro", new BigDecimal("0.25"));
        Ingredient ingredient26 = new Ingredient("sour cream thinned", new BigDecimal("0.5"), cupUom);
        Ingredient ingredient27 = new Ingredient("cup milk", new BigDecimal("0.25"), cupUom);
        Ingredient ingredient28 = new Ingredient("lime, cut into wedges", new BigDecimal(1));

        recipe2.addIngredient(ingredient9);
        recipe2.addIngredient(ingredient10);
        recipe2.addIngredient(ingredient11);
        recipe2.addIngredient(ingredient12);
        recipe2.addIngredient(ingredient13);
        recipe2.addIngredient(ingredient14);
        recipe2.addIngredient(ingredient15);
        recipe2.addIngredient(ingredient16);
        recipe2.addIngredient(ingredient17);
        recipe2.addIngredient(ingredient18);
        recipe2.addIngredient(ingredient19);
        recipe2.addIngredient(ingredient20);
        recipe2.addIngredient(ingredient21);
        recipe2.addIngredient(ingredient22);
        recipe2.addIngredient(ingredient23);
        recipe2.addIngredient(ingredient24);
        recipe2.addIngredient(ingredient25);
        recipe2.addIngredient(ingredient26);
        recipe2.addIngredient(ingredient27);
        recipe2.addIngredient(ingredient28);

        recipes.add(recipe2);

        return recipes;
    }
}
