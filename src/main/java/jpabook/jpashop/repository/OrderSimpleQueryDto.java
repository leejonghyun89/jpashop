package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by JongHyun Lee on 2020-10-13
 */
@Getter @Setter
public class OrderSimpleQueryDto {

  private Long orderId;
  private String name;
  private LocalDateTime orderDate;
  private OrderStatus stats;
  private Address address;

  public OrderSimpleQueryDto(Long orderId,
                             String name,
                             LocalDateTime orderDate,
                             OrderStatus stats,
                             Address address) {
    this.orderId = orderId;
    this.name = name;
    this.orderDate = orderDate;
    this.stats = stats;
    this.address = address;
  }

}
