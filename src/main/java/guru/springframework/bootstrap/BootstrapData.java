package guru.springframework.bootstrap;

import guru.springframework.models.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapData(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        recipeRepository.saveAll(loadData());
    }

    private Set<Recipe> loadData() {

//        load Category
        Optional<Category> catAmerican = categoryRepository.findByDescription("American");
        if(!catAmerican.isPresent())
            throw new RuntimeException("American category not found");

        Optional<Category> catItalian = categoryRepository.findByDescription("Italian");
        if(!catItalian.isPresent())
            throw new RuntimeException("Italian category not found");

        Optional<Category> catMexican = categoryRepository.findByDescription("Mexican");
        if(!catMexican.isPresent())
            throw new RuntimeException("Mexican category not found");

        Optional<Category> catFastFood = categoryRepository.findByDescription("Fast Food");
        if(!catFastFood.isPresent())
            throw new RuntimeException("Fast Food category not found");


        Category americanCategory = catAmerican.get();
        Category italianCategory = catItalian.get();
        Category mexicanCategory = catMexican.get();
        Category fastfoodCategory = catFastFood.get();

        // load UOM
        Optional<UnitOfMeasure> uomTeaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!uomTeaspoon.isPresent())
            throw new RuntimeException("UOM Teaspoon not found");

        Optional<UnitOfMeasure> uomTablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!uomTablespoon.isPresent())
            throw new RuntimeException("UOM Tablespoon not found");

        Optional<UnitOfMeasure> uomCup = unitOfMeasureRepository.findByDescription("Cup");
        if(!uomCup.isPresent())
            throw new RuntimeException("UOM Cup not found");

        Optional<UnitOfMeasure> uomPinch = unitOfMeasureRepository.findByDescription("Pinch");
        if(!uomPinch.isPresent())
            throw new RuntimeException("UOM Pinch not found");

        Optional<UnitOfMeasure> uomOunce = unitOfMeasureRepository.findByDescription("Ounce");
        if(!uomOunce.isPresent())
            throw new RuntimeException("UOM Ounce not found");

        Optional<UnitOfMeasure> uomClove = unitOfMeasureRepository.findByDescription("Clove");
        if(!uomClove.isPresent())
            throw new RuntimeException("UOM Clove not found");

        Optional<UnitOfMeasure> uomPint = unitOfMeasureRepository.findByDescription("Pint");
        if(!uomPint.isPresent())
            throw new RuntimeException("UOM Pint not found");

        UnitOfMeasure teaspoon = uomTeaspoon.get();
        UnitOfMeasure tablespoon = uomTablespoon.get();
        UnitOfMeasure cup = uomCup.get();
        UnitOfMeasure pinch = uomPinch.get();
        UnitOfMeasure ounce = uomOunce.get();
        UnitOfMeasure clove = uomClove.get();
        UnitOfMeasure pint = uomPint.get();


        Set<Recipe> recipes = new HashSet<>();

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("How to Make Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setServings(15);
        perfectGuacamole.setSource("Good source");
        perfectGuacamole.setDifficulty(Difficulty.MODERATE);
        perfectGuacamole.getCategories().add(americanCategory);

        perfectGuacamole.getIngredients().add(new Ingredient("ripe avocados", BigDecimal.valueOf(2L), null, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("salt", BigDecimal.valueOf(.25), teaspoon, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("fresh lime juice", BigDecimal.valueOf(1), tablespoon, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("minced red onion", BigDecimal.valueOf(2), tablespoon, perfectGuacamole));
        perfectGuacamole.getIngredients().add(new Ingredient("serrano chiles", BigDecimal.valueOf(2), null, perfectGuacamole));

        perfectGuacamole.setDirections("Directions");

        Notes notes = new Notes();
        notes.setRecipe(perfectGuacamole);
        notes.setRecipeNotes("THE BEST WAY TO CUT AN AVOCADO\n" +
                "To slice open an avocado, cut it in half lengthwise with a sharp chef’s knife and twist apart the sides. One side will have the pit. To remove it, you can do one of two things:\n" +
                "\n" +
                "Method #1: Gently tap the pit with your chef’s knife so the knife gets wedged into the pit. Twist your knife slightly to dislodge the pit and lift to remove. If you use this method, first protect your hand with a thick kitchen towel before proceeding.\n" +
                "Method #2: Cut the side with the pit in half again, exposing more of the pit. Use your fingers or a spoon to remove the pit\n" +
                "Once the pit is removed, just cut the avocado into chunks right inside the peel and use a spoon to scoop them out.");
        perfectGuacamole.setNotes(notes);
        recipes.add(perfectGuacamole);

        Recipe spicyGrilledChicken = new Recipe();
        spicyGrilledChicken.setDescription("Spicy Grilled Chicken Tacos");

        recipes.add(spicyGrilledChicken);

        return recipes;
    }

}
