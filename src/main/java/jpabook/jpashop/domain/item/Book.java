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
@DiscriminatorValue("Book")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book extends Item {

  private String author;
  private String isbn;

  public Book(String name,
              int price,
              int stock,
              String author,
              String isbn) {
    this.changeName(name);
    this.changePrice(price);
    this.changeStock(stock);
    this.author = author;
    this.isbn = isbn;
  }

}
