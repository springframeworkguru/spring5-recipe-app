package guru.springframework.services.image;

import guru.springframework.domains.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile multipartFile) {
        try {
            Recipe recipe = recipeRepository.findById(id).get();

            Byte[] bytes = new Byte[multipartFile.getBytes().length];

            //maybe cloning??
            int i = 0;
            for(byte b: multipartFile.getBytes()){
                bytes[i++] = b;
            }

            recipe.setImage(bytes);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better.
            log.error("IOException occurred while retrieving bytes from the file.");

            e.printStackTrace();
        }
    }
}
