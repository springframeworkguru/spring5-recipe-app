package guru.springframework.controllers;

import guru.springframework.dtos.RecipeDto;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {
    private final ImageService imageService;

    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }


    @GetMapping("/recipe/{recipeId}/image")
    public String getImageUploadForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeDtoById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{recipeId}/image")
    public String storeRecipeImage(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile image) {
        imageService.saveImageFile(Long.valueOf(recipeId), image);
        return "redirect:/recipe/" + recipeId + "/show";
    }

    @GetMapping("/recipe/{recipeId}/recipeimage")
    public void loadImage(@PathVariable String recipeId, HttpServletResponse response) throws IOException {
        log.debug("Enter load image");
        RecipeDto recipeDto = recipeService.getRecipeDtoById(Long.valueOf(recipeId));

        byte[] unboxedImage = new byte[recipeDto.getImage().length];
        int i=0;
        for (Byte b : recipeDto.getImage()){
            unboxedImage[i++] = b;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(unboxedImage);
        IOUtils.copy(is, response.getOutputStream());
    }
}
