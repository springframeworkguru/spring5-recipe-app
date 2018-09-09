package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    @Override
    public void saveImageFile(Long recipeId, MultipartFile multipartFile) {
        log.debug("Received image file for recipe: " + recipeId);
        try {
            Recipe recipe = recipeRepository.findById(recipeId)
                    .orElseThrow(() -> new RuntimeException("Recipe with id: " + recipeId + "not found"));
            Byte[] bytes = new Byte[multipartFile.getBytes().length];
            int index = 0;

            for (byte b : multipartFile.getBytes()) {
                bytes[index++] = b;
            }

            recipe.setImage(bytes);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Error during saving image to recipe");
            e.printStackTrace();
        }
    }
}
