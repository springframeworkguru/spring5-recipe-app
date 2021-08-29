package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.image.ImageService;
import guru.springframework.services.recipe.RecipeService;
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
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageupload";
    }

    @PostMapping("/recipe/{id}/image")
    public String postImageUploadForm(@PathVariable String id, @RequestParam("imagefile")MultipartFile multipartFile){
        imageService.saveImageFile(Long.valueOf(id), multipartFile);

        return "redirect:/recipe/"+id+"/show";
    }

    @GetMapping("/recipe/{id}/recipeimage")
    public void renderImageFromDb(@PathVariable String id,
                                    HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

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
