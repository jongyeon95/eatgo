package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Category;
import kr.co.fastcampus.eatgo.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        categoryService=new CategoryService(categoryRepository);
    }

    @Test
    public void getCategories(){
        List<Category> MockCategory=new ArrayList<>();
        MockCategory.add(Category.builder().name("Korean Food").build());
        given(categoryRepository.findAll()).willReturn(MockCategory);
        List<Category> categories=categoryService.getCategories();

        Category category=categories.get(0);
        assertThat(category.getName(),is("Korean Food"));
    }

}