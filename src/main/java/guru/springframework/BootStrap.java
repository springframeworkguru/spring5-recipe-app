package guru.springframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.service.NotesService;
import guru.springframework.service.RecipeService;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private NotesService notesService;
	
	public void initData() {
		Recipe recipe = new Recipe();
		recipe.setDescription("day la con cac");
		recipe.setDirections("abc");
		recipe.setUrl("http://concac");
		
		Recipe recipe1 = new Recipe();
		recipe1.setDescription("day la con cu");
		recipe1.setDirections("bcd");
		recipe1.setUrl("http://concu");
		
		Notes note1 = new Notes();
		note1.setRecipeNotes("concac");
		
		Notes note2 = new Notes();
		note2.setRecipeNotes("concu");
		
		recipe.setNotes(note1);
		note2.setRecipe(recipe1);
		recipeService.save(recipe);
		recipeService.save(recipe1);
		//notesService.save(note1);
		notesService.save(note2);

	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		initData();
	}
}
