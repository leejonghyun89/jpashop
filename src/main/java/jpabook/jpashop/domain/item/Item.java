package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dType")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Item {

  @Id @GeneratedValue
  @Column(name = "item_id")
  private Long id;

  private String name;

  private int price;

  private int stock;

  @ManyToMany(mappedBy = "items")
  private List<Category> categories = new ArrayList<>();

  public void changeCategory(Category category) {
    this.categories.add(category);
    category.getItems().add(this);
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public void addStock(int stock) {
    this.stock += stock;
  }

  /**
   * 재고 마이너스 비즈니스 로직
   * @param stock
   */
  public void removeStock(int stock) {
    int resultStock = this.stock - stock;

    if (resultStock < 0) {
      throw new NotEnoughStockException("need more stock");
    }
    this.stock = resultStock;
  }

}
