package tn.spring.pispring.Controller.admin;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Category;
import tn.spring.pispring.ServiceIMP.admin.category.CategoryService;
import tn.spring.pispring.dto.CategoryDto;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){

        Category category = categoryService.createcategory(categoryDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(){

        return  ResponseEntity.ok(categoryService.getAllCategories());
    }
}
