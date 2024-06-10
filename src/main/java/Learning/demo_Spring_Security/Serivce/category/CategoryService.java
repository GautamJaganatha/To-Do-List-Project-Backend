package Learning.demo_Spring_Security.Serivce.category;

import Learning.demo_Spring_Security.Dto.CategoryDto;
import Learning.demo_Spring_Security.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();
}
