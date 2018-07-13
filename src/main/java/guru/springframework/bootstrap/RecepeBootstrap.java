package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.IngredientsRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class RecepeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientsRepository ingredientsRepository;

    public RecepeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,IngredientsRepository ingredientsRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init() {
        Recipe recipe = new Recipe();

        recipe.setCookTime(15);
        recipe.setPrepTime(20);

        recipe.setDescription("Spicy Grilled Chicken Tacos Recipe");
        recipe.setDifficulty(Difficulty.MODERATE);
        recipe.setServing(6);
        recipe.setDirections("none");
        recipe.setSource("www.simplyrecipes.com");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        Notes notes = new Notes();
        notes.setNotes("" +
                "1 Prepare a gas or charcoal grill for medium-high, direct heat.<br>\n" +
                "<br>\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.<br>\n" +
                "<br>\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings."+
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.<br>\n" +
                "<br>\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.<br>\n" +
                "<br>\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "<br>\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges."
        );
        recipe.setNotes(notes);

        UnitOfMeasure tablespoons = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure pounds = unitOfMeasureRepository.findByDescription("Pounds").get();

        recipe
                .addIngredients( new Ingredient("ancho chili powder",new BigDecimal(2),tablespoons))
                .addIngredients(new Ingredient("dried oregano",new BigDecimal(1),tablespoon))
                .addIngredients( new Ingredient("dried cumin",new BigDecimal(1),tablespoon))
                .addIngredients( new Ingredient("sugar",new BigDecimal(1),tablespoon))
                .addIngredients( new Ingredient("salt",new BigDecimal(0.5),tablespoon))
                .addIngredients( new Ingredient("garlic",new BigDecimal(1),tablespoon))
                .addIngredients( new Ingredient("finely grated orange zest",new BigDecimal(1),tablespoon))
                .addIngredients( new Ingredient("fresh-squeezed orange juice",new BigDecimal(3),tablespoons))
                .addIngredients( new Ingredient("olive oil",new BigDecimal(2),tablespoons))
                .addIngredients( new Ingredient("boneless chicken thighs",new BigDecimal(6),pounds));

        Category category = categoryRepository.findByCategoryName("American").get();
        Set<Category> categories = new HashSet<Category>();
        categories.add(category);
        recipe.setCategories(categories);
        recipeRepository.save(recipe);


    }
}
