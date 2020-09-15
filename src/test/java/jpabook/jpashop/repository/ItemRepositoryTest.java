package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
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
public class ItemRepositoryTest {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional
  @Test
  public void createItem() throws Exception {

    // given
    Category category = new Category("카테고리1번");
    Long categoryId = categoryRepository.save(category);

    // when
    Category findCategory = categoryRepository.findById(categoryId);

    // given
    Book book = new Book("북네임", 100, 100, "작성자", "??");
    book.changeCategory(category);
    Long bookId = itemRepository.save(book);

    // when
    Item findItem = itemRepository.findById(bookId);
    System.out.println("findItem = " + findItem);
  }

}