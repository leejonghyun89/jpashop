package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by JongHyun Lee on 2020-09-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

  @Autowired
  EntityManager em;

  @Autowired
  OrderService orderService;

  @Test
  public void 상품_주문() throws Exception {
    // given
    Member member = createMember("이름", "서울시", "동대문구", "123456");
    em.persist(member);

    Item item = createBook("시골 JPA", 10000, 10, "author", "isbn");
    em.persist(item);

    // when
    int orderCount = 2;
    Long orderId = orderService.saveOrder(member.getId(), item.getId(), orderCount);

    // then
    Order order = orderService.getOrderById(orderId);

    assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, order.getStatus());
    assertEquals("주문한 상품 종류 수가 정확해야 한다." , 1, order.getOrderItems().size());
    assertEquals("주문 가격은 상품 * 수량이다.", 10000 * orderCount , order.getTotalPrice());
    assertEquals("주문 수량만큼 재고가 줄어야한다.", 8, item.getStock());
  }

  @Test(expected = NotEnoughStockException.class)
  public void 상품_주문_재고_초과() throws Exception {
    // given
    Member member = createMember("이름", "서울시", "동대문구", "123456");
    em.persist(member);

    Item item = createBook("시골 JPA", 10000, 10, "author", "isbn");
    em.persist(item);

    // when
    int orderCount = 11;
    orderService.saveOrder(member.getId(), item.getId(), orderCount);

    // then
    fail("재고 수량 부족 예외가 발생해야 한다.");
  }

  @Test
  public void 상품_주문_취소() throws Exception {
    // given
    Member member = createMember("이름", "서울시", "동대문구", "123456");
    em.persist(member);

    Item item = createBook("시골 JPA", 10000, 10, "author", "isbn");
    em.persist(item);

    int orderCount = 2;
    Long orderId = orderService.saveOrder(member.getId(), item.getId(), orderCount);

    // when
    orderService.cancelOrder(orderId);

    // then
    Order order = orderService.getOrderById(orderId);

    assertEquals("상품 주문시 상태는 ORDER", OrderStatus.CANCEL, order.getStatus());
    assertEquals("주문을 취소한 만큼 재고가 복구 되어야한다" , 10, item.getStock());
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
                          String isbn) {
    return new Book(null, name, price, stock, author, isbn);
  }

}