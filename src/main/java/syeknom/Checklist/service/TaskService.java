package syeknom.Checklist.service;

import syeknom.Checklist.dto.TaskCreateDTO;
import syeknom.Checklist.dto.TaskResponseDTO;
import syeknom.Checklist.model.TaskStatus;
import java.util.List;

public interface TaskService {
    TaskResponseDTO create(TaskCreateDTO dto);
    List<TaskResponseDTO> listAll();
    TaskResponseDTO getById(Long id);
    TaskResponseDTO update(Long id, TaskCreateDTO dto);
    TaskResponseDTO patchStatus(Long id, TaskStatus status);
    void delete(Long id);
    List<TaskResponseDTO> findByStatus(TaskStatus status);
}
