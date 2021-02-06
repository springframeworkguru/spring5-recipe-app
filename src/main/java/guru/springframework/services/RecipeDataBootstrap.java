package guru.springframework.services;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
public class RecipeDataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository uomRepository;
    private final CategoryRepository categoryRepository;
    private List<UnitOfMeasure> units;

    public RecipeDataBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
        this.categoryRepository = categoryRepository;

        loadUnits();
    }

    private void loadUnits() {
        if (units != null && !units.isEmpty()) {
            return;
        }

        UnitOfMeasure teaspoon = uomRepository.findByDescription("Teaspoon").orElse(null);
        UnitOfMeasure tablespoon = uomRepository.findByDescription("Tablespoon").orElse(null);
        UnitOfMeasure cup = uomRepository.findByDescription("Cup").orElse(null);
        UnitOfMeasure dash = uomRepository.findByDescription("Dash").orElse(null);
        UnitOfMeasure clove = uomRepository.findByDescription("Clove").orElse(null);
        UnitOfMeasure pint = uomRepository.findByDescription("Pint").orElse(null);

        if (teaspoon == null || tablespoon == null || cup == null || dash == null || clove == null || pint == null) {
            throw new RuntimeException("Uom not found");
        }
        units = new ArrayList<>(Arrays.asList(teaspoon, tablespoon, cup, dash, clove, pint));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadData();

        recipeRepository.findAll().forEach(recipe -> System.out.println("Recipe id: " + recipe.getId()
                + " AND Recipe Description: " + recipe.getDescription()));
    }

    private UnitOfMeasure findUnit(String unitName) {
        return units.stream()
                .filter(unit -> unit.getDescription().equals(unitName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unit of Measure not found"));
    }

    private void loadData() {
        Recipe perfectGuacamole = new Recipe();
        Recipe chickenTacos = new Recipe();

        Category dip = new Category();
        dip.setDescription("Dip");
        dip.addRecipe(perfectGuacamole);
        categoryRepository.save(dip);

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipe(perfectGuacamole);
        guacamoleNotes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.\n");

        perfectGuacamole.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.\n");
        perfectGuacamole.addCategory(dip);
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("SimplyRecipes");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/#recipe159");
        perfectGuacamole.setIngredients(getGuacamoleIngredients(perfectGuacamole));
        perfectGuacamole.setNotes(guacamoleNotes);
        perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl...\n");

        Notes chickenTacosNotes = new Notes();
        chickenTacosNotes.setRecipe(chickenTacos);
        chickenTacosNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder...)");

        Category grill = new Category();
        grill.setRecipes(new HashSet<>(Arrays.asList(chickenTacos)));
        grill.setDescription("Grill");

        Category quick = new Category();
        quick.setRecipes(new HashSet<>(Arrays.asList(chickenTacos)));
        quick.setDescription("Quick");

        categoryRepository.save(grill);
        categoryRepository.save(quick);

        log.debug("Loaded extra categories");

        chickenTacos.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.\n");
        chickenTacos.setPrepTime(20);
        chickenTacos.setCookTime(15);
        chickenTacos.setServings(6);
        chickenTacos.setSource("SimplyRecipes");
        chickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" + "\n" + "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic...");
        chickenTacos.setIngredients(getChickenTacosIngredients(chickenTacos));
        chickenTacos.setDifficulty(Difficulty.EASY);
        chickenTacos.setNotes(chickenTacosNotes);
        chickenTacos.setCategories(new HashSet<>(Arrays.asList(grill, quick)));

        recipeRepository.save(perfectGuacamole);
        recipeRepository.save(chickenTacos);

        log.debug("Loaded recipes!");
    }

    public Set<Ingredient> getGuacamoleIngredients(Recipe perfectGuacamole) {
        Ingredient avocado = new Ingredient();
        avocado.setRecipe(perfectGuacamole);
        avocado.setAmount(BigDecimal.valueOf(2));
        avocado.setDescription("Ripe Avocados");

        Ingredient salt = new Ingredient();
        salt.setRecipe(perfectGuacamole);
        salt.setAmount(BigDecimal.valueOf(0.25));
        salt.setUom(findUnit("Teaspoon"));
        salt.setDescription("Salt for the taste");

        Ingredient lime = new Ingredient();
        lime.setRecipe(perfectGuacamole);
        lime.setUom(findUnit("Tablespoon"));
        lime.setAmount(BigDecimal.valueOf(1));
        lime.setDescription("Fresh lime juice or lemon juice");

        Ingredient onion = new Ingredient();
        onion.setRecipe(perfectGuacamole);
        onion.setUom(findUnit("Cup"));
        onion.setAmount(BigDecimal.valueOf(0.25));
        onion.setDescription("minced red onion or thinly sliced green onion");

        Ingredient chile = new Ingredient();
        chile.setRecipe(perfectGuacamole);
        chile.setAmount(BigDecimal.ONE);
        chile.setDescription("serrano chiles, stems and seeds removed, minced");

        Ingredient cilantro = new Ingredient();
        cilantro.setRecipe(perfectGuacamole);
        cilantro.setAmount(BigDecimal.valueOf(2));
        cilantro.setUom(findUnit("Tablespoon"));
        cilantro.setDescription("leaves and tender stems, finely chopped");

        Ingredient blackPepper = new Ingredient();
        blackPepper.setRecipe(perfectGuacamole);
        blackPepper.setAmount(BigDecimal.ONE);
        blackPepper.setUom(findUnit("Dash"));
        blackPepper.setDescription("freshly grated black pepper");

        Ingredient tomato = new Ingredient();
        tomato.setRecipe(perfectGuacamole);
        tomato.setAmount(BigDecimal.valueOf(0.5));
        tomato.setDescription("ripe tomato, seeds and pulp removed, chopped");

        Ingredient radish = new Ingredient();
        radish.setRecipe(perfectGuacamole);
        radish.setDescription("Red radishes or jicama, to garnish");

        Ingredient tortilla = new Ingredient();
        tortilla.setRecipe(perfectGuacamole);
        tortilla.setDescription("Tortilla chips, to serve");

        return new HashSet<>(Arrays.asList(avocado, salt, lime, onion, chile, cilantro, blackPepper, tomato, radish, tortilla));
    }

    private Set<Ingredient> getChickenTacosIngredients(Recipe chickenTacos) {
        Ingredient chiliPowder = new Ingredient();
        chiliPowder.setRecipe(chickenTacos);
        chiliPowder.setDescription("ancho chili powder");
        chiliPowder.setAmount(BigDecimal.valueOf(2));
        chiliPowder.setUom(findUnit("Tablespoon"));

        Ingredient oregano = new Ingredient();
        oregano.setRecipe(chickenTacos);
        oregano.setDescription("dried oregano");
        oregano.setAmount(BigDecimal.valueOf(1));
        oregano.setUom(findUnit("Teaspoon"));

        Ingredient cumin = new Ingredient();
        cumin.setRecipe(chickenTacos);
        cumin.setDescription("dried cumin");
        cumin.setAmount(BigDecimal.valueOf(1));
        cumin.setUom(findUnit("Teaspoon"));

        Ingredient sugar = new Ingredient();
        sugar.setRecipe(chickenTacos);
        sugar.setDescription("sugar");
        sugar.setAmount(BigDecimal.valueOf(1));
        sugar.setUom(findUnit("Teaspoon"));

        Ingredient saltForChicken = new Ingredient();
        saltForChicken.setRecipe(chickenTacos);
        saltForChicken.setDescription("salt");
        saltForChicken.setAmount(BigDecimal.valueOf(0.5));
        saltForChicken.setUom(findUnit("Teaspoon"));

        Ingredient garlic = new Ingredient();
        garlic.setRecipe(chickenTacos);
        garlic.setDescription("garlic, finely chopped");
        garlic.setAmount(BigDecimal.ONE);
        garlic.setUom(findUnit("Clove"));

        Ingredient orangeZest = new Ingredient();
        orangeZest.setRecipe(chickenTacos);
        orangeZest.setDescription("finely grated orange zest");
        orangeZest.setAmount(BigDecimal.ONE);
        orangeZest.setUom(findUnit("Tablespoon"));

        Ingredient orangeJuice = new Ingredient();
        orangeJuice.setRecipe(chickenTacos);
        orangeJuice.setDescription("fresh-squeezed orange juice");
        orangeJuice.setAmount(BigDecimal.valueOf(3));
        orangeJuice.setUom(findUnit("Tablespoon"));

        Ingredient oliveOil = new Ingredient();
        oliveOil.setRecipe(chickenTacos);
        oliveOil.setDescription("olive oil");
        oliveOil.setAmount(BigDecimal.valueOf(2));
        oliveOil.setUom(findUnit("Tablespoon"));

        Ingredient cornTortillas = new Ingredient();
        cornTortillas.setRecipe(chickenTacos);
        cornTortillas.setDescription("corn tortillas");
        cornTortillas.setAmount(BigDecimal.valueOf(8));

        Ingredient babyArugula = new Ingredient();
        babyArugula.setRecipe(chickenTacos);
        babyArugula.setDescription("packed baby arugula");
        babyArugula.setAmount(BigDecimal.valueOf(3));
        babyArugula.setUom(findUnit("Cup"));

        Ingredient avocadoForTortilla = new Ingredient();
        avocadoForTortilla.setRecipe(chickenTacos);
        avocadoForTortilla.setDescription("medium ripe avocados, sliced");
        avocadoForTortilla.setAmount(BigDecimal.valueOf(2));

        Ingredient radishForTortilla = new Ingredient();
        radishForTortilla.setRecipe(chickenTacos);
        radishForTortilla.setDescription("radishes, thinly sliced");
        radishForTortilla.setAmount(BigDecimal.valueOf(4));

        Ingredient cherryTomatoes = new Ingredient();
        cherryTomatoes.setRecipe(chickenTacos);
        cherryTomatoes.setDescription("cherry tomatoes, halved");
        cherryTomatoes.setAmount(BigDecimal.valueOf(0.5));
        cherryTomatoes.setUom(findUnit("Pint"));

        Ingredient redOnion = new Ingredient();
        redOnion.setRecipe(chickenTacos);
        redOnion.setDescription("red onion, thinly sliced");
        redOnion.setAmount(BigDecimal.valueOf(0.25));

        Ingredient cilantroForTortilla = new Ingredient();
        cilantroForTortilla.setRecipe(chickenTacos);
        cilantroForTortilla.setDescription("Roughly chopped cilantro");

        Ingredient sourCream = new Ingredient();
        sourCream.setRecipe(chickenTacos);
        sourCream.setDescription("sour cream");
        sourCream.setAmount(BigDecimal.valueOf(0.5));
        sourCream.setUom(findUnit("Cup"));

        Ingredient milk = new Ingredient();
        milk.setRecipe(chickenTacos);
        milk.setDescription("milk");
        milk.setAmount(BigDecimal.valueOf(0.25));
        milk.setUom(findUnit("Cup"));

        Ingredient limeForTortilla = new Ingredient();
        limeForTortilla.setRecipe(chickenTacos);
        limeForTortilla.setDescription("lime, cut into wedges");
        limeForTortilla.setAmount(BigDecimal.ONE);

        return new HashSet<>(Arrays.asList(
                chiliPowder, oregano, cumin, sugar, saltForChicken, garlic, orangeZest, orangeJuice, oliveOil,
                cornTortillas, babyArugula, avocadoForTortilla, radishForTortilla, cherryTomatoes, redOnion,
                cilantroForTortilla, sourCream, milk, limeForTortilla
        ));
    }
}
