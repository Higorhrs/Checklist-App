package syeknom.Checklist.dto;

import org.junit.jupiter.api.Test;
import syeknom.Checklist.model.Category;
import syeknom.Checklist.model.TaskStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DTOsTest {

    @Test
    void testarCategoryCreateDTO() {
        CategoryCreateDTO dto = new CategoryCreateDTO();
        dto.setName("Trabalho");
        dto.setDescription("Descrição");

        assertEquals("Trabalho", dto.getName());
        assertEquals("Descrição", dto.getDescription());
    }

    @Test
    void testarCategoryResponseDTOO() {
        Category category = new Category("Trabalho", "Descrição");
        category.setId(1L);

        CategoryResponseDTO dto = new CategoryResponseDTO(category);

        assertEquals(1L, dto.getId());
        assertEquals("Trabalho", dto.getName());
        assertEquals("Descrição", dto.getDescription());
    }

    @Test
    void testarTaskCreateDTOO() {
        TaskCreateDTO dto = new TaskCreateDTO();
        dto.setTitle("Título");
        dto.setDescription("Descrição");
        dto.setDueDate(LocalDate.now());
        dto.setPriority(1);
        dto.setCategoryID(1L);

        assertEquals("Título", dto.getTitle());
        assertEquals("Descrição", dto.getDescription());
        assertNotNull(dto.getDueDate());
        assertEquals(1, dto.getPriority());
        assertEquals(1L, dto.getCategoryID());
    }
}