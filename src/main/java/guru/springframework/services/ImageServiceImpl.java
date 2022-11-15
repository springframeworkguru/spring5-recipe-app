package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ArrayUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {
            Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

            if (!optionalRecipe.isPresent()){
                throw  new RuntimeException("Recipe not present with id " + recipeId);
            } else {
                Recipe recipe = optionalRecipe.get();
                Byte[] image = boxByteArray(file.getBytes());
                recipe.setImage(image);
                recipeRepository.save(recipe);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private Byte[] boxByteArray(byte[] bytes) {
        Byte[] boxed = new Byte[bytes.length];

        int i = 0;
        for (byte b : bytes) {
            boxed[i++] = b;
        }
        return boxed;
    }
}
