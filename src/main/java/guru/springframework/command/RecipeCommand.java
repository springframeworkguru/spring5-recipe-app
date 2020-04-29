package guru.springframework.command;

import guru.springframework.models.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand extends BaseEntity{
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Set<CategoryCommand> categories;
    private Set<IngredientCommand> ingredients;
    private byte[] image;
    private NotesCommand notes;
}
