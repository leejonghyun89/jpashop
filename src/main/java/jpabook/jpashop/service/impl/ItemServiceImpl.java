package jpabook.jpashop.service.impl;

import jpabook.jpashop.domain.dto.ItemDto;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-21
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  /**
   * 상품 저장
   * @param item
   */
  @Transactional
  public void saveItem(Item item) {
    itemRepository.save(item);
  }

  /**
   * 상품 정보 조회
   * @return 상품 목록
   */
  public List<Item> findItems() {
    return itemRepository.findAll();
  }

  @Override
  @Transactional
  public void updateItem(Long itemId,
                         ItemDto.UpdateItem updateItem) {

    Item findItem = itemRepository.findById(itemId);
    findItem.setName(updateItem.getName());
    findItem.setPrice(updateItem.getPrice());
    findItem.setStock(updateItem.getStock());
  }

  /**
   * 상품 정보 상세 조회
   * @param itemId 상품 ID
   * @return 상품 상세 정보
   */
  public Item findItemById(Long itemId) {
    return itemRepository.findById(itemId);
  }

}
