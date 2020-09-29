package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Entity
@Table(name ="order_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

  @Id @GeneratedValue
  @Column(name = "order_item_id")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  private int orderPrice;

  private int count;



  /**
   * 주문 아이템 생성 메서드
   * @param item 상품 정보
   * @param orderPrice 상품 가격
   * @param count 상품 구매 갯수
   * @return 상품 아이템 정보
   */
  public static OrderItem createOrderItem(Item item,
                                          int orderPrice,
                                          int count) {

      OrderItem orderItem = new OrderItem();
      orderItem.setItem(item);
      orderItem.setOrderPrice(orderPrice);
      orderItem.setCount(count);
      item.removeStock(count);

      return orderItem;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public void setOrderPrice(int orderPrice) {
    this.orderPrice = orderPrice;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void cancelOrderItem() {
    getItem().addStock(count);
  }

  /**
   * 상품 구매 가격 조회
   * @return
   */
  public int getOrderPrice() {
    return orderPrice * count;
  }

}
