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

import java.math.BigDecimal;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initGrilledChicken();

    }

    @Transactional
    protected void initGrilledChicken() {
        Category american = categoryRepository.findByCategoryName("American").get();
        Category mexican = categoryRepository.findByCategoryName("Mexican").get();
        Category fastFood = categoryRepository.findByCategoryName("Fast Food").get();

        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure cup = unitOfMeasureRepository.findByDescription("Cup").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();
        UnitOfMeasure ounce = unitOfMeasureRepository.findByDescription("Ounce").get();
        UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Dash").get();


        Recipe recipeChicken = new Recipe();
        recipeChicken.setDescription("Spicy Grilled Chicken Tacos Recipe");
        recipeChicken.setPrepTime(20);
        recipeChicken.setCookTime(15);
        recipeChicken.setServings(4);
        recipeChicken.setSource("Simply Recipes");
        recipeChicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipeChicken.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");
        recipeChicken.setDifficulty(Difficulty.EASY);
        //TODO set image
        recipeChicken.addCategory(american);
        recipeChicken.addCategory(fastFood);

        Ingredient anchoChiliPowder = new Ingredient();
        anchoChiliPowder.setAmount(BigDecimal.valueOf(2));
        anchoChiliPowder.setUom(tablespoon);
        anchoChiliPowder.setDescription("ancho chili powder");
        recipeChicken.addIngredient(anchoChiliPowder);

        Ingredient oregano = new Ingredient();
        oregano.setAmount(BigDecimal.valueOf(1));
        oregano.setUom(teaspoon);
        oregano.setDescription("dried oregano");
        recipeChicken.addIngredient(oregano);

        Ingredient cumin = new Ingredient();
        cumin.setAmount(BigDecimal.valueOf(1));
        cumin.setUom(teaspoon);
        cumin.setDescription("dried cumin");
        recipeChicken.addIngredient(cumin);

        Ingredient sugar = new Ingredient();
        sugar.setAmount(BigDecimal.valueOf(1));
        sugar.setUom(teaspoon);
        sugar.setDescription("sugar");
        recipeChicken.addIngredient(sugar);

        Ingredient salt = new Ingredient();
        salt.setAmount(BigDecimal.valueOf(0.5));
        salt.setUom(teaspoon);
        salt.setDescription("salt");
        recipeChicken.addIngredient(salt);

        Ingredient garlic = new Ingredient();
        garlic.setAmount(BigDecimal.valueOf(1));
        garlic.setDescription("clove garlic, finely chopped");
        recipeChicken.addIngredient(garlic);

        Ingredient zest = new Ingredient();
        zest.setAmount(BigDecimal.valueOf(1));
        zest.setUom(tablespoon);
        zest.setDescription("finely grated orange zest");
        recipeChicken.addIngredient(zest);

        Ingredient juice = new Ingredient();
        juice.setAmount(BigDecimal.valueOf(3));
        juice.setUom(tablespoon);
        juice.setDescription("fresh-squeezed orange juice");
        recipeChicken.addIngredient(juice);

        Ingredient oil = new Ingredient();
        oil.setAmount(BigDecimal.valueOf(2));
        oil.setUom(tablespoon);
        oil.setDescription("olive oil");
        recipeChicken.addIngredient(oil);

        Ingredient chicken = new Ingredient();
        chicken.setAmount(BigDecimal.valueOf(5));
        chicken.setDescription("skinless, boneless chicken thighs");
        recipeChicken.addIngredient(chicken);

        Recipe savedRecipe = recipeRepository.save(recipeChicken);
        log.debug("Saved recipe {}", savedRecipe);
        System.out.println(String.format("Saved recipe %s", savedRecipe.getDescription()));


    }


}
