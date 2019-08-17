package guru.springframework.domain;

import javax.crypto.spec.PSource;

/**
 * Created by ccabo 8/17/19
 */
public class Recipe {
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String Source;
    private String url;
    private String directions;

    //todo add
    //private Difficulty difficulty;
    private Byte[] image;
}
