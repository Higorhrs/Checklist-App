package syeknom.Checklist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syeknom.Checklist.dto.TaskCreateDTO;
import syeknom.Checklist.dto.TaskResponseDTO;
import syeknom.Checklist.model.Category;
import syeknom.Checklist.model.Task;
import syeknom.Checklist.model.TaskStatus;
import syeknom.Checklist.repository.CategoryRepository;
import syeknom.Checklist.repository.TaskRepository;
import syeknom.Checklist.service.TaskService;


import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskServiceImpl(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public TaskResponseDTO create(TaskCreateDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryID())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        Task t = new Task();
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setDueDate(dto.getDueDate());
        t.setPriority(dto.getPriority());
        t.setCategory(category);
        t.setStatus(TaskStatus.PENDING);

        Task saved = taskRepository.save(t);
        return toResponse(saved);
    }

    @Override
    public List<TaskResponseDTO> listAll() {
        return taskRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO getById(Long id) {
        return taskRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
    }

    @Override
    public TaskResponseDTO update(Long id, TaskCreateDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
        Category category = categoryRepository.findById(dto.getCategoryID())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());
        task.setPriority(dto.getPriority());
        task.setCategory(category);

        return toResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponseDTO patchStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
        task.setStatus(status);
        return toResponse(taskRepository.save(task));
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Tarefa não encontrada");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponseDTO> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status).stream().map(this::toResponse).collect(Collectors.toList());
    }

    private TaskResponseDTO toResponse(Task t) {
        String catName = t.getCategory() != null ? t.getCategory().getName() : null;
        return new TaskResponseDTO(t.getId(), t.getTitle(), t.getDescription(),
                t.getDueDate(), t.getStatus().name(), t.getPriority(), catName);
    }
}
