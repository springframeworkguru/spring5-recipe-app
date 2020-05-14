package guru.springframework.controllers;

import guru.springframework.exceptions.RecipeNotFoundException;
import guru.springframework.services.RecipeImageService;
import guru.springframework.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/recipe/{recipeId}")
public class RecipeImageController {
    private final RecipeService recipeService;
    private final RecipeImageService recipeImageService;

    public RecipeImageController(RecipeService recipeService, RecipeImageService recipeImageService) {
        this.recipeService = recipeService;
        this.recipeImageService = recipeImageService;
    }

    @GetMapping("/image")
    public String showUploadForm(@PathVariable Long recipeId, Model model) {
        if (!recipeService.recipeExists(recipeId)) {
            throw new RecipeNotFoundException(recipeId);
        }
        model.addAttribute("recipeId", recipeId);
        return "recipe/imageuploadform";
    }

    @PostMapping("/image")
    public String postImage(@PathVariable Long recipeId, @RequestParam MultipartFile imagefile) throws IOException {
        final boolean success = recipeImageService.save(recipeId, imagefile);
        if (!success) {
            throw new PersistenceException("Could not persist image");
        }

        return String.format("redirect:/recipe/%d/show", recipeId);
    }

    @GetMapping("/recipeimage")
    public void showRecipeImage(@PathVariable Long recipeId, HttpServletResponse response) throws IOException {
        final Byte[] imageBytes = recipeImageService.findById(recipeId);
        if (imageBytes == null) {
            return;
        }

        byte[] primitiveBytes = new byte[imageBytes.length];
        for (int i = 0; i < imageBytes.length; i++) {
            primitiveBytes[i] = imageBytes[i];
        }

        response.setContentType("image/jpeg");
        final ByteArrayInputStream is = new ByteArrayInputStream(primitiveBytes);
        IOUtils.copy(is, response.getOutputStream());
    }
}
