package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.OrderSimpleQueryDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JongHyun Lee on 2020-10-13
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleController {

  private final OrderRepository orderRepository;

  /**
   * 해당 API는 Hibernate5Module Bean 등록 했기 때문에 모든 Entity 노출이 되기 때문에 사용하면 안됨.
   * @return
   */
  @GetMapping("/api/v1/simple-orders")
  public List<Order> getOrdersV1() {
    List<Order> orders = orderRepository.findAllByString(new OrderSearch());
    orders.forEach(order -> {
      order.getMember().getName(); // Lazy 강제 초기화;
      order.getDelivery().getAddress(); // Lazy 강제 초기화
    });
    return orders;
  }

  /**
   * 해당 API는 1 + N + N 쿼리가 수행되기 떄문에 성능이 좋지 않음.
   * @return
   */
  @GetMapping("/api/v2/simple-orders")
  public List<SimpleOrderDto> getOrdersV2() {
    return orderRepository.findAllByString(new OrderSearch())
      .stream().map(SimpleOrderDto::new).collect(Collectors.toList());
  }

  /**
   * 해당 API는 fetch join 방법으로 사용 (V2 해결 하기 위해 가장 많이 쓰는 방법)
   * @return
   */
  @GetMapping("/api/v3/simple-orders")
  public List<SimpleOrderDto> getOrdersV3() {
    return orderRepository.findAllWithMemberAndDelivery()
      .stream().map(SimpleOrderDto::new).collect(Collectors.toList());
  }

  /**
   * JPA -> Dto 변경 방법 (재사용이 떨어짐)
   * @return
   */
  @GetMapping("/api/v4/simple-orders")
  public List<OrderSimpleQueryDto> getOrdersV4() {
    return orderRepository.findOrdersDto();
  }

  @Getter @Setter
  public static class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus stats;
    private Address address;

    public SimpleOrderDto(Order order) {
      this.orderId = order.getId();
      this.name = order.getMember().getName(); // LAZY 초기화
      this.orderDate = order.getOrderDate();
      this.stats = order.getStatus();
      this.address = order.getDelivery().getAddress(); // LAZY 초기화
    }
  }

}
