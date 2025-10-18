package syeknom.Checklist.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateDTO {


    private String name;

    private String description;

    public CategoryCreateDTO() {}
}