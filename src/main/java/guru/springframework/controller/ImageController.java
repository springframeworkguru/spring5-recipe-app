package guru.springframework.controller;

import guru.springframework.command.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
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

    // send the image upload form
    @GetMapping("/recipe/{id}/image")
    public String showUploadForm(Model model, @PathVariable String id){

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe", recipeCommand);

        return "recipe/imageuploadform";
    }

    // handle the post of an image
    @PostMapping("/recipe/{id}/image")
    public String imagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("/recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {

        // get the recipe and the image
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        // copy the image from Recipe to byteArray
        if(recipeCommand.getImage()!=null){
            byte[] byteArray = new byte[recipeCommand.getImage().length];

            int i=0;

            for(Byte wrappedByte : recipeCommand.getImage()){
                byteArray[i++] = wrappedByte;
            }

            // create the response
            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            // convert from inputStream to an outputStream
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }

}
