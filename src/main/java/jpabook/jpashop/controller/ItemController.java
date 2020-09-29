package jpabook.jpashop.controller;

import jpabook.jpashop.domain.dto.ItemDto;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JongHyun Lee on 2020-09-27
 */
@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/items/new")
  public String createItemForm(Model model) {
    model.addAttribute("form", new ItemDto.ItemForm());
    return "items/createItemForm";
  }

  @PostMapping("/items/new")
  public String createItem(ItemDto.ItemForm itemForm) {
    Book book = new Book(null, itemForm.getName(), itemForm.getPrice(), itemForm.getStock(), itemForm.getAuthor(), itemForm.getIsbn());
    itemService.saveItem(book);
    return "redirect:/";
  }

  @GetMapping("/items")
  public String itemList(Model model) {
    model.addAttribute("items", itemService.findItems());
    return "items/itemList";
  }

  @GetMapping("/items/{itemId}/edit")
  public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
    Book item = (Book) itemService.findItemById(itemId);

    ItemDto.ItemForm form = new ItemDto.ItemForm();
    form.setId(item.getId());
    form.setName(item.getName());
    form.setPrice(item.getPrice());
    form.setStock(item.getStock());
    form.setAuthor(item.getAuthor());
    form.setIsbn(form.getIsbn());

    model.addAttribute("form", form);
    return "items/updateItemForm";
  }

  @PostMapping("/items/{itemId}/edit")
  public String updateItem (@PathVariable Long itemId,
                            @ModelAttribute("form") ItemDto.ItemForm itemForm) {

//    Book book = new Book(itemId, itemForm.getName(), itemForm.getPrice(), itemForm.getStock(), itemForm.getAuthor(), itemForm.getIsbn());

//    itemService.saveItem(book);
    ItemDto.UpdateItem updateItem = ItemDto.UpdateItem.ofDefault(itemForm.getName(), itemForm.getPrice(), itemForm.getStock());
    itemService.updateItem(itemId, updateItem);
    return "redirect:/items";
  }

}
