package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    //guardar lo que viene de los repositorios
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);

        //get Units Of Measure
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected Each UOM not found");
        }
        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected Tablespoon UOM not found");
        }
        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected Teaspoon UOM not found");
        }
        Optional<UnitOfMeasure> dashUOMOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUOMOptional.isPresent()){
            throw new RuntimeException("Expected Dash UOM not found");
        }
        Optional<UnitOfMeasure> pintUOMOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUOMOptional.isPresent()){
            throw new RuntimeException("Expected Pint UOM not found");
        }
        Optional<UnitOfMeasure> cupsUOMOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupsUOMOptional.isPresent()){
            throw new RuntimeException("Expected Cup UOM not found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUOM = tableSpoonUomOptional.get();
        UnitOfMeasure teaspoonUOM = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUOMOptional.get();
        UnitOfMeasure pintUom = pintUOMOptional.get();
        UnitOfMeasure cupsUom = cupsUOMOptional.get();

        //Categorias
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Creamos la receta
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1. Cut the avocados: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl\n" +
                "2. Mash the avocado flesh: Using a fork, roughly mash the avocado. \n" +
                "3. Add the remaining ingredients to taste: Sprinkle with salt and lime (or lemon) juice. Add the chopped onion, cilantro, black pepper, and chilis.\n" +
                "4. Serve immediately\n");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Chilling tomatoes hurts their flavor.\n" +
                "So, if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setNotes(guacNotes);
        guacRecipe.addIngredient(new Ingredient("Ripe Avocados",new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt",new BigDecimal(0.5), teaspoonUOM));
        guacRecipe.addIngredient(new Ingredient("Fresh lime/lemon juice",new BigDecimal(2), tableSpoonUOM));
        guacRecipe.addIngredient(new Ingredient("Minced Red/green onion ",new BigDecimal(2), tableSpoonUOM));
        guacRecipe.addIngredient(new Ingredient("Serrano chiles, stems and seeds removed",new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro",new BigDecimal(2),tableSpoonUOM));
        guacRecipe.addIngredient(new Ingredient("Freshly grated black pepper",new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed",new BigDecimal(0.5), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        //Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(15);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1. Prepare the grill: Prepare either a gas or charcoal grill for medium-high, direct heat.\n" +
                "2. Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over. Set aside.\n" +
                "3. Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes.\n" +
                "4. Thin the sour cream with milk: Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.\n" +
                "5. Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "6. Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side. Wrap warmed tortillas in a tea towel to keep them warm until serving.\n");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)\n" +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/\n");
        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho chilli powder", new BigDecimal(2), tableSpoonUOM));
        tacosRecipe.addIngredient(new Ingredient("Dried oregano", new BigDecimal(1), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1),  teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(0.5), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("Corn tortillas", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Avocado, sliced", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Boneless Chicken thighs", new BigDecimal(6), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Onion, sliced", new BigDecimal(0.25), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Sour cream", new BigDecimal(0.5),  cupsUom));
        tacosRecipe.addIngredient(new Ingredient("Lime/lemon", new BigDecimal(1),  eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;
    }
}
