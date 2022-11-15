package guru.springframework.dtos;

import guru.springframework.domain.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDto {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientDto> ingredients  = new HashSet<>();
    private Set<CategoryDto> categories = new HashSet<>();
    private Difficulty difficulty;
    private NotesDto notes;
    private Byte[] image;
}
