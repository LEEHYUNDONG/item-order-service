package hello.itemservice.service;

import hello.itemservice.domain.item.Item;
import hello.itemservice.dto.ItemDto;
import hello.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{


    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public ItemDto addItem(ItemDto req) {
        Item item = Item.builder()
                .itemName(req.getItemName())
                .price(req.getPrice())
                .quantity(req.getQuantity())
                .build();
        itemRepository.save(item);

        return ItemDto.of(item);
    }

    @Override
    @Transactional
    public Long updateItem(Long itemId, ItemDto req) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("No item"));
        item.update(req.getItemName(), req.getPrice(), req.getQuantity());
        return item.getId();
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Item"));
        itemRepository.delete(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemDto> getItemList() {
        List<Item> itemList = itemRepository.findAll();
        System.out.println("getItemList");
        for (Item item : itemList) {
            System.out.println("item.toString() = " + item.toString());
            
        }

        return itemRepository.findAll().stream().map(ItemDto::of).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDto findItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no item"));
        return ItemDto.of(item);
    }
}
