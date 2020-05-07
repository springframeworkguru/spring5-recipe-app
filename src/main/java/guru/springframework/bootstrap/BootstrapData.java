package guru.springframework.bootstrap;

import guru.springframework.exceptions.NotFoundException;
import guru.springframework.models.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
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
        log.debug("loading bootstrap data");
    }

    private List<Recipe> loadData() {

        log.debug("loadData - start");

        List<Recipe> recipes = new ArrayList<>();

//        load Category
        Optional<Category> catAmerican = categoryRepository.findByDescription("American");
        if(!catAmerican.isPresent())
            throw new NotFoundException("American category not found");

        Optional<Category> catItalian = categoryRepository.findByDescription("Italian");
        if(!catItalian.isPresent())
            throw new NotFoundException("Italian category not found");

        Optional<Category> catMexican = categoryRepository.findByDescription("Mexican");
        if(!catMexican.isPresent())
            throw new NotFoundException("Mexican category not found");

        Optional<Category> catFastFood = categoryRepository.findByDescription("Fast Food");
        if(!catFastFood.isPresent())
            throw new NotFoundException("Fast Food category not found");

        Category americanCategory = catAmerican.get();
        Category italianCategory = catItalian.get();
        Category mexicanCategory = catMexican.get();
        Category fastfoodCategory = catFastFood.get();

        // load UOM
        Optional<UnitOfMeasure> uomTeaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!uomTeaspoon.isPresent())
            throw new NotFoundException("UOM Teaspoon not found");

        Optional<UnitOfMeasure> uomTablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!uomTablespoon.isPresent())
            throw new NotFoundException("UOM Tablespoon not found");

        Optional<UnitOfMeasure> uomCup = unitOfMeasureRepository.findByDescription("Cup");
        if(!uomCup.isPresent())
            throw new NotFoundException("UOM Cup not found");

        Optional<UnitOfMeasure> uomPinch = unitOfMeasureRepository.findByDescription("Pinch");
        if(!uomPinch.isPresent())
            throw new NotFoundException("UOM Pinch not found");

        Optional<UnitOfMeasure> uomOunce = unitOfMeasureRepository.findByDescription("Ounce");
        if(!uomOunce.isPresent())
            throw new NotFoundException("UOM Ounce not found");

        Optional<UnitOfMeasure> uomClove = unitOfMeasureRepository.findByDescription("Clove");
        if(!uomClove.isPresent())
            throw new NotFoundException("UOM Clove not found");

        Optional<UnitOfMeasure> uomPint = unitOfMeasureRepository.findByDescription("Pint");
        if(!uomPint.isPresent())
            throw new NotFoundException("UOM Pint not found");

        UnitOfMeasure teaspoon = uomTeaspoon.get();
        UnitOfMeasure tablespoon = uomTablespoon.get();
        UnitOfMeasure cup = uomCup.get();
        UnitOfMeasure pinch = uomPinch.get();
        UnitOfMeasure ounce = uomOunce.get();
        UnitOfMeasure clove = uomClove.get();
        UnitOfMeasure pint = uomPint.get();

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("How to Make Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("Good source");
        perfectGuacamole.setDifficulty(Difficulty.MODERATE);

        perfectGuacamole.setUrl("http://google.com");

        perfectGuacamole.getCategories().add(americanCategory);
        perfectGuacamole.getCategories().add(italianCategory);

        perfectGuacamole.addIngredient(new Ingredient("ripe avocados", BigDecimal.valueOf(2L), tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("salt", BigDecimal.valueOf(.25), teaspoon));
        perfectGuacamole.addIngredient(new Ingredient("fresh lime juice", BigDecimal.valueOf(1), tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("minced red onion", BigDecimal.valueOf(2), tablespoon));
        perfectGuacamole.addIngredient(new Ingredient("serrano chiles", BigDecimal.valueOf(2), tablespoon));

        perfectGuacamole.setDirections("Directions");

        Notes notes = new Notes();
        notes.setRecipeNotes("Note 1");
        perfectGuacamole.setNotes(notes);

        recipes.add(perfectGuacamole);

        ////////////////////////////////////////////////////////////

//        Recipe spicyGrilledChicken = new Recipe();
//        spicyGrilledChicken.setDescription("Spicy Grilled Chicken Tacos");
//
//        Notes note2 = new Notes();
//        note2.setRecipeNotes("Note 2");
//        spicyGrilledChicken.setNotes(note2);
//
//        recipes.add(spicyGrilledChicken);

        log.debug("loadData - finish");
        return recipes;
    }
}

