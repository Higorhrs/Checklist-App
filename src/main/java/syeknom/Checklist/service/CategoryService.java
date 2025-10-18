package syeknom.Checklist.service;

import syeknom.Checklist.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    Optional<Category> findById(Long id);

    void deleteById(Long id);
}