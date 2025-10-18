package syeknom.Checklist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import syeknom.Checklist.dto.StatusUpdateDTO;
import syeknom.Checklist.dto.TaskCreateDTO;
import syeknom.Checklist.dto.TaskResponseDTO;
import syeknom.Checklist.model.TaskStatus;
import syeknom.Checklist.service.TaskService;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Validated
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@Valid @RequestBody TaskCreateDTO dto) {
        TaskResponseDTO created = service.create(dto);
        URI location = URI.create("/api/tasks/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public List<TaskResponseDTO> list() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO update(@PathVariable Long id, @Valid @RequestBody TaskCreateDTO dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/{id}/status")
    public TaskResponseDTO patchStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateDTO dto) {
        return service.patchStatus(id, dto.getStatus());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/status/{status}")
    public List<TaskResponseDTO> findByStatus(@PathVariable TaskStatus status) {
        return service.findByStatus(status);
    }
}
