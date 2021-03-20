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

        
        // Buscando uma carecterística a partir do repositório


        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("American");
        
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Categoria Not Found");
        }
        Category mexicanCategory = mexicanCategoryOptional.get();
        Set<Category> categoriasDaReceita = new HashSet<Category>();

        categoriasDaReceita.add(mexicanCategory);
        
        // Buscando unidades de medida a partir da base;
        Optional<UnitOfMeasure> pitadaUnitOfMeasure = unitOfMeasureRepository.findByDescription("Pitada");
        if (!pitadaUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected Unidade de Medida Not Found");
        }
        Optional<UnitOfMeasure> unidadeUnitOfMeasure = unitOfMeasureRepository.findByDescription("Unidade");
        if (!unidadeUnitOfMeasure.isPresent()){
            throw new RuntimeException("Expected Unidade de Medida Not Found");
        }
        UnitOfMeasure unidade = unidadeUnitOfMeasure.get();

         // Adiconando os dados da Receita
        Recipe Receita1 = new Recipe();
        Receita1.setCategories(categoriasDaReceita);
        Receita1.setDescription("Perfect Guacamole");
        Receita1.setPrepTime(60);
        Receita1.setCookTime(15);
        Receita1.setDifficulty(Difficulty.HARD);
        Receita1.setDirections("Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.");
        
        Ingredient abacate = new Ingredient( "Abacate", new BigDecimal(2.0) ,unidade);
        Ingredient tomate = new Ingredient( "Tomate", new BigDecimal(2.0) ,unidade);

        
        Set<Ingredient> ingredientesDaReceita = new HashSet<Ingredient>();
        ingredientesDaReceita.add(abacate);
        ingredientesDaReceita.add(tomate);
            
        Receita1.setIngredients(ingredientesDaReceita);

        Notes comentarios = new Notes();
        comentarios.setRecipe(Receita1);
        comentarios.setRecipeNotes("Comentarios");
        Receita1.setNotes(comentarios);

        recipeRepository.save(Receita1);
    }

}
