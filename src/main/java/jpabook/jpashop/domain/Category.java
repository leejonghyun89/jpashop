package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {

  @Id @GeneratedValue
  @Column(name = "category_id")
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(name = "category_item",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "item_id")
  )
  private List<Item> items = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> categories = new ArrayList<>();

  public void changeCategoriesParent(Category parent) {
    this.parent = parent;
    parent.getCategories().add(this);
  }

  public Category(String name) {
    this.name = name;
  }

}
