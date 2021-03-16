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

        
        
        log.info("Buscando uma carecterística a partir do repositório");

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("American");
        
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Categoria Not Found");
        }
        Category mexicanCategory = mexicanCategoryOptional.get();
        Set<Category> categoriasDaReceita = new HashSet<>();

        categoriasDaReceita.add(mexicanCategory);
        

        Optional<UnitOfMeasure> pitadaUnitOfMeasure = unitOfMeasureRepository.findByDescription("Pitada");
        if (!pitadaUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected Unidade de Medida Not Found");
        }
        Optional<UnitOfMeasure> unidadeUnitOfMeasure = unitOfMeasureRepository.findByDescription("Unidade");
        if (!unidadeUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected Unidade de Medida Not Found");
        }
        UnitOfMeasure unidade = unidadeUnitOfMeasure.get();

        log.info("Adiconando os dados da Receita");
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
        comentarios.setRecipeNotes("Comentarios");
        receita1.setNotes(comentarios);

        recipeRepository.save(receita1);
    }

}
