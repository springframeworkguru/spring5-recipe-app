package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final UnitOfMeasureRepository unitOfMeasureRepository ;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository repository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("I am in data loader class");
        Recipe guacamole = new Recipe();

        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure piece = unitOfMeasureRepository.findByDescription("Piece").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();

        Category mexican = categoryRepository.findByDescription("Mexican").get();
        Category american = categoryRepository.findByDescription("American").get();

        mexican.getRecipes().add(guacamole);
        american.getRecipes().add(guacamole);
        guacamole.getCategories().add(mexican);
        guacamole.getCategories().add(american);
        guacamole.addIngredient(new Ingredient("Avocado",new BigDecimal(2),piece));
        guacamole.addIngredient(new Ingredient("Kosher Salt",new BigDecimal(1/4),teaspoon));
        guacamole.addIngredient(new Ingredient("Lime",new BigDecimal(1),tablespoon));
        guacamole.addIngredient(new Ingredient("Onion",new BigDecimal(2),piece));
        guacamole.addIngredient(new Ingredient("Chili",new BigDecimal(1),piece));
        guacamole.addIngredient(new Ingredient("Cilantro",new BigDecimal(2),tablespoon));
        guacamole.addIngredient(new Ingredient("Black Pepper",new BigDecimal(1),pinch));
        guacamole.addIngredient(new Ingredient("Tomato",new BigDecimal(1),piece));

        guacamole.setDirection("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");
        guacamole.setCookTime(0);
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        guacNotes.setRecipe(guacamole);
        guacamole.setNotes(guacNotes);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDescription("guacamole");
        recipeRepository.save(guacamole);





    }
}
