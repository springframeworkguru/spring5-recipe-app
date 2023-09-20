package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.service.ImageService;
import guru.springframework.service.RecipeService;
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

/**
 * Created by jt on 7/3/17.
 */
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/show/"+id;
    }
    @GetMapping("recipe/{recipeId}/recipeImage")
    public void loadImageFromDB(@PathVariable Long recipeId, HttpServletResponse response) throws IOException {
        RecipeCommand commandById = recipeService.findCommandById(recipeId);
        if (commandById.getImage()!=null){
            byte[] bytes = new byte[commandById.getImage().length];
            int i = 0;
            for (byte c : commandById.getImage()){
                bytes[i++]= c;
            }
            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(bytes);
            IOUtils.copy(inputStream,response.getOutputStream());
        }

    }
}