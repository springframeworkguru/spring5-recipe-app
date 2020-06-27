package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.image.ImageConverter;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final ImageConverter imageConverter;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, ImageConverter imageConverter) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.imageConverter = imageConverter;
    }

    @Override
    public void run(String... args) {

        loadData();

    }

    private void loadData() {

        final String couldNotFindCategory = "Could not find {0} category";

        final String couldNotFindUom = "Could not find {0} UOM";

        final Category mexican = this.categoryRepository.findCategoryByDescription("Mexican").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindCategory, "Mexican")));
        final Category fastFood = this.categoryRepository.findCategoryByDescription("Fast food").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindCategory, "Fast food")));

        final UnitOfMeasure unit = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Unit").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindUom, "Unit")));
        final UnitOfMeasure teaspoon = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindUom, "Teaspoon")));
        final UnitOfMeasure tablespoon = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Tablespoon").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindUom, "Tablespoon")));
        final UnitOfMeasure cup = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Cup").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindUom, "Cup")));
        final UnitOfMeasure dash = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Dash").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindUom, "Dash")));
        final UnitOfMeasure bag = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Bag").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindUom, "Bag")));
        final UnitOfMeasure clove = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Clove").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindUom, "Clove")));
        final UnitOfMeasure pound = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Pound").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindUom, "Pound")));
        final UnitOfMeasure pint = this.unitOfMeasureRepository.findUnitOfMeasureByDescription("Pint").orElseThrow(() -> new RuntimeException(MessageFormat.format(couldNotFindUom, "Pint")));

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setDirections(String.join("\n", "1 Cut the avocadoGuacamole, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocadoGuacamole with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.", "2 Mash with a fork: Using a fork, roughly mash the avocadoGuacamole. (Don't overdo it! The guacamole should be a little chunky.)", "3 Add saltGuacamole, lime juice, and the rest: Sprinkle with saltGuacamole and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocadoGuacamole and will help delay the avocados from turning brown.\n", "Add the chopped onionGuacamole, cilantroGuacamole, black pepper, and chilesGuacamole. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n", "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n", "Chilling tomatoes hurts their flavor, so if you want to add chopped tomatoGuacamole to your guacamole, add it just before serving.", "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."));
        guacamole.setServings(4);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.getCategories().add(mexican);
        guacamole.getCategories().add(fastFood);
        guacamole.setImage(this.imageConverter.convertFromUrl("https://www.simplyrecipes.com/wp-content/uploads/2018/07/Guacamole-LEAD-6.jpg", "jpg"));

        final Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipe(guacamole);
        guacamoleNotes.setRecipeNotes("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.\n" +
                "Guacamole! Did you know that over 2 billion pounds of avocados are consumed each year in the U.S.? (Google it.) That’s over 7 pounds per person. I’m guessing that most of those avocados go into what has become America’s favorite dip, guacamole.\n" +
                "WHERE DOES GUACAMOLE COME FROM?\n" +
                "The word “guacamole”, and the dip, are both originally from Mexico, where avocados have been cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "INGREDIENTS FOR EASY GUACAMOLE\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "\n" +
                "GUACAMOLE TIP: USE RIPE AVOCADOS\n" +
                "The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.\n" +
                "\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n" +
                "THE BEST WAY TO CUT AN AVOCADO\n" +
                "To slice open an avocado, cut it in half lengthwise with a sharp chef’s knife and twist apart the sides. One side will have the pit. To remove it, you can do one of two things:\n" +
                "\n" +
                "Method #1: Gently tap the pit with your chef’s knife so the knife gets wedged into the pit. Twist your knife slightly to dislodge the pit and lift to remove. If you use this method, first protect your hand with a thick kitchen towel before proceeding.\n" +
                "Method #2: Cut the side with the pit in half again, exposing more of the pit. Use your fingers or a spoon to remove the pit\n" +
                "Once the pit is removed, just cut the avocado into chunks right inside the peel and use a spoon to scoop them out.\n" +
                "GUACAMOLE VARIATIONS\n" +
                "Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!\n" +
                "\n" +
                "Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "OTHER WAYS TO USE GUACAMOLE\n" +
                "Guacamole has a role in the kitchen beyond a party dip, of course. It’s great scooped on top of nachos and also makes an excellent topping or side for enchiladas, tacos, grilled salmon, or oven-baked chicken.\n" +
                "\n" +
                "Guacamole is great in foods, as well. Try mixing some guacamole into a tuna sandwich or your next batch of deviled eggs.\n" +
                "\n" +
                "HOW TO STORE GUACAMOLE\n" +
                "Guacamole is best eaten right after it’s made. Like apples, avocados start to oxidize and turn brown once they’ve been cut. That said, the acid in the lime juice you add to guacamole can help slow down that process, and if you store the guacamole properly, you can easily make it a few hours ahead if you are preparing for a party.\n" +
                "\n" +
                "The trick to keeping guacamole green is to make sure air doesn’t touch it! Transfer it to a container, cover with plastic wrap, and press down on the plastic wrap to squeeze out any air pockets. Make sure any exposed surface of the guacamole is touching the plastic wrap, not air. This will keep the amount of browning to a minimum.\n" +
                "\n" +
                "You can store the guacamole in the fridge this way for up to three days.\n" +
                "\n" +
                "If you leave the guacamole exposed to air, it will start to brown and discolor. That browning isn’t very appetizing, but the guacamole is still good. You can either scrape off the brown parts and discard, or stir them into the rest of the guacamole.");

        guacamole.setNotes(guacamoleNotes);

        guacamole.addIngredient(new Ingredient("Avocado", BigDecimal.valueOf(2), unit));
        guacamole.addIngredient(new Ingredient("Salt", BigDecimal.valueOf(.25), teaspoon));
        guacamole.addIngredient(new Ingredient("Fresh lime or lemon juice", BigDecimal.ONE, tablespoon));
        guacamole.addIngredient(new Ingredient("Minced red onionGuacamole or thinly sliced green onionGuacamole", BigDecimal.valueOf(.25), cup));
        guacamole.addIngredient(new Ingredient("Serrano chilesGuacamole, stems and seeds removed, minced", BigDecimal.valueOf(2), unit));
        guacamole.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2), tablespoon));
        guacamole.addIngredient(new Ingredient("Freshly grated black pepper", BigDecimal.ONE, dash));
        guacamole.addIngredient(new Ingredient("Freshly grated black pepper", BigDecimal.valueOf(.5), unit));
        guacamole.addIngredient(new Ingredient("Jicama, to garnish", BigDecimal.ONE, unit));
        guacamole.addIngredient(new Ingredient("Tortilla chips", BigDecimal.ONE, bag));

        Recipe spicyChickenTacos = new Recipe();
        spicyChickenTacos.setDescription("Spicy Chicken Tacos");
        spicyChickenTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat." +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, saltGuacamole, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings." +
                "Spicy Grilled Chicken Tacos" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocadoGuacamole, radishes, tomatoes, and onionGuacamole slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        spicyChickenTacos.setServings(4);
        spicyChickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyChickenTacos.setPrepTime(20);
        spicyChickenTacos.setCookTime(15);
        spicyChickenTacos.setDifficulty(Difficulty.MODERATE);
        spicyChickenTacos.getCategories().add(mexican);
        spicyChickenTacos.setImage(this.imageConverter.convertFromUrl("https://www.simplyrecipes.com/wp-content/uploads/2017/05/2017-05-29-GrilledChickenTacos-2.jpg", "jpg"));

        final Notes spicyChickenTacosNotes = new Notes();
        spicyChickenTacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");

        spicyChickenTacos.setNotes(spicyChickenTacosNotes);

        spicyChickenTacos.addIngredient(new Ingredient("Ancho chili powder", BigDecimal.valueOf(2), tablespoon));
        spicyChickenTacos.addIngredient(new Ingredient("Salt", BigDecimal.valueOf(.5), teaspoon));
        spicyChickenTacos.addIngredient(new Ingredient("Dried oregano", BigDecimal.ONE, teaspoon));
        spicyChickenTacos.addIngredient(new Ingredient("Cumin", BigDecimal.ONE, teaspoon));
        spicyChickenTacos.addIngredient(new Ingredient("Sugar", BigDecimal.ONE, teaspoon));
        spicyChickenTacos.addIngredient(new Ingredient("Garlic", BigDecimal.ONE, clove));
        spicyChickenTacos.addIngredient(new Ingredient("Orange zest", BigDecimal.ONE, tablespoon));
        spicyChickenTacos.addIngredient(new Ingredient("Orange juice", BigDecimal.valueOf(3), tablespoon));
        spicyChickenTacos.addIngredient(new Ingredient("Olive oil", BigDecimal.valueOf(2), tablespoon));
        spicyChickenTacos.addIngredient(new Ingredient("Boneless chicken thighs", BigDecimal.valueOf(1.25), pound));
        spicyChickenTacos.addIngredient(new Ingredient("Corn tortillas", BigDecimal.valueOf(8), unit));
        spicyChickenTacos.addIngredient(new Ingredient("Baby arugula", BigDecimal.valueOf(3), cup));
        spicyChickenTacos.addIngredient(new Ingredient("Medium ripe avocado", BigDecimal.valueOf(2), unit));
        spicyChickenTacos.addIngredient(new Ingredient("Radish, thinly sliced",BigDecimal.valueOf(4), unit));
        spicyChickenTacos.addIngredient(new Ingredient("Cherry Tomato, halved", BigDecimal.valueOf(.5), pint));
        spicyChickenTacos.addIngredient(new Ingredient("Red onion, thinly sliced", BigDecimal.valueOf(.25), pint));
        spicyChickenTacos.addIngredient(new Ingredient("Cilantro, roughly chopped",BigDecimal.ONE, cup));
        spicyChickenTacos.addIngredient(new Ingredient("Sour cream", BigDecimal.valueOf(.5), cup));
        spicyChickenTacos.addIngredient(new Ingredient("Milk", BigDecimal.valueOf(.25), cup));
        spicyChickenTacos.addIngredient(new Ingredient("Lime, wedged", BigDecimal.ONE, unit));

        final Set<Recipe> recipes = new HashSet<>();
        recipes.add(guacamole);
        recipes.add(spicyChickenTacos);

        this.recipeRepository.saveAll(recipes);

    }

}
