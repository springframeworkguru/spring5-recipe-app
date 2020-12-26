package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class LoadData implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public LoadData(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.info("Loading Bootstrap Data");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachSpoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Each");
        if(!eachSpoonUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not Found!");
        }

        Optional<UnitOfMeasure> tableSpoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not Found!");
        }

        Optional<UnitOfMeasure> teaSpoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not Found!");
        }

        Optional<UnitOfMeasure> dashUnitOfMeasure = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not Found!");
        }

        Optional<UnitOfMeasure> pinchUnitOfMeasure = unitOfMeasureRepository.findByDescription("Pinch");
        if(!pinchUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not Found!");
        }

        Optional<UnitOfMeasure> cupUnitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected UOM not Found!");
        }

        // get Optionals
        UnitOfMeasure eachUOM = eachSpoonUnitOfMeasure.get();
        UnitOfMeasure tableSpoonUOM = tableSpoonUnitOfMeasure.get();
        UnitOfMeasure teaSpoonUOM = teaSpoonUnitOfMeasure.get();
        UnitOfMeasure dashUOM = dashUnitOfMeasure.get();
        UnitOfMeasure pintUOM = pinchUnitOfMeasure.get();
        UnitOfMeasure cupUOM = cupUnitOfMeasure.get();

        Optional<Category> americanOptional = categoryRepository.findByDescription("American");
        if(!americanOptional.isPresent()){
            throw new RuntimeException("Expected UOM not Found!");
        }

        Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanOptional.isPresent()){
            throw new RuntimeException("Expected UOM not Found!");
        }

        // get Optionals
        Category americanCategory = americanOptional.get();
        Category mexicanCategory = mexicanOptional.get();

        // Guacamole recipe
        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "\n"+
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"+
                "\n"+
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving."+
                "\n"+
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole."+
                "\n"+
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados."+
                "\n"+
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");
        perfectGuacamole.setNotes(guacamoleNotes);

        // Ingredients
        perfectGuacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaSpoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("Fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("serrano chilies, stem and seeds removed, minced", new BigDecimal(2), eachUOM));
        perfectGuacamole.addIngredient(new Ingredient("cilantro", new BigDecimal(2), tableSpoonUOM));
        perfectGuacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUOM));
        perfectGuacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(.5), eachUOM));

        perfectGuacamole.getCategories().add(americanCategory);
        perfectGuacamole.getCategories().add(mexicanCategory);

        recipes.add(perfectGuacamole);

        // taco recipe
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacoRecipe.setCookTime(9);
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat."+
                "\n"+
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings."+
                "\n"+
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes."+
                "\n"+
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving."+
                "\n"+
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house."+
                "\n"+
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!"+
                "\n"+
                "The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");
        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUOM));
        tacoRecipe.addIngredient((new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUOM)));
        tacoRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUOM));
        tacoRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUOM));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);

        return recipes;

    }
}
