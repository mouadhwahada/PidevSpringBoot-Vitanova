package tn.spring.pispring.ServiceIMP.admin.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Category;
import tn.spring.pispring.dto.CategoryDto;
import tn.spring.pispring.repo.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public Category createcategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

    return  categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
