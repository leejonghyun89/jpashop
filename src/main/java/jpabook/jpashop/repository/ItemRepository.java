package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by JongHyun Lee on 2020-09-15
 */
@Repository
@RequiredArgsConstructor
public class ItemRepository {

  @PersistenceContext
  private EntityManager em;

  public Long save(Item item) {
    em.persist(item);
    return item.getId();
  }

  public Item findById(Long id) {
    return em.find(Item.class, id);
  }

}
