package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Entity
@Getter
public class Delivery {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "delivery_id")
  private Long id;

  @JsonIgnore
  @OneToOne(mappedBy = "delivery", fetch = LAZY)
  private Order order;

  @Embedded
  private Address address;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status;

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public void setStatus(DeliveryStatus status) {
    this.status = status;
  }

}
