package Learning.demo_Spring_Security.Serivce.Todo;

import Learning.demo_Spring_Security.Dto.TodoDto;
import Learning.demo_Spring_Security.entity.Category;
import Learning.demo_Spring_Security.entity.Todo;
import Learning.demo_Spring_Security.repo.CategoryRepository;
import Learning.demo_Spring_Security.repo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    private final CategoryRepository categoryRepository;

    public TodoDto addToTodo(TodoDto todoDto){
        Todo todo = new Todo();
         todo.setTitle(todoDto.getTitle());
         todo.setDescription(todoDto.getDescription());
         todo.setCompleted(todoDto.isCompleted());
         todo.setCreatedAt(LocalDateTime.now());
         todo.setUpdatedAt(todoDto.getUpdatedAt());

        Optional<Category> optionalCategory = categoryRepository.findById(todoDto.getCategoryId());

// Create a Set<Category>
        Set<Category> categories = new HashSet<>();

// If the category is present, add it to the Set
        optionalCategory.ifPresent(categories::add);

// Set the categories on the todo object
        todo.setCategories(categories);




        return todoRepository.save(todo).getDto();
    }


    public List<TodoDto> getAllTodo(){
        List<Todo> todoList = todoRepository.findAll();

        return todoList.stream().map(Todo::getDto).collect(Collectors.toList());
    }


}
