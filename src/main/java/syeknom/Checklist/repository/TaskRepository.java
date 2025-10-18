package syeknom.Checklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syeknom.Checklist.model.Task;
import syeknom.Checklist.model.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);
    List<Task> findByCategory_name(String categoryName);

}
