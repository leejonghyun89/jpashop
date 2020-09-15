package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

  private String city;

  private String street;

  private String zipCode;

  public Address(String city,
                 String street,
                 String zipCode) {
    this.city = city;
    this.street = street;
    this.zipCode = zipCode;
  }

}
