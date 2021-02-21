package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeService recipeService;
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeService recipeService, RecipeRepository recipeRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.info("saving image..." + file.getName());

        Recipe recipe = recipeService.findById(recipeId);

        if (recipe == null) {
            log.debug("recipe with id " + recipeId + " not found!");
            return;
        }

        try {
            Byte[] imageBytes = new Byte[file.getBytes().length];
            addBytesFromPrimitive(imageBytes, file.getBytes());

            recipe.setImage(imageBytes);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void addBytesFromPrimitive(Byte[] box, byte[] primitive) {
        Arrays.setAll(box, n -> primitive[n]);
    }
}
