package Learning.demo_Spring_Security.Controller;

import Learning.demo_Spring_Security.Dto.CategoryDto;
import Learning.demo_Spring_Security.Serivce.Todo.TodoService;
import Learning.demo_Spring_Security.Serivce.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("AddCategory")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.addCategory(categoryDto);

        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @GetMapping("GetCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();

        return ResponseEntity.ok(categoryDtos);
    }
}
