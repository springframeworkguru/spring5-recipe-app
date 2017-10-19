package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class Bootstraper implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public Bootstraper(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
                       UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {

        Category american = categoryRepository.findByDescription("American");
        Category mexican = categoryRepository.findByDescription("Mexican");

        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        UnitOfMeasure cup = unitOfMeasureRepository.findByDescription("Cup");
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch");
        UnitOfMeasure clove = unitOfMeasureRepository.findByDescription("Clove");
        UnitOfMeasure pint = unitOfMeasureRepository.findByDescription("Pint");
        UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Dash");
        UnitOfMeasure none = unitOfMeasureRepository.findByDescription("");

        Recipe guacRecipe = new Recipe();
        Note guacRecipeNote = new Note();


        guacRecipe.setCategories(new HashSet<>());
        guacRecipe.getCategories().add(mexican);

        guacRecipe.setNote(guacRecipeNote);

        mexican.setRecipes(new HashSet<>());
        mexican.getRecipes().add(guacRecipe);



        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setCookTime(5);
        guacRecipeNote.setRecipeNotes("Guacamole, a dip made from avocados, is originally from Mexico. " +
                "The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon " +
                "juice—a splash of acidity to balance the richness of the avocado. Then comes chopped cilantro, chiles, " +
                "onion, and tomato, if you want.\n" +
                "\n" +
                "The trick to making perfect guacamole is using good, ripe avocados. Check for ripeness by gently " +
                "pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not " +
                "taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado " +
                "may be past ripe and not good. In this case, taste test first before using.\n");
        guacRecipe.setDescription("How to Make Perfect Guacamole Recipe");
        guacRecipe.setPrepTime(10);
        guacRecipe.setServings(3);
        guacRecipe.setSource("Simply Recipes");
        guacRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        //Starting to Add the Ingredients Now!
        guacRecipe.setIngredients( new HashSet<>() );
        guacRecipe.addIngredient( new Ingredient("ripe avocados",new BigDecimal(2),none) );
        guacRecipe.addIngredient( new Ingredient("Kosher salt",new BigDecimal("0.5"),teaspoon) );
        guacRecipe.addIngredient( new Ingredient("fresh lime juice or lemon juice",new BigDecimal(1),tablespoon) );
        guacRecipe.addIngredient( new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal(2),tablespoon) );
        guacRecipe.addIngredient( new Ingredient("serranochiles, stems and seeds removed, minced",new BigDecimal(2),none) );
        guacRecipe.addIngredient( new Ingredient("cilantro (leaves and tender stems), finely chopped",new BigDecimal(2),tablespoon) );
        guacRecipe.addIngredient( new Ingredient("of freshly grated black pepper",new BigDecimal(1),dash) );
        guacRecipe.addIngredient( new Ingredient("ripe tomato, seeds and pulp removed, chopped",new BigDecimal("0.5"),none) );

        guacRecipe.setDirections("Method\n" +
                "\n" +
                "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the " +
                "avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) " +
                "Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be " +
                "a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the " +
                "lime juice will provide some balance to the richness of the avocado and will help delay the avocados" +
                " from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their" +
                " hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of" +
                " hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start" +
                " with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it" +
                " and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the " +
                "guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it" +
                " just before serving it!.");

        recipeRepository.save(guacRecipe);

        System.out.println(guacRecipe);


    }

}