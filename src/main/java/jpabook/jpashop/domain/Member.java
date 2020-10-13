package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  private String name;

  @Embedded
  private Address address = new Address();

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<>();

  public Member(String name,
                Address address) {
    this.name = name;
    this.address = address;
  }

  public Member(String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

}
