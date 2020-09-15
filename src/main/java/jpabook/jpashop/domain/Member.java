package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Entity
@Getter
public class Member {

  @Id @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  private String name;

  @Embedded
  private Address address = new Address();

  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<>();

  public void changeName(String name) {
    this.name = name;
  }

}
