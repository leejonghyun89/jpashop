package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;

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

  public void changeOrder(Order order) {
    this.order = order;
    order.getOrderItems().add(this);
  }

}
