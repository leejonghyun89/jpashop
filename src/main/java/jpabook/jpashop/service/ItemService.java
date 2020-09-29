package jpabook.jpashop.service;


import jpabook.jpashop.domain.dto.ItemDto;
import jpabook.jpashop.domain.item.Item;

import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-21
 */
public interface ItemService {

  void saveItem(Item item);

  List<Item> findItems();

  void updateItem(Long itemId,
                  ItemDto.UpdateItem updateItem);

  Item findItemById(Long itemId);

}
