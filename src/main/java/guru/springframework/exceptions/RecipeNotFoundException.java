package guru.springframework.exceptions;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(Long id)  {
        super(String.format("Could not find recipe with id %d", id));
    }
}
