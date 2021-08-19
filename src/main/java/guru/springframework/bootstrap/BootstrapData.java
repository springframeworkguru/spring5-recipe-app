package guru.springframework.bootstrap;

import guru.springframework.domains.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public BootstrapData(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.debug("Bootstrap started.");

        UnitOfMeasure ripe = unitOfMeasureRepository.findByDescription("Ripe").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure piece = unitOfMeasureRepository.findByDescription("Piece").get();

        Category mexican = categoryRepository.findByName("Mexican").get();

        Recipe guacamole = new Recipe();

        Ingredient avocado = new Ingredient();
        avocado.setAmount(new BigDecimal(2));
        avocado.setUom(ripe);
        avocado.setDescription("Avocado");

        guacamole.addIngredient(avocado);

        Ingredient salt = new Ingredient();
        salt.setDescription("Salt");
        salt.setAmount(new BigDecimal(1/4));
        salt.setUom(teaspoon);

        guacamole.addIngredient(salt);

        Ingredient lemonJuice = new Ingredient();
        lemonJuice.setDescription("Lemon Juice");
        lemonJuice.setAmount(new BigDecimal(1));
        lemonJuice.setUom(tablespoon);

        guacamole.addIngredient(lemonJuice);

        Ingredient mincedRedOnion = new Ingredient();
        mincedRedOnion.setDescription("Minced red onion");
        mincedRedOnion.setAmount(new BigDecimal(2));
        mincedRedOnion.setUom(tablespoon);

        guacamole.addIngredient(mincedRedOnion);

        Ingredient serranoChilis = new Ingredient();
        serranoChilis.setDescription("Serrano Chilis");
        serranoChilis.setAmount(new BigDecimal(1));
        serranoChilis.setUom(piece);

        guacamole.addIngredient(serranoChilis);

        guacamole.getCategories().add(mexican);

        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDescription("A very easy and practical mexican dish made with avocados.");

        recipeRepository.save(guacamole);

        log.debug("Guacamole recipe has been saved to the repository.");
    }


}
