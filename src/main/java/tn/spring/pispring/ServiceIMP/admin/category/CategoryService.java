package tn.spring.pispring.ServiceIMP.admin.category;

import tn.spring.pispring.Entities.Category;
import tn.spring.pispring.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    Category createcategory(CategoryDto categoryDto);

    List<Category> getAllCategories();
}
