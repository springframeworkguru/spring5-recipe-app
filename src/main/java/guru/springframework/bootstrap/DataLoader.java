package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository measureRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository measureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.measureRepository = measureRepository;
    }


    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading data on startup");
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);
        UnitOfMeasure teaspoon = getUnitOfMeasure("Teaspoon");
        UnitOfMeasure tablespoon = getUnitOfMeasure("Tablespoon");
        UnitOfMeasure cup = getUnitOfMeasure("Cup");
        UnitOfMeasure pinch = getUnitOfMeasure("Pinch");
        UnitOfMeasure ounce = getUnitOfMeasure("Ounce");
        UnitOfMeasure each = getUnitOfMeasure("Each");
        UnitOfMeasure dash = getUnitOfMeasure("Dash");
        UnitOfMeasure pint = getUnitOfMeasure("Pint");

        Category american = getCategory("American");
        Category mexican = getCategory("Mexican");

        Recipe perfectGuacamole = new Recipe();

        perfectGuacamole.addCategory(mexican);

        Notes notesForGuacamole = new Notes();
        notesForGuacamole.setRecipeNotes(getGuacamoleDescription());
        perfectGuacamole.setNotes(notesForGuacamole);

        perfectGuacamole.setDescription(getGuacamoleDescription());
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole");
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setServings(3);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setDescription("How to Make the Best Guacamole");
        perfectGuacamole.setDirections(getGuacamoleDirections());
        Set<Ingredient> guacIngrediends = perfectGuacamole.getIngredients();
        perfectGuacamole.addIngredient(new Ingredient("ripe avocado", "2", each));
        perfectGuacamole.addIngredient(new Ingredient("fresh lemon or lime juice", "1", tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("salt", "0.25", teaspoon));
        perfectGuacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", "3", tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", "2", each));
        perfectGuacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", "2", tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("freshly ground black pepper", "1", pinch));
        perfectGuacamole.addIngredient(new Ingredient("ripe tomato, chopped (optional)", "1", each));
        perfectGuacamole.addIngredient(new Ingredient("Red radish or jicama slices for garnish (optional)", "1", each));
        perfectGuacamole.addIngredient(new Ingredient("Tortilla chips, to serve", "3", each));

        log.debug("saveing guac: " + perfectGuacamole);

        recipes.add(perfectGuacamole);


        Recipe chicken = new Recipe();

        chicken.addCategory(american);
        chicken.addCategory(mexican);


        Notes noteForChicken = new Notes();
        noteForChicken.setRecipeNotes(getChickenDescription());
        chicken.setNotes(noteForChicken);

        chicken.setDescription("Spicy Grilled Chicken Tacos");
        chicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chicken.setDifficulty(Difficulty.MODERATE);
        chicken.setServings(5);
        chicken.setCookTime(15);
        chicken.setPrepTime(20);
        chicken.setDirections(getChickenDirections());


        chicken.addIngredient(new Ingredient("ancho chili powder", "2", tablespoon));
        chicken.addIngredient(new Ingredient("dried oregano", "1", teaspoon));
        chicken.addIngredient(new Ingredient("dried cumin", "1", teaspoon));
        chicken.addIngredient(new Ingredient("sugar", "1", teaspoon));
        chicken.addIngredient(new Ingredient("salt", "0.5", teaspoon));
        chicken.addIngredient(new Ingredient("clove garlic, finely chopped", "1", each));
        chicken.addIngredient(new Ingredient("finely grated orange zest", "1", tablespoon));
        chicken.addIngredient(new Ingredient("fresh-squeezed orange juice", "3", tablespoon));
        chicken.addIngredient(new Ingredient("olive oil", "2", tablespoon));
        chicken.addIngredient(new Ingredient("skinless, boneless chicken thighs", "5", each));

        log.debug("saveing chicken: " + chicken);

        recipes.add(chicken);

        return recipes;
    }


    private Category getCategory(String desc) {
        return categoryRepository
                .findByDescription(desc)
                .orElseThrow(() -> new RuntimeException("Expected Category Not Found!"));
    }

    private UnitOfMeasure getUnitOfMeasure(String desc) {
        return measureRepository
                .findByDescription(desc)
                .orElseThrow(() -> new RuntimeException("Expected UOM Not Found!"));
    }

    private String getChickenDirections() {
        return "1: Prepare a gas or charcoal grill for medium-high, direct heat\n\n" +
                "2: Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest." +
                " Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n\n" +
                "3: Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F." +
                " Transfer to a plate and rest for 5 minutes.\n\n" +
                "4: Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat." +
                "As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n\n" +
                "5: Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices," +
                "sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.";
    }

    private String getGuacamoleDirections() {
        return "1: Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "2: Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "3: Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                "4: Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.";
    }


    private String getGuacamoleDescription() {
        return "The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime," +
                " onions, chilis, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on" +
                " top of tacos for an easy dinner upgrade.";
    }


    private String getChickenDescription() {
        return "Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes." +
                " Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.";
    }

}
