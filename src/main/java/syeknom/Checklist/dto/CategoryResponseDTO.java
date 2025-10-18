package syeknom.Checklist.dto;

import lombok.Getter;
import lombok.Setter;
import syeknom.Checklist.model.Category;

@Getter
@Setter
public class CategoryResponseDTO {

    private Long id; // Inclui o ID que é gerado
    private String name;
    private String description;

    // Construtor para converter a Entity
    public CategoryResponseDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        // NOTA: Nenhuma referência à lista de tasks!
    }
}