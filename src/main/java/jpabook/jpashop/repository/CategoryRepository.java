package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by JongHyun Lee on 2020-09-15
 */
@Repository
@RequiredArgsConstructor
public class CategoryRepository {

  @PersistenceContext
  private EntityManager em;

  public Long save(Category category) {
    em.persist(category);
    return category.getId();
  }

  public Category findById(Long id) {
    return em.find(Category.class, id);
  }

}
