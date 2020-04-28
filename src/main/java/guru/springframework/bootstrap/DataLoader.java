package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }


    public List<Recipe> getRecipes() {



        UnitOfMeasure count = unitOfMeasureRepository.findByDescription("Count").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();

        Category mexicanCategory = categoryRepository.findByDescription("Mexican").get();

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("How to make a perfect Guac!");

        Recipe guacamole = new Recipe();
        guacamole.getCategories().add(mexicanCategory);
        guacamole.setCookTime(1);
        guacamole.setDescription("Perfect Guacamole Recipe");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("Cut and mix all ingredients. Refrigerate until ready to serve.");

        guacamole.addIngredient(new Ingredient("Cilantro", BigDecimal.valueOf(2), tablespoon));
        guacamole.addIngredient(new Ingredient("Serrano Chiles", BigDecimal.valueOf(2), count));
        guacamole.addIngredient(new Ingredient("Chopped Onion", BigDecimal.valueOf(2), tablespoon));
        guacamole.addIngredient(new Ingredient("Lime Juice", BigDecimal.valueOf(1), tablespoon));
        guacamole.addIngredient(new Ingredient("Salt", BigDecimal.valueOf(.25), teaspoon));
        guacamole.addIngredient(new Ingredient("Avocdos", BigDecimal.valueOf(2), count));

        guacamole.setNotes(guacNotes);
        guacamole.setServings(4);
        guacamole.setPrepTime(1);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("http://localhost:8080/recipes");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(guacamole);
        return recipes;

    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading Bootstrap data");
        recipeRepository.saveAll(getRecipes());
    }
}
