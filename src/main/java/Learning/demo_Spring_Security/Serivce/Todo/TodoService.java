package Learning.demo_Spring_Security.Serivce.Todo;

import Learning.demo_Spring_Security.Dto.TodoDto;

import java.util.List;

public interface TodoService {




    TodoDto addToTodo(TodoDto todoDto);

    List<TodoDto> getAllTodo();



}
