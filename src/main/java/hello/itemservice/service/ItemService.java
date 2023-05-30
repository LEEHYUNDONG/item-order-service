package hello.itemservice.service;

import hello.itemservice.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    ItemDto addItem(ItemDto req);

    Long updateItem(Long itemId, ItemDto req);

    void deleteItem(Long id);

    List<ItemDto> getItemList();

    ItemDto findItem(Long Id);

}
