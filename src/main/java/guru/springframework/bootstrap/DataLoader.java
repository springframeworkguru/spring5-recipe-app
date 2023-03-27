package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    final private UnitOfMeasureRepository unitOfMeasureRepository;
    final private CategoryRepository categoryRepository;
    final private RecipeRepository recipeRepository;

    public DataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(loadRecipes());
    }


    private List<Recipe> loadRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);
        // Init unitOfMeuares
        Optional<UnitOfMeasure> Teaspoon = unitOfMeasureRepository.findAllByDescription("Teaspoon");
        if (!Teaspoon.isPresent()) {
            throw new RuntimeException("Teaspoon uOm is not present");
        }
        Optional<UnitOfMeasure> Tablespoon = unitOfMeasureRepository.findAllByDescription("Tablespoon");
        if (!Tablespoon.isPresent()) {
            throw new RuntimeException("Tablespoon uOm is not present");
        }
        Optional<UnitOfMeasure> Cup = unitOfMeasureRepository.findAllByDescription("Cup");
        if (!Cup.isPresent()) {
            throw new RuntimeException("Cup uOm is not present");
        }
        Optional<UnitOfMeasure> Pinch = unitOfMeasureRepository.findAllByDescription("Pinch");
        if (!Pinch.isPresent()) {
            throw new RuntimeException("Pinch uOm is not present");
        }
        Optional<UnitOfMeasure> Pint = unitOfMeasureRepository.findAllByDescription("Pint");
        if (!Pint.isPresent()) {
            throw new RuntimeException("Pint uOm is not present");
        }
        Optional<UnitOfMeasure> Each = unitOfMeasureRepository.findAllByDescription("Each");
        if (!Each.isPresent()) {
            throw new RuntimeException("Each uOm is not present");
        }
        //Get all uom
        UnitOfMeasure teaspoonUom = Teaspoon.get();
        UnitOfMeasure tablespoonUom = Tablespoon.get();
        UnitOfMeasure cupUom = Cup.get();
        UnitOfMeasure pinchUom = Pinch.get();
        UnitOfMeasure pintUom = Pint.get();
        UnitOfMeasure eachUom = Each.get();

        //Init Categories
        Optional<Category> Italian = categoryRepository.findAllByDescription("Italian");
        if (!Italian.isPresent()) {
            throw new RuntimeException("Italian category is not present");
        }
        Optional<Category> Russian = categoryRepository.findAllByDescription("Russian");
        if (!Russian.isPresent()) {
            throw new RuntimeException("Russian category is not present");
        }
        Optional<Category> Georgian = categoryRepository.findAllByDescription("Georgian");
        if (!Georgian.isPresent()) {
            throw new RuntimeException("Georgian category is not present");
        }
        Optional<Category> Siberian = categoryRepository.findAllByDescription("Siberian");
        if (!Siberian.isPresent()) {
            throw new RuntimeException("Siberian category is not present");
        }
        Optional<Category> Mexico = categoryRepository.findAllByDescription("Mexico");
        if (!Mexico.isPresent()) {
            throw new RuntimeException("Mexico category is not present");
        }


        //Get all categories
        Category italianCategory = Italian.get();
        Category russianCategory = Russian.get();
        Category georgianCategory = Georgian.get();
        Category siberianCategory = Siberian.get();
        Category mexicoCategory = Mexico.get();

        //Create Recipes
        //Gluacamole ingredients

        Recipe bestGluacamole = new Recipe();
        Notes gluacamoleNote = new Notes();
        gluacamoleNote.setRecipe(bestGluacamole);
        gluacamoleNote.setRecipeNotes(" Guacamole Variations\n" +
                "\n" +
                "Once you have basic guacamole down, feel free to experiment with variations by adding strawberries, peaches, pineapple, mangoes, or even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with your homemade guacamole!\n" +
                "\n" +
                "    Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of other ingredients stop you from making guacamole.\n" +
                "    Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "    Don't have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great.\n");
        bestGluacamole.setNotes(gluacamoleNote);
        bestGluacamole.setDescription("Best Gluacamole");
        bestGluacamole.setDirections(" Cut the avocados:\n" +
                "\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl. " +
                " Mash the avocado flesh:\n" +
                "\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                " Add the remaining ingredients to taste:\n" +
                "\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste. " +
                " Serve immediately:\n" +
                "\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days. " +
                "");
        bestGluacamole.setPrepTime(10);
        bestGluacamole.setCookTime(15);
        bestGluacamole.getCategories().add(mexicoCategory);
        bestGluacamole.setDifficulty(Difficulty.HARD);
        bestGluacamole.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, bestGluacamole));
        bestGluacamole.getIngredients().add(new Ingredient("teaspoon kosher salt", new BigDecimal(".25"), teaspoonUom, bestGluacamole));
        bestGluacamole.getIngredients().add(new Ingredient("fresh lime or lemon juice", new BigDecimal(1), tablespoonUom, bestGluacamole));
        bestGluacamole.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(3), eachUom, bestGluacamole));
        bestGluacamole.getIngredients().add(new Ingredient("serrano (or jalapeño) chilis", new BigDecimal(2), eachUom, bestGluacamole));
        bestGluacamole.getIngredients().add(new Ingredient("ground black pepper ", new BigDecimal(1), tablespoonUom, bestGluacamole));
        bestGluacamole.getIngredients().add(new Ingredient("ripe tomato ", new BigDecimal(2), eachUom, bestGluacamole));
        bestGluacamole.getIngredients().add(new Ingredient("Red radish or jicama slices for garnish", new BigDecimal(1), eachUom, bestGluacamole));

        recipes.add(bestGluacamole);

        //Spicy Grilled Chicken Tacos ingredients


        Recipe spicyGrilledChickenTacos = new Recipe();

        Notes spiceGCTNote = new Notes();
        spiceGCTNote.setRecipe(spicyGrilledChickenTacos);
        spiceGCTNote.setRecipeNotes(" We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today's tacos are more purposeful a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes! ");
        spicyGrilledChickenTacos.setNotes(spiceGCTNote);
        spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        spicyGrilledChickenTacos.setDirections(" Prepare the grill:\n" +
                "\n" +
                "Prepare either a gas or charcoal grill for medium-high, direct heat. " +
                " Make the marinade and coat the chicken:\n" +
                "\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings. " +
                " Grill the chicken:\n" +
                "\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes. " +
                " Thin the sour cream with milk:\n" +
                "\n" +
                "Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle. " +
                "\n" +
                "Assemble the tacos:\n" +
                "\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "Warm the tortillas:\n" +
                "\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving. ");
        spicyGrilledChickenTacos.setPrepTime(7);
        spicyGrilledChickenTacos.setCookTime(25);
        spicyGrilledChickenTacos.getCategories().add(italianCategory);
        spicyGrilledChickenTacos.getCategories().add(russianCategory);
        spicyGrilledChickenTacos.getCategories().add(siberianCategory);
        spicyGrilledChickenTacos.setDifficulty(Difficulty.EASY);
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient(" small corn tortillas", new BigDecimal(8), eachUom, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("packed baby arugula ", new BigDecimal(3), cupUom, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("medium ripe avocados", new BigDecimal(1), eachUom, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient(" cherry tomatoes", new BigDecimal("0.5"), pintUom, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("red onion", new BigDecimal("0.5"), eachUom, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(1), eachUom, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("sour cream", new BigDecimal("0.5"), cupUom, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("cup milk", new BigDecimal("0.25"), cupUom, spicyGrilledChickenTacos));
        spicyGrilledChickenTacos.getIngredients().add(new Ingredient("lime", new BigDecimal(1), eachUom, spicyGrilledChickenTacos));
        recipes.add(spicyGrilledChickenTacos);

        return recipes;
    }
}
