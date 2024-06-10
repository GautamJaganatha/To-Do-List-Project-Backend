package Learning.demo_Spring_Security.Serivce.category;

import Learning.demo_Spring_Security.Dto.CategoryDto;
import Learning.demo_Spring_Security.entity.Category;
import Learning.demo_Spring_Security.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryDto addCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());

        return categoryRepository.save(category).getDto();
    }

    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(Category::getDto).collect(Collectors.toList());
    }
}
