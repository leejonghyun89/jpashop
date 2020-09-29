package jpabook.jpashop.service;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderSearch;

import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-21
 */
public interface OrderService {

  Long saveOrder(Long memberId,
                 Long itemId,
                 int count);

  void cancelOrder(Long orderId);

  Order getOrderById(Long orderId);

  List<Order> findOrders(OrderSearch orderSearch);

}
