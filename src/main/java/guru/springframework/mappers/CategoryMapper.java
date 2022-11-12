package guru.springframework.mappers;

import guru.springframework.domain.Category;
import guru.springframework.dtos.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category categoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto categoryToCategoryDto(Category category);

    Set<Category> categoryDtosToCategories(Set<CategoryDto> categoryDtos);
    Set<CategoryDto> categoriesToCategoryDtos(Set<Category> categories);
}
