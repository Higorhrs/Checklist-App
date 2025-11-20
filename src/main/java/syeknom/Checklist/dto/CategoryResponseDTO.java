package syeknom.Checklist.dto;

//import lombok.Getter;
//import lombok.Setter;
import syeknom.Checklist.model.Category;

//Getter
//@Setter
public class CategoryResponseDTO {

    private Long id; // Inclui o ID que é gerado
    private String name;
    private String description;

    
    public CategoryResponseDTO() {
    }
    
    // Construtor para converter a Entity
    public CategoryResponseDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        // NOTA: Nenhuma referência à lista de tasks!
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}