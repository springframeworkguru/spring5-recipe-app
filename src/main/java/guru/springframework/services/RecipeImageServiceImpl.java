package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
@Service
public class RecipeImageServiceImpl implements RecipeImageService {
    private final RecipeRepository recipeRepository;

    public RecipeImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public boolean save(Long recipeId, MultipartFile image) throws IOException {
        final Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            final byte[] bytes = image.getBytes();
            final Byte[] wrappedByteArray = IntStream.range(0, bytes.length).mapToObj(i -> bytes[i]).toArray(Byte[]::new);

            final Recipe recipe = optRecipe.get();
            recipe.setImage(wrappedByteArray);
            recipeRepository.save(recipe);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Byte[] findById(Long recipeId) {
        return recipeRepository.findById(recipeId).map(Recipe::getImage).orElse(null);
    }

}
