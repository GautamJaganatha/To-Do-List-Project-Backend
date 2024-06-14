package Learning.demo_Spring_Security.Controller;

import Learning.demo_Spring_Security.Dto.TodoDto;
import Learning.demo_Spring_Security.Serivce.Todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
