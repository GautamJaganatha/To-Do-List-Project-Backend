package Learning.demo_Spring_Security.entity;

import Learning.demo_Spring_Security.Dto.TodoDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private boolean completed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "todo_category_fk"
            )
    )
    private Set<Category> categories = new HashSet<>();

    public TodoDto getDto() {
        TodoDto todoDto = new TodoDto();
        todoDto.setId(getId());
        todoDto.setTitle(getTitle());
        todoDto.setDescription(getDescription());
        todoDto.setCompleted(isCompleted());
        todoDto.setCreatedAt(LocalDateTime.now());
        todoDto.setUpdatedAt(getUpdatedAt());
        if (!categories.isEmpty()) {
            // Get the first category in the set
            Category category = categories.iterator().next();
            todoDto.setCategoryId(category.getId());
            todoDto.setCategoryName(category.getName());
        } else {
            todoDto.setCategoryId(null); // or a default value
            todoDto.setCategoryName(null); // or a default value
        }


        return todoDto;
    }
}
