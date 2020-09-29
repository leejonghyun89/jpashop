package jpabook.jpashop.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by JongHyun Lee on 2020-09-27
 */
public class ItemDto {

  @Getter @Setter
  public static class ItemForm {

    private Long id;
    private String name;
    private int price;
    private int stock;
    private String author;
    private String isbn;
  }

  @Getter @Setter @NoArgsConstructor
  public static class UpdateItem {
    private String name;
    private int price;
    private int stock;

    private UpdateItem(String name,
                       int price,
                       int stock) {
      this.name = name;
      this.price = price;
      this.stock = stock;
    }

    public static UpdateItem ofDefault(String name,
                                       int price,
                                       int stock) {
      return new UpdateItem(name, price, stock);
    }
  }

}
