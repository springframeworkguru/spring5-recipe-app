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

        perfectGuacamole.addIngredient(new Ingredient("ripe avocados", BigDecimal.valueOf(2L), null));
        perfectGuacamole.addIngredient(new Ingredient("salt", BigDecimal.valueOf(.25), teaspoon));
        perfectGuacamole.addIngredient(new Ingredient("fresh lime juice", BigDecimal.valueOf(1), tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("minced red onion", BigDecimal.valueOf(2), tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("serrano chiles", BigDecimal.valueOf(2), null));

        perfectGuacamole.setDirections("Directions");

        Notes notes = new Notes();
        notes.setRecipeNotes("Note 1");
        perfectGuacamole.setNotes(notes);
        recipes.add(perfectGuacamole);

        Recipe spicyGrilledChicken = new Recipe();
        spicyGrilledChicken.setDescription("Spicy Grilled Chicken Tacos");

        Notes note2 = new Notes();
        note2.setRecipeNotes("Note 2");
        spicyGrilledChicken.setNotes(note2);

        recipes.add(spicyGrilledChicken);

        return recipes;
    }

}
