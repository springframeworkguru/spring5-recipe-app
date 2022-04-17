package guru.springframework.repositories;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {
	
	 @Autowired
	 UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Before
	public void setUp() {
		
	}

	@Test
	@DirtiesContext
	public void testFindByDescription() {
	
		String description="Teaspoon";
		
		assertTrue(!unitOfMeasureRepository.findByDescription(description).isEmpty());
	}
	
	@Test
	public void testFindByDescriptionCup() {
	
		String description="Cup";
		
		assertTrue(!unitOfMeasureRepository.findByDescription(description).isEmpty());
	}

}
