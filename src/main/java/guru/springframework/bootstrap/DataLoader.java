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
        this.loadCategories();
        this.loadUom();
        Set recipes = this.loadData();
        recipeRepo.saveAll(recipes);
        System.out.println("Recipe"+recipes);
    }

    private void loadCategories(){
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepo.save(cat1);

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepo.save(cat2);

        Category cat3 = new Category();
        cat3.setDescription("Mexican");
        categoryRepo.save(cat3);

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepo.save(cat4);
    }

    private void loadUom(){
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setUom("Teaspoon");
        unitOfMRepo.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setUom("Tablespoon");
        unitOfMRepo.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setUom("Cup");
        unitOfMRepo.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setUom("Pinch");
        unitOfMRepo.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setUom("Ounce");
        unitOfMRepo.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setUom("Each");
        unitOfMRepo.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setUom("Pint");
        unitOfMRepo.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setUom("Dash");
        unitOfMRepo.save(uom8);
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
        UnitOfMeasure ripeUom= this.unitOfMRepo.findByUom("Pint").get();
        UnitOfMeasure teaspoonUom= this.unitOfMRepo.findByUom("Teaspoon").get();
        UnitOfMeasure tablespoonUom= this.unitOfMRepo.findByUom("Tablespoon").get();
        UnitOfMeasure cupUom= this.unitOfMRepo.findByUom("Cup").get();
        UnitOfMeasure dashUom= this.unitOfMRepo.findByUom("Dash").get();

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
