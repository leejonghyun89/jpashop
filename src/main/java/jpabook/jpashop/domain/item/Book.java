package jpabook.jpashop.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

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

  public Book(Long id,
              String name,
              int price,
              int stock,
              String author,
              String isbn) {

    if (!ObjectUtils.isEmpty(id)) {
      this.setId(id);
    }
    this.setName(name);
    this.setPrice(price);
    this.setStock(stock);
    this.author = author;
    this.isbn = isbn;
  }

}
