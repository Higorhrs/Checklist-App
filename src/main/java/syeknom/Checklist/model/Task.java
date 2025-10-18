package syeknom.Checklist.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;

import java.time.LocalDate;

@Entity
@Table (name = "tasks")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private  String title;

    @Column(length =  1000)
    private  String description;

    private LocalDate dueDate;

   @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;

   private Integer priority;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id")
    private Category category;

   public Task(){
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
