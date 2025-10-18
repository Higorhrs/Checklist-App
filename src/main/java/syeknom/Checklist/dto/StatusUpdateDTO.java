package syeknom.Checklist.dto;

import jakarta.validation.constraints.NotNull;
import syeknom.Checklist.model.TaskStatus;

public class StatusUpdateDTO {

    @NotNull
    private TaskStatus status;

    public TaskStatus getStatus() {return status; }
    public void setStatus(TaskStatus status) {this.status = status;}

}
