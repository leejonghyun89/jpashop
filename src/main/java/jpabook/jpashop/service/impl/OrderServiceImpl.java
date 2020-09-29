package jpabook.jpashop.service.impl;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-21
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository;

  private final ItemRepository itemRepository;

  private final OrderRepository orderRepository;

  /**
   * 주문 하기
   * @param memberId 회원 ID
   * @param itemId 상품 ID
   * @param count 상품 구매 갯수
   * @return 주문 ID
   */
  @Override
  @Transactional
  public Long saveOrder(Long memberId,
                        Long itemId,
                        int count) {

    // 회원 정보 및 상품 조회
    Member member = memberRepository.findById(memberId);
    Item item = itemRepository.findById(itemId);

    // 배송정보 생성
    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());

    // 주문 상품 생성
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

    // 주문 생성
    Order order = Order.createOrder(member, delivery, orderItem);

    // 주문 저장
    orderRepository.save(order);

    return order.getId();
  }

  /**
   * 주문 취소
   * @param orderId 주문 취소 ID
   */
  @Transactional
  public void cancelOrder(Long orderId) {

    // 주문 정보 조화
    Order order = orderRepository.findById(orderId);

    // 주문 취소
    order.cancelOrder();
  }

  @Override
  public Order getOrderById(Long orderId) {
    return orderRepository.findById(orderId);
  }

  /**
   * 주문 전체 조회
   * @param orderSearch 주문 검색 조건
   * @return 주문 목록
   */
  public List<Order> findOrders(OrderSearch orderSearch) {
    return orderRepository.findAllByString(orderSearch);
  }

}
