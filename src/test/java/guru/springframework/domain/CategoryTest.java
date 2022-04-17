package guru.springframework.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {
	
	private Category category;
	
	@Before
	public void setUp() {
		category=new Category();
	}

	@Test
	public void testGetId() {
		long expectedId=12334L;
		category.setId(12334L);
		assertEquals(expectedId, category.getId().longValue());
	}
	
	@Test
	public void testGetDescription() {
		String expectedDescription="American";
		category.setDescription(expectedDescription);
		assertEquals(expectedDescription, category.getDescription());
	}
	
}
