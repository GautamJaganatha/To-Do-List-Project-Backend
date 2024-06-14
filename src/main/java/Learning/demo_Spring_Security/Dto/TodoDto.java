package Learning.demo_Spring_Security.Dto;

import Learning.demo_Spring_Security.entity.Category;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TodoDto {
    private Long id;

    private String title;

    private String description;

    private boolean completed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long categoryId;

    private String categoryName;
}
