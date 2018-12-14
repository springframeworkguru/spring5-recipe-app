package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private String base64Image;
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
    
    public String getBase64Image()
    {
    	if(image != null && StringUtils.isEmpty(base64Image)) {
    		base64Image = Base64.encodeBase64String(toPrimitiveByteArray(image));
    	}
    	
    	return base64Image;
    }
    
    private byte[] toPrimitiveByteArray(Byte[] byteArrayObject) {
        byte[] byteArrayPrimitive = new byte[byteArrayObject.length];
        for (int i = 0; i < byteArrayObject.length; i++)
        {
        	byteArrayPrimitive[i] = byteArrayObject[i];
        }
        
        return byteArrayPrimitive;
    }
}
