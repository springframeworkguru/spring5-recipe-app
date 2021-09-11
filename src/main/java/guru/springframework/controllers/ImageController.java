package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.image.ImageService;
import guru.springframework.services.recipe.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {
    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/recipe/{id}/image")
    public String getImageUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/imageupload";
    }

    @PostMapping("/recipe/{id}/image")
    public String postImageUploadForm(@PathVariable String id, @RequestParam("imagefile")MultipartFile multipartFile){
        imageService.saveImageFile(id, multipartFile);

        return "redirect:/recipe/"+id+"/show";
    }

    @GetMapping("/recipe/{id}/recipeimage")
    public void renderImageFromDb(@PathVariable String id,
                                    HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(id);

        if (recipeCommand.getImage() != null){
            byte[] byteArr = new byte[recipeCommand.getImage().length];

            int i = 0;
            for (Byte wrappedByte: recipeCommand.getImage()) {
                byteArr[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArr);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
