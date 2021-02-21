package guru.springframework.controllers;

import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/recipe/{recipeId}/image")
    public String getUploadImageForm(@PathVariable("recipeId") String recipeId, Model model) {

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/image_upload_form";
    }

    @PostMapping("/recipe/{recipeId}/image")
    public String uploadImage(@PathVariable("recipeId") Long recipeId, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(recipeId, file);

        return "redirect:/recipe/" + recipeId + "/show";
    }
}
