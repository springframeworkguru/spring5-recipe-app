package guru.springframework.service;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Notes;

public interface NotesService extends JpaRepository<Notes, Long>{

}
