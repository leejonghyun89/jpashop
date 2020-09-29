package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-21
 */
@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final EntityManager em;

  public void save(Order order) {
    em.persist(order);
  }

  public Order findById(Long id) {
    return em.find(Order.class, id);
  }

  /**
   * 검색 조건 값을 동적으로 쿼리 만드는 방법 (실무에선 사용하지 않는다. -> 유지보수가 어렵다)
   * @param orderSearch
   * @return
   */
  public List<Order> findAllByString(OrderSearch orderSearch){
   String jpql = "select o from Order o join o.member m";

   boolean isFirstCondition = true;

   // 주문 상태 검색
   if (!ObjectUtils.isEmpty(orderSearch.getStatus())) {
     if (isFirstCondition) {
       jpql += " where";
       isFirstCondition = false;
     } else {
       jpql += " and";
     }
     jpql += " o.status = :status";
   }

   // 회원 이름 검색
   if (!StringUtils.isEmpty(orderSearch.getMemberName())) {
     if (isFirstCondition) {
       jpql += " where";
       isFirstCondition = false;
     } else {
       jpql += "and ";
     }
     jpql += " m.name like :name";
   }

    TypedQuery<Order> query = em.createQuery(jpql, Order.class)
        .setMaxResults(1000);

    if (!ObjectUtils.isEmpty(orderSearch.getStatus())) {
      query = query.setParameter("status", orderSearch.getStatus());
    }

    if (!StringUtils.isEmpty(orderSearch.getMemberName())) {
      query = query.setParameter("name" , orderSearch.getMemberName());
    }

    return query.getResultList();
  }

  /**
   * JPA Criteria (실무에선 사용하지 않는다. -> 유지보수가 어렵다)
   * @param orderSearch
   * @return
   */
  public List<Order> findAllByCriteria(OrderSearch orderSearch) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Order> cq = cb.createQuery(Order.class);
    Root<Order> order = cq.from(Order.class);
    Join<Object, Object> member = order.join("member", JoinType.INNER);

    List<Predicate> criteria = new ArrayList<>();

    // 주문 상태 검색
    if (!ObjectUtils.isEmpty(orderSearch.getStatus())) {
      Predicate status = cb.equal(order.get("status"), orderSearch.getStatus());
      criteria.add(status);
    }

    // 회원 이름 검색
    if (!StringUtils.isEmpty(orderSearch.getMemberName())) {
      Predicate name = cb.equal(member.get("name"), "%" + orderSearch.getMemberName() + "%");
      criteria.add(name);
    }

    cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
    TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);

    return query.getResultList();
  }

}
