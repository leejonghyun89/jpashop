package jpabook.jpashop.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Entity
@DiscriminatorValue("Movie")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Movie extends Item {

  private String director;
  private String actor;

  public Movie(String name,
               int price,
               int stock,
               String director,
               String actor) {
    this.changeName(name);
    this.changePrice(price);
    this.changeStock(stock);
    this.director = director;
    this.actor = actor;
  }

}
