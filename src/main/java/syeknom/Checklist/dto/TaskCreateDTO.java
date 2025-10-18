package syeknom.Checklist.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;


public class TaskCreateDTO {

    @NotBlank(message = "Titulo obrigatorio")
    @Size(min = 3, max = 1000)
    private String title;

    @Size(max = 1000)
    private String description;

    private LocalDate dueDate;

    @NotNull(message = "Priority obrigatorio")
    @Min(1)
    @Max(5)
    private Integer priority;

    @NotNull(message = "categoryID obrigatorio")
    private Long categoryID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }
}

