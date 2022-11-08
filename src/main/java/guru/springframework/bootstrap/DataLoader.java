package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RecipeService recipeService;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(RecipeService recipeService, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeService = recipeService;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadData();
    }

    private void loadData() {
        Recipe chicken = new Recipe();
        String chickenDesc = loadChickenDesc();
        chicken.setDescription(chickenDesc);
        chicken.setPrepTime(20);
        chicken.setCookTime(15);
        chicken.setServings(4);
        chicken.setSource("");
        chicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        String chickenDirections = loadChickenDirections();
        chicken.setDirections(chickenDirections);
        loadChickenIngredients(chicken);
        loadChickenCategories(chicken);
        chicken.setNotes(new Notes(loadRecipeNotesChicken()));
        chicken.setDifficulty(Difficulty.EASY);

        recipeService.saveRecipe(chicken);

    }


    private void loadChickenCategories(Recipe chicken) {
        Optional<Category> optionalCategory = categoryRepository.findByDescription("Mexican");
        if (optionalCategory.isPresent()) {
            chicken.getCategories().add(optionalCategory.get());
        } else {
            throw new RuntimeException("Category not exists in DB");
        }
    }

    private void loadChickenIngredients(Recipe recipe) {
        recipe.addIngredient(new Ingredient("Chili", BigDecimal.valueOf(2), getUoM("Tablespoon")))
                .addIngredient(new Ingredient("Oregano", BigDecimal.valueOf(1), getUoM("Teaspoon")))
                .addIngredient(new Ingredient("Cumin", BigDecimal.valueOf(1), getUoM("Teaspoon")))
                .addIngredient(new Ingredient("Sugar", BigDecimal.valueOf(1), getUoM("Teaspoon")));
    }

    private UnitOfMeasure getUoM(String uom) {
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription(uom);
        if (optionalUnitOfMeasure.isPresent()) {
            return optionalUnitOfMeasure.get();
        } else {
            throw new RuntimeException("Unit of measure not exists in DB");
        }
    }

    private String loadChickenDesc() {
        return "Spicy Grilled Chicken Tacos";
    }

    private String loadChickenDirections() {
        return "Prepare the grill:\n" +
                "Prepare either a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "Thin the sour cream with milk:\n" +
                "Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.\n" +
                "\n" +
                "Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.";
    }

    private String loadRecipeNotesChicken() {
        return "We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today's tacos are more purposeful a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "Sally Vargas\n" +
                "The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well—this green isn't traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that's living!\n" +
                "\n";
    }
}
