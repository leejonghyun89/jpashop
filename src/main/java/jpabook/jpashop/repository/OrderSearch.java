package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by JongHyun Lee on 2020-09-27
 */
@Getter @Setter
public class OrderSearch {

  private String memberName;
  private OrderStatus status;

}
