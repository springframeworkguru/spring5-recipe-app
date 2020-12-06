package cc.paukner.bootstrap;

import cc.paukner.domain.Category;
import cc.paukner.domain.Difficulty;
import cc.paukner.domain.Ingredient;
import cc.paukner.domain.Notes;
import cc.paukner.domain.Recipe;
import cc.paukner.domain.UnitOfMeasure;
import cc.paukner.repositories.CategoryRepository;
import cc.paukner.repositories.RecipeRepository;
import cc.paukner.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataInitializer(RecipeRepository recipeRepository,
                           CategoryRepository categoryRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private <T> T ensureExists(Optional<T> item) {
        if (!item.isPresent()) {
            throw new RuntimeException("Expected item not found");
        } else {
            return item.get();
        }
    }

    private List<Recipe> getRecipes() {
        Category mexican = ensureExists(categoryRepository.findByDescription("Mexican"));
        Category american = ensureExists(categoryRepository.findByDescription("American"));

        UnitOfMeasure piece = ensureExists(unitOfMeasureRepository.findByDescription("Piece"));
        UnitOfMeasure teaspoon = ensureExists(unitOfMeasureRepository.findByDescription("Teaspoon"));
        UnitOfMeasure tablespoon = ensureExists(unitOfMeasureRepository.findByDescription("Tablespoon"));

        Ingredient avocados = new Ingredient("Avocados", BigDecimal.valueOf(2), piece);
        Ingredient salt = new Ingredient("Salt", BigDecimal.valueOf(.25), teaspoon);

        Recipe guacamole = new Recipe();
        guacamole.setDescription("How to make perfect guacamole");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setCategories(Set.of(mexican, american));
        guacamole.setIngredients(Set.of(avocados, salt));
        guacamole.setDirections("Cut, do and cook. Serve with wine.");
        guacamole.setNotes(new Notes("Nota bene"));


        Ingredient chiliPowder = new Ingredient("Chili powder", BigDecimal.valueOf(2), tablespoon);
        Ingredient driedOregano = new Ingredient("Dried oregano", BigDecimal.valueOf(1), teaspoon);

        Recipe grilledChickenTacos = new Recipe();
        grilledChickenTacos.setDescription("Spicy grilled chicken tacos");
        grilledChickenTacos.setDifficulty(Difficulty.MODERATE);
        grilledChickenTacos.setCategories(Set.of(mexican));
        grilledChickenTacos.setIngredients(Set.of(chiliPowder, driedOregano));
        grilledChickenTacos.setDirections("Cut, heat up, cook, grill. Serve with beer.");
        grilledChickenTacos.setNotes(new Notes("My grilled notes"));


        return List.of(guacamole, grilledChickenTacos);
    }
}
