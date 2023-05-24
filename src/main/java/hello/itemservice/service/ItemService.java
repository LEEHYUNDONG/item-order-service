package hello.itemservice.service;

import hello.itemservice.dto.ItemDto;

import java.util.List;

public interface ItemService {

    void addItem(ItemDto req);
    Long updateItem(ItemDto req);
    void deleteItem(Long Id);
    List<ItemDto> getItemList();

}
