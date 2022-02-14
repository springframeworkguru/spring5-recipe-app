package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Notes;

public interface NotesRepository extends CrudRepository<Notes, Long> {

}
