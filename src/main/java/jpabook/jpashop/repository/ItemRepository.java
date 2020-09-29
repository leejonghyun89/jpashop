package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-15
 */
@Repository
@RequiredArgsConstructor
public class ItemRepository {

  private final EntityManager em;

  // TODO : merge(병합)은 절대 사용하지 말고 변경 감지를 사용해야 한다. (null 들어갈 오류 발생 방지)
  public void save(Item item) {
    if (ObjectUtils.isEmpty(item.getId())) {
      em.persist(item);
    } else {
      em.merge(item);
    }
  }

  public Item findById(Long id) {
    return em.find(Item.class, id);
  }

  public List<Item> findAll() {
    return em.createQuery("select i from Item i", Item.class)
      .getResultList();
  }

}
