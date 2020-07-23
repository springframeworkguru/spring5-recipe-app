package guru.springframework.repository;


import guru.springframework.model.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes,Long> {

}
