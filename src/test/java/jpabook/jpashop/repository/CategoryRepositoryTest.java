package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by JongHyun Lee on 2020-09-15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional
  @Test
  public void createCategory() throws Exception {

    // given
    Category category = new Category("카테고리1번");
    Long categoryId = categoryRepository.save(category);

    // when
    Category findCategory = categoryRepository.findById(categoryId);
    System.out.println("findCategory = " + findCategory.getId());

    // given
    Category child = new Category("카테고리1-1번");
    child.changeCategoriesParent(category);
    Long childCategoryId = categoryRepository.save(child);

    // when
    Category findChildCategory = categoryRepository.findById(childCategoryId);
    System.out.println("findChildCategory = " + findChildCategory);
  }

}