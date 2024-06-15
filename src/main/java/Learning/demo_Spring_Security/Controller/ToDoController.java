package Learning.demo_Spring_Security.Controller;

import Learning.demo_Spring_Security.Dto.TodoDto;
import Learning.demo_Spring_Security.Serivce.Todo.TodoService;
import Learning.demo_Spring_Security.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ToDoController {
    private final TodoService todoService;




    @PostMapping("addToTodo")
    public ResponseEntity<?> addTodo(@RequestBody TodoDto todoDto){
        TodoDto todoDto1 = todoService.addToTodo(todoDto);
        return new ResponseEntity<>(todoDto1, HttpStatus.CREATED);
    }


    @GetMapping("getAllTodo")
    public ResponseEntity<List<?>> gettoDo(){
        List<TodoDto> todoList = todoService.getAllTodo();
        return ResponseEntity.ok(todoList);
    }
}
