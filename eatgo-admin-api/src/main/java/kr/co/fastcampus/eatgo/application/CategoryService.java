package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Category;
import kr.co.fastcampus.eatgo.domain.CategoryRepository;
import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository=categoryRepository;
    }


    public List<Category> getCategories() {
        List<Category> categories=categoryRepository.findAll();
        return categories;
    }

    public Category addCategory(String name) {
        Category category=Category.builder().name(name).build();
        categoryRepository.save(category);
        return category;
    }
}
