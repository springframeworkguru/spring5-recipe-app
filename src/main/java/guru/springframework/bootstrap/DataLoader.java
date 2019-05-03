package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private IngredientRepository ingredientRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;

    public DataLoader(IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadData();
    }

    private void loadData() {

        /**
         * Recipe - Perfect Guacaomole
          */
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Perfect Guacamole");
        recipe1.setPrepTime(10);
        recipe1.setCookTime(10);
        recipe1.setServings(3);
        recipe1.setSource("SimplyRecipes.com");
        recipe1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        String directions = "<ul>\n" +
                "    <li>Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.</li>\n" +
                "    <li>2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)</li>\n" +
                "    <li>3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.</li>\n" +
                "    <li>Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.</li>\n" +
                "    <li>Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.</li>\n" +
                "    <li>4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.</li>\n" +
                "    <li>Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.</li>\n" +
                "</ul>";
        recipe1.setDirections(directions);
        Recipe savedRecipe1 = recipeRepository.save(recipe1);

        // Category - Mexican
        Optional<Category> categoryMexican = categoryRepository.findCategoryByDescription("Mexican");
        if (categoryMexican.isPresent()) {

            System.out.println("cat.id: " + categoryMexican.get().getId());

            Set<Category> categories = new HashSet<>();
            categoryMexican.ifPresent(o -> categories.add(o));
            categoryMexican.ifPresent(o -> savedRecipe1.setCategories(categories));

            Set<Recipe> recipes = new HashSet<>();
            categoryMexican.ifPresent(o -> recipes.add(savedRecipe1));
            categoryMexican.ifPresent(o -> o.setRecipes(recipes));
        }

        // Ingredient 1
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("ripe avocados");
        BigDecimal bigDecimalAmount1 = new BigDecimal("2");
        ingredient1.setAmount(bigDecimalAmount1);
        ingredient1.setRecipe(savedRecipe1);
        ingredientRepository.save(ingredient1);

        // Ingredient 2
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setDescription("Kosher salt");
        BigDecimal bigDecimalAmount2 = new BigDecimal("0.5");
        ingredient2.setAmount(bigDecimalAmount2);
        ingredient2.setRecipe(savedRecipe1);

        Optional<UnitOfMeasure> optionalUnitOfMeasure2 = unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");
        if (optionalUnitOfMeasure2.isPresent()) {
            optionalUnitOfMeasure2.ifPresent(o -> ingredient2.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient2);


        // Ingredient 3
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setDescription("fresh lime juice or lemon juice");
        BigDecimal bigDecimalAmount3 = new BigDecimal("1");
        ingredient3.setAmount(bigDecimalAmount3);
        ingredient3.setRecipe(savedRecipe1);

        Optional<UnitOfMeasure> optionalUnitOfMeasure3 = unitOfMeasureRepository.findUnitOfMeasureByDescription("Tablespoon");
        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient3.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient3);


        // Ingredient 4
        Ingredient ingredient4 = new Ingredient();
        ingredient4.setDescription("minced red onion or thinly sliced green onion");
        ingredient4.setAmount(bigDecimalAmount3);
        ingredient4.setRecipe(savedRecipe1);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient4.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient4);


        // Ingredient 5
        Ingredient ingredient5 = new Ingredient();
        ingredient5.setDescription("serrano chiles, stems and seeds removed, minced");
        BigDecimal bigDecimalAmount5 = new BigDecimal("1.5");
        ingredient5.setAmount(bigDecimalAmount5);
        ingredient5.setRecipe(savedRecipe1);
        ingredientRepository.save(ingredient5);


        // Ingredient 6
        Ingredient ingredient6 = new Ingredient();
        ingredient6.setDescription("cilantro (leaves and tender stems), finally chopped");
        ingredient6.setAmount(bigDecimalAmount1);
        ingredient6.setRecipe(savedRecipe1);

        if (optionalUnitOfMeasure3.isPresent()) {
            optionalUnitOfMeasure3.ifPresent(o -> ingredient6.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient6);


        // Ingredient 7
        Ingredient ingredient7 = new Ingredient();
        ingredient7.setDescription("freshly grated black pepper");
        ingredient7.setAmount(bigDecimalAmount3);
        ingredient7.setRecipe(savedRecipe1);

        Optional<UnitOfMeasure> optionalUnitOfMeasure7 = unitOfMeasureRepository.findUnitOfMeasureByDescription("Dash");
        if (optionalUnitOfMeasure7.isPresent()) {
            optionalUnitOfMeasure7.ifPresent(o -> ingredient7.setUnitOfMeasure(o));
        }
        ingredientRepository.save(ingredient7);


        // Ingredient 8
        Ingredient ingredient8 = new Ingredient();
        ingredient8.setDescription("ripe tomato, seeds and pulp remvoed, chopped");
        ingredient8.setAmount(bigDecimalAmount5);
        ingredient8.setRecipe(savedRecipe1);
        ingredientRepository.save(ingredient8);
    }
}
