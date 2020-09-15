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
@DiscriminatorValue("Album")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Album extends Item {

  private String artist;
  private String etc;

  public Album(String name,
               int price,
               int stock,
               String artist,
               String etc) {
    this.changeName(name);
    this.changePrice(price);
    this.changeStock(stock);
    this.artist = artist;
    this.etc = etc;
  }

}
