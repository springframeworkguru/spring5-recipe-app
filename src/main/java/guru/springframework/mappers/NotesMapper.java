package guru.springframework.mappers;

import guru.springframework.domain.Notes;
import guru.springframework.dtos.NotesDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotesMapper {
    Notes notesDtoToNotes(NotesDto notesDto);
    NotesDto notesToNotesDto(Notes notes);
}
