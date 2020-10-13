package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * Created by JongHyun Lee on 2020-10-06
 */
@Component
@RequiredArgsConstructor
public class initDb {

  private final InitService initService;

  @PostConstruct
  public void init() {
    initService.dbInit1();
    initService.dbInit2();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService {

    private final EntityManager em;

    public void dbInit1() {
      Member member = createMember("userA", "서울", "1", "1111");
      em.persist(member);

      Book book1 = createBook("JPA1 BOOK", 10000, 100, "1", "1");
      em.persist(book1);

      Book book2 = createBook("JPA2 BOOK", 20000, 100, "1", "1");
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

      Order order = createOrder(member, orderItem1, orderItem2);
      em.persist(order);
    }

    public void dbInit2() {
      Member member = createMember("userB", "부산", "2", "2222");
      em.persist(member);

      Book book1 = createBook("SPRING1 BOOK", 20000, 100, "1", "1");
      em.persist(book1);

      Book book2 = createBook("SPRING2 BOOK", 40000, 100, "1", "1");
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

      Order order = createOrder(member, orderItem1, orderItem2);
      em.persist(order);
    }

    private Member createMember(String name,
                                String city,
                                String street,
                                String zipCode) {
      return new Member(name, new Address(city, street, zipCode));
    }

    private Book createBook(String name,
                            int price,
                            int stock,
                            String author,
                            String isBn) {
      return new Book(null, name, price, stock, author, isBn);
    }

    private Order createOrder(Member member,
                              OrderItem orderItem1,
                              OrderItem orderItem2) {
      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());
      return Order.createOrder(member, delivery, orderItem1, orderItem2);
    }
  }

}

