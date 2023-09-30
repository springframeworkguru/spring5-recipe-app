package guru.springframework.command;

import guru.springframework.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand implements Comparable<RecipeCommand>{
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new TreeSet<>();
    private NotesCommand notes;
    private Difficulty difficlulty;
    private Set<CategoryCommand> categories = new HashSet<>();

    @Override
    public int compareTo(RecipeCommand recipeCommand) {
        if( this.getId() < recipeCommand.getId() )
            return -1;
        else
            return 1;
    }
}
