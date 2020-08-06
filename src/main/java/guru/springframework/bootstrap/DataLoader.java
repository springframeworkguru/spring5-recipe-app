package guru.springframework.bootstrap;


import guru.springframework.model.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.repository.NotesRepository;
import guru.springframework.repository.RecipeRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    CategoryRepository categoryRepo ;
    UnitOfMeasureRepository unitOfMRepo;
    RecipeRepository recipeRepo;
    private NotesRepository notesrepo;




    DataLoader(CategoryRepository categoryRepo,
               UnitOfMeasureRepository unitOfMRepo,
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

        UnitOfMeasure ripeUom= this.unitOfMRepo.findByUom("ripe");
        UnitOfMeasure teaspoonUom= this.unitOfMRepo.findByUom("teaspoon");
        UnitOfMeasure tablespoonUom= this.unitOfMRepo.findByUom("tablespoon");
        UnitOfMeasure  cupUom= this.unitOfMRepo.findByUom("cup");
        UnitOfMeasure dashUom= this.unitOfMRepo.findByUom("dash");

        Optional<Category> guacomoleCat = this.categoryRepo.findByDescription("Mexican");



        guacamoleRecp.addIngredient(new Ingredient(new BigDecimal(2), ripeUom,"avocados"));

        guacamoleRecp.addIngredient(new Ingredient(new BigDecimal(1/4), teaspoonUom,"of salt, more to taste"));
        guacamoleRecp.addIngredient(new Ingredient(new BigDecimal(1), tablespoonUom,"fresh lime juice or lemon juice"));
        guacamoleRecp.addIngredient(new Ingredient(new BigDecimal(2), tablespoonUom,"red onion or thinly sliced green onion"));
        guacamoleRecp.addIngredient(new Ingredient(new BigDecimal(1), dashUom,"serrano chiles, stems and seeds removed, minced"));
        guacamoleRecp.addIngredient(new Ingredient(new BigDecimal(2), tablespoonUom,"cilantro (leaves and tender stems), finely chopped"));
        guacamoleRecp.addIngredient(new Ingredient(new BigDecimal(1), dashUom,"of freshly grated black pepper"));
        guacamoleRecp.addIngredient(new Ingredient(new BigDecimal(1/2), ripeUom,"tomato, seeds and pulp removed, chopped"));


        guacamoleRecp.getCategories().add(guacomoleCat.get());
        guacamoleRecp.setCookTime(new Integer(10));
        guacamoleRecp.setServings(new Integer(2));
        guacamoleRecp.setPrepTime(10);
        guacamoleRecp.setDescription("How to Make Perfect Guacamole Recipe");

        guacamoleRecp.setDiffculty(Difficulty.EASY);
        Notes note = new Notes();
        note.setRecipeNotes("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
                "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
                "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim." +
                " Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. " +
                "Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. " +
                "Aenean vulputate eleifend tellus. " +
                "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. " +
                "Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. " +
                "Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, " +
                "sit amet adipiscing sem neque sed ipsum.");



        guacamoleRecp.setNotes(note);
        guacamoleRecp.setSource("Spring guru");
        guacamoleRecp.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecp.setDiretions("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum.");

        recipes.add(guacamoleRecp);

        return recipes;


    }


}
