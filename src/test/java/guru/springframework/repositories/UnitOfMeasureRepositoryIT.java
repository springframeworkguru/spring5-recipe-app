package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT{

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before public void setUp() throws Exception{
	}

	@Test
	public void findByDescription(){
		String testDescription = "Teaspoon";
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription( testDescription );
		assertEquals( testDescription, uomOptional.get().getDescription() );
	}

	@Test public void findByDescriptionCup(){
		String testDescription = "Cup";
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription( testDescription );
		assertEquals( testDescription, uomOptional.get().getDescription() );
	}
}