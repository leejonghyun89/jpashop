package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
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

  public void changeName(String name) {
    this.name = name;
  }

  public void changePrice(int price) {
    this.price = price;
  }

  public void changeStock(int stock) {
    this.stock = stock;
  }

}
