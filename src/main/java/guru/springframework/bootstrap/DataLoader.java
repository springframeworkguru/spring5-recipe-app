package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("custom data loader boostrap runner");
        List<Recipe> recipes = getRecipe();
        recipeRepository.saveAll(recipes);
    }

    private List<Recipe> getRecipe() {
        List<Recipe> recipes = new ArrayList<>(2);

        UnitOfMeasure uom = new UnitOfMeasure();

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByUom("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Each not found");
        }
        System.out.println("the each unit of measure id has value " + eachUomOptional.get().getId());


        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByUom("Teaspoon");
        if(!teaspoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Teaspoon not found");
        }
        System.out.println("the Teaspoon unit of measure id has value " + teaspoonUomOptional.get().getId());

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByUom("Tablespoon");
        if(!tablespoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Tablespoon not found");
        }
        System.out.println("the tablespoon unit of measure id has value " + tablespoonUomOptional.get().getId());

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByUom("Cup");
        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Cup not found");
        }
        System.out.println("the cup unit of measure id has value " + cupUomOptional.get().getId());

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByUom("Pint");
        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Pint not found");
        }
        System.out.println("the Pint unit of measure id has value " + pintUomOptional.get().getId());

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByUom("Pinch");
        if(!pinchUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Pinch not found");
        }
        System.out.println("the Pinch unit of measure id has value " + pinchUomOptional.get().getId());

        Optional<UnitOfMeasure> smallUomOptional = unitOfMeasureRepository.findByUom("Small");
        if(!smallUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Small not found");
        }
        System.out.println("the Small unit of measure id has value " + smallUomOptional.get().getId());

        Optional<UnitOfMeasure> mediumUomOptional = unitOfMeasureRepository.findByUom("Medium");
        if(!mediumUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Medium not found");
        }
        System.out.println("the Medium unit of measure id has value " + mediumUomOptional.get().getId());

        Optional<UnitOfMeasure> poundsUomOptional = unitOfMeasureRepository.findByUom("Pounds");
        if(!poundsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Pounds not found");
        }
        System.out.println("the Pounds unit of measure id has value " + poundsUomOptional.get().getId());

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure smallUom = smallUomOptional.get();
        UnitOfMeasure mediumUom = mediumUomOptional.get();
        UnitOfMeasure poundsUom = poundsUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category American not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Mexican not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();
        System.out.println("------------- cat for american: " + americanCategory.getDescription());

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Put the avocado, the other shit, and some lime juice in a bowl. Add cumin. Stir. Serve.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("This is a note for guacamole: its really easy");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), pinchUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        Recipe chickenRecipe = new Recipe();
        chickenRecipe.setDescription("Some Chicken Thing");
        chickenRecipe.setPrepTime(20);
        chickenRecipe.setCookTime(30);
        chickenRecipe.setDifficulty(Difficulty.KIND_OF_HARD);
        chickenRecipe.setDirections("season the chicken, put it in the oven, bake it, and then eat it.");

        Notes chickenNotes = new Notes();
        chickenNotes.setRecipeNotes("This is a note for chicken stuff: its kinda hard");
        chickenNotes.setRecipe(chickenRecipe);
        chickenRecipe.setNotes(chickenNotes);

        chickenRecipe.addIngredient(new Ingredient("chicken", new BigDecimal(2), smallUom, chickenRecipe));
        chickenRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUom, chickenRecipe));
        chickenRecipe.addIngredient(new Ingredient("Pepper", new BigDecimal(2), pinchUom, chickenRecipe));
        chickenRecipe.addIngredient(new Ingredient("onions", new BigDecimal(2), mediumUom, chickenRecipe));
        chickenRecipe.addIngredient(new Ingredient("rice", new BigDecimal(2), cupUom, chickenRecipe));
        chickenRecipe.addIngredient(new Ingredient("lemon juice", new BigDecimal(1), tableSpoonUom, chickenRecipe));
        chickenRecipe.addIngredient(new Ingredient("wine", new BigDecimal(1), eachUom, chickenRecipe));

        chickenRecipe.getCategories().add(americanCategory);

        recipes.add(chickenRecipe);

        return recipes;
    }
}
