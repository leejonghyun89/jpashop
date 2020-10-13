package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "delivery_id")
  private Delivery delivery;

  private LocalDateTime orderDate;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  public void setMember(Member member) {
    this.member = member;
    member.getOrders().add(this);
  }

  public void addOrderItem(OrderItem orderItem) {
    this.orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
    delivery.setOrder(this);
  }

  public void setOrderStatus(OrderStatus status) {
    this.status = status;
  }

  public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }

  /**
   * 주문 생성 메서드
   * @param member 주문자
   * @param delivery 배송 정보
   * @param orderItems 주문 상품
   * @return
   */
  public static Order createOrder(Member member,
                                  Delivery delivery,
                                  OrderItem... orderItems) {
    Order order = new Order();

    order.setMember(member);
    order.setDelivery(delivery);
    for (OrderItem orderItem : orderItems) {
      order.addOrderItem(orderItem);
    }
    order.setOrderStatus(OrderStatus.ORDER);
    order.setOrderDate(LocalDateTime.now());

    return order;
  }

  /**
   * 주문 취소 비즈니스 로직
   */
  public void cancelOrder() {
    if (delivery.getStatus() == DeliveryStatus.COMPLITE) {
      throw new IllegalStateException("이미 배송완료된 상품을 취소가 불가능합니다.");
    }

    this.setOrderStatus(OrderStatus.CANCEL);
    for (OrderItem orderItem : orderItems) {
      orderItem.cancelOrderItem();
    }
  }

  /**
   * 전체 주문 가격 조회
   * @return
   */
  public int getTotalPrice() {
    int totalPrice = 0;

    for (OrderItem orderItem : orderItems) {
      totalPrice += orderItem.getOrderPrice() * orderItem.getCount();
    }

    return totalPrice;
  }

}
