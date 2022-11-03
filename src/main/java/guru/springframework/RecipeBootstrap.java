package guru.springframework;

import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
  private final CategoryRepository categoryRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final RecipeRepository recipeRepository;


  public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
    this.categoryRepository = categoryRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    recipeRepository.saveAll(getRecipes());
  }

  private List<Recipe> getRecipes() {
    List<Recipe> recipes = new ArrayList<>();
    Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
    if (!eachUomOptional.isPresent()) {
      throw new RuntimeException("Expected UOM Not Found");
    }
    Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
    if (!tableSpoonUomOptional.isPresent()) {
      throw new RuntimeException("Expected UOM Not Found");
    }
    Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
    if (!teaSpoonUomOptional.isPresent()) {
      throw new RuntimeException("Expected UOM Not Found");
    }
    Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
    if (!dashUomOptional.isPresent()) {
      throw new RuntimeException("Expected UOM Not Found");
    }
    Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
    if (!pintUomOptional.isPresent()) {
      throw new RuntimeException("Expected UOM Not Found");
    }
    Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
    if (!cupsUomOptional.isPresent()) {
      throw new RuntimeException("Expected UOM Not Found");
    }

    UnitOfMeasure eachUom = eachUomOptional.get();
    UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
    UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
    UnitOfMeasure dashUom = dashUomOptional.get();
    UnitOfMeasure pintUom = pintUomOptional.get();
    UnitOfMeasure cupsUom = cupsUomOptional.get();

    Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
    if (!americanCategoryOptional.isPresent()) {
      throw new RuntimeException("Expected Category Not Found");
    }
    Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
    if (!mexicanCategoryOptional.isPresent()) {
      throw new RuntimeException("Expected Category Not Found");
    }
    Category americanCategory = americanCategoryOptional.get();
    Category mexicanCategory = mexicanCategoryOptional.get();

    Recipe guacRecipe = new Recipe();
    guacRecipe.setDescription("Perfect Guacamole");
    guacRecipe.setPrepTime(10);
    guacRecipe.setCookTime(0);
    guacRecipe.setServings(4);
    guacRecipe.setSource("Simply Recipes");
    guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
    guacRecipe.setDirections(
        "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon\n"
        +
        "Place in a bowl.\n" +
        "\n" +
        "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
        "\n" +
        "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n"
        +
        "\n" +
        "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n"
        +
        "\n" +
        "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
        "\n" +
        "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n"
        +
        "\n" +
        "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
        "\n" +
        "\n" +
        "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");
    Notes guacNotes = new Notes();
    guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                             "\n" +
                             "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n"
                             +
                             "\n" +
                             "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n"
                             +
                             "\n" +
                             "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n"
                             +
                             "\n" +
                             "\n" +
                             "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
    guacNotes.setRecipe(guacRecipe);
    guacRecipe.setNotes(guacNotes);
    guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
    guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));
    guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
    guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
    guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
    guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
    guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
    guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));
    guacRecipe.getCategories().add(americanCategory);
    guacRecipe.getCategories().add(mexicanCategory);
    recipes.add(guacRecipe);
    return recipes;
  }
}
