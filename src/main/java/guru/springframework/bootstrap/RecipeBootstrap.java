package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Started Loading boostrap data");
        recipeRepository.saveAll(getRecipes());
        log.debug("Finished Loading boostrap data");
    }


    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        log.debug("Creating the Units Of Measure");
        //get UOMs
        Optional<UnitOfMeasure>  eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure>  dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure>  ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if(!ounceUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure>  pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if(!pinchUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure>  cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure>  tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tablespoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure>  teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaspoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure>  pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pinchUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();

        log.debug("Creating the Categories");
        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if (!italianCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
        if (!fastFoodCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        // get optionals
        Category americanCategory = americanCategoryOptional.get();
        Category italianCategory = italianCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();
        Category fastFoodCategory = fastFoodCategoryOptional.get();

        log.debug("Creating the Guacamole recipe");
        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDescription("Loads of shite in here");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("Ripe avocados",new BigDecimal(2),eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt",new BigDecimal(0.25), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("Fresh Lime juice or Lemon juice",new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chillies, stems and seeds removed,  minced",new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro",new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper",new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped",new BigDecimal(0.5), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        log.debug("Creating teh Tacos recipe");
        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy grilled Chicken Tacos");
        tacosRecipe.setPrepTime(9);
        tacosRecipe.setCookTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDescription("MOre description stuff here");

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("More note stuff");
        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chilli Powder", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(0.5), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, chopped", new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thigh", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugala", new BigDecimal(3), cupUom));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(0.5), pintUom));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(0.25), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);

        return recipes;
    }


}
