package guru.springframework.bootstrap;


import guru.springframework.model.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.MasureOfRepository;
import guru.springframework.repository.NotesRepository;
import guru.springframework.repository.RecipeRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    CategoryRepository categoryRepo ;
    MasureOfRepository unitOfMRepo;
    RecipeRepository recipeRepo;
    private NotesRepository notesrepo;




    DataLoader(CategoryRepository categoryRepo,
               MasureOfRepository unitOfMRepo,
               RecipeRepository recipeRepo, NotesRepository notesrepo){

            this.categoryRepo = categoryRepo;
            this.unitOfMRepo = unitOfMRepo;
            this.recipeRepo = recipeRepo;
        this.notesrepo = notesrepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        recipeRepo.saveAll(this.loadData());

    }
/*
    2 ripe avocados
    1/4 teaspoon of salt, more to taste
    1 tablespoon fresh lime juice or lemon juice
    2 tablespoons to 1/4 cup of minced red onion or thinly sliced green onion
    1-2 serrano chiles, stems and seeds removed, minced
    2 tablespoons cilantro (leaves and tender stems), finely chopped
    A dash of freshly grated black pepper
    1/2 ripe tomato, seeds and pulp removed, chopped
    Red radishes or jicama, to garnish
    Tortilla chips, to serve
*/
    private Set<Recipe> loadData(){

        Set<Recipe> recipes = new HashSet<Recipe>();
        Recipe guacamoleRecp = new Recipe();
        Set<Ingredient> ingredients = new HashSet<Ingredient>();
        UnitOfMeasure ripeUom= this.unitOfMRepo.findByUom("ripe");
        UnitOfMeasure teaspoonUom= this.unitOfMRepo.findByUom("teaspoon");
        UnitOfMeasure tablespoonUom= this.unitOfMRepo.findByUom("tablespoon");
        UnitOfMeasure  cupUom= this.unitOfMRepo.findByUom("cup");
        UnitOfMeasure dashUom= this.unitOfMRepo.findByUom("dash");

        Category guacomoleCat = this.categoryRepo.findByDescription("Mexican");



        ingredients.add(new Ingredient(new BigDecimal(2), ripeUom,"avocados",guacamoleRecp));

        guacamoleRecp.getIngredients().add(new Ingredient(new BigDecimal(1/4), teaspoonUom,"of salt, more to taste",guacamoleRecp));
        guacamoleRecp.getIngredients().add(new Ingredient(new BigDecimal(1), tablespoonUom,"fresh lime juice or lemon juice",guacamoleRecp));
        guacamoleRecp.getIngredients().add(new Ingredient(new BigDecimal(2), tablespoonUom,"red onion or thinly sliced green onion",guacamoleRecp));
        guacamoleRecp.getIngredients().add(new Ingredient(new BigDecimal(1), dashUom,"serrano chiles, stems and seeds removed, minced",guacamoleRecp));
        guacamoleRecp.getIngredients().add(new Ingredient(new BigDecimal(2), tablespoonUom,"cilantro (leaves and tender stems), finely chopped",guacamoleRecp));
        guacamoleRecp.getIngredients().add(new Ingredient(new BigDecimal(1), dashUom,"of freshly grated black pepper",guacamoleRecp));
        guacamoleRecp.getIngredients().add(new Ingredient(new BigDecimal(1/2), ripeUom,"tomato, seeds and pulp removed, chopped",guacamoleRecp));


       // guacamoleRecp.getCategories().add(guacomoleCat);
        guacamoleRecp.setCookTime(new Integer(10));
        guacamoleRecp.setServings(new Integer(2));
        guacamoleRecp.setPrepTime(10);
        guacamoleRecp.setDescription("How to Make Perfect Guacamole Recipe");

        guacamoleRecp.setDiffculty(Difficulty.EASY);
        Notes note = new Notes();
        note.setRecipeNotes("2 ripe avocados\n" +
                "    1/4 teaspoon of salt, more to taste\n" +
                "    1 tablespoon fresh lime juice or lemon juice\n" +
                "    2 tablespoons to 1/4 cup of minced red onion or thinly sliced green onion\n" +
                "    1-2 serrano chiles, stems and seeds removed, minced\n" +
                "    2 tablespoons cilantro (leaves and tender stems), finely chopped\n" +
                "    A dash of freshly grated black pepper\n" +
                "    1/2 ripe tomato, seeds and pulp removed, chopped\n" +
                "    Red radishes or jicama, to garnish\n" +
                "    Tortilla chips, to serve");

        note.setRecipe(guacamoleRecp);
        //this.notesrepo.save(note);
        guacamoleRecp.setNotes(note);
        guacamoleRecp.setSource("Spring guru");
        guacamoleRecp.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        recipes.add(guacamoleRecp);

        return recipes;


    }


}
