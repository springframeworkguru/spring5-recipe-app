package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public void run(String... args) throws Exception {

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = dashUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        log.info("Buscando uma carecterística a partir do repositório");

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Categoria Not Found");
        }
        Category mexicanCategory = mexicanCategoryOptional.get();

        Optional<Category> AmericanCategoryOptional = categoryRepository.findByDescription("American");

        if (!AmericanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Categoria Not Found");
        }
        Category AmericanCategory = AmericanCategoryOptional.get();

        Optional<Category> ffCategoryOptional = categoryRepository.findByDescription("Fast Food");

        if (!ffCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Categoria Not Found");
        }
        Category ffCategory = ffCategoryOptional.get();


        Set<Category> categoriasDaReceita = new HashSet<>();

        categoriasDaReceita.add(mexicanCategory);
        categoriasDaReceita.add(AmericanCategory);
        categoriasDaReceita.add(ffCategory);


        Optional<UnitOfMeasure> pitadaUnitOfMeasure = unitOfMeasureRepository.findByDescription("Pitada");
        if (!pitadaUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected Unidade de Medida Not Found");
        }
        Optional<UnitOfMeasure> unidadeUnitOfMeasure = unitOfMeasureRepository.findByDescription("Unidade");
        if (!unidadeUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected Unidade de Medida Not Found");
        }
        UnitOfMeasure unidade = unidadeUnitOfMeasure.get();

        log.info("Adiconando os dados da Receita1");
        Recipe receita1 = new Recipe();
        receita1.setCategories(categoriasDaReceita);
        receita1.setDescription("Perfect Guacamole");
        receita1.setPrepTime(60);
        receita1.setCookTime(15);
        receita1.setDifficulty(Difficulty.HARD);
        receita1.setDirections("Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.");

        
        Ingredient abacate = new Ingredient( "Abacate", BigDecimal.valueOf(2.0) ,unidade);
        Ingredient tomate = new Ingredient( "Tomate", BigDecimal.valueOf(2.0) ,unidade);

        
        Set<Ingredient> ingredientesDaReceita = new HashSet<>();
        ingredientesDaReceita.add(abacate);
        ingredientesDaReceita.add(tomate);
            
        receita1.setIngredients(ingredientesDaReceita);

        Notes comentarios = new Notes();
        comentarios.setRecipe(receita1);
        comentarios.setRecipeNotes("Comentarios 1");
        receita1.setNotes(comentarios);
        recipeRepository.save(receita1);


        log.info("Adiconando os dados da Receita2");
        Recipe receita2 = new Recipe();
        receita2.setCategories(categoriasDaReceita);
        receita2.setDescription("Perfect SucuDuva");
        receita2.setPrepTime(10);
        receita2.setCookTime(00);
        receita2.setDifficulty(Difficulty.EASY);
        receita2.setDirections("Just Smash all");


        Ingredient uva = new Ingredient( "Uvas", BigDecimal.valueOf(2.0) ,unidade);
        Ingredient acucar = new Ingredient( "Açúcar", BigDecimal.valueOf(4.0) ,unidade);
        Ingredient agua = new Ingredient( "Agua", BigDecimal.valueOf(1.0) ,unidade);

         Set<Ingredient> ingredientesDaReceita2 = new HashSet<>();
        ingredientesDaReceita.add(uva);
        ingredientesDaReceita.add(acucar);
        ingredientesDaReceita.add(agua);
        
        receita2.setIngredients(ingredientesDaReceita2);

        Notes comentariosR2 = new Notes();
        comentariosR2.setRecipe(receita2);
        comentariosR2.setRecipeNotes("Comentarios 2");
        receita2.setNotes(comentariosR2);

        recipeRepository.save(receita2);

        log.info("Adiconando os dados da Receita3");
        Recipe receita3 = new Recipe();
        receita3.setCategories(categoriasDaReceita);
        receita3.setDescription("Perfect Agua Gelada");
        receita3.setPrepTime(1);
        receita3.setCookTime(5);
        receita3.setDifficulty(Difficulty.MODERATE);
        receita3.setDirections("Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.");


        Ingredient gelo = new Ingredient( "Gelo", BigDecimal.valueOf(2.0) ,unidade);


        Set<Ingredient> ingredientesDaReceita3 = new HashSet<>();
        ingredientesDaReceita.add(agua);
        ingredientesDaReceita.add(gelo);

        receita3.setIngredients(ingredientesDaReceita3);

        Notes comentariosR3 = new Notes();
        comentariosR3.setRecipe(receita1);
        comentariosR3.setRecipeNotes("Comentarios 3");
        receita3.setNotes(comentariosR3);
        recipeRepository.save(receita3);
    }

}
