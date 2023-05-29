package hello.itemservice.service;

import hello.itemservice.domain.item.Item;
import hello.itemservice.dto.ItemDto;
import hello.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public ItemDto addItem(ItemDto req) {
        Item item = itemRepository.save(Item.builder()
                .itemName(req.getItemName())
                .price(req.getPrice())
                .quantity(req.getQuantity())
                .build());
        return ItemDto.of(item);
    }

    @Override
    public Long updateItem(Long itemId, ItemDto req) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("No item"));
        item.update(req.getItemName(), req.getPrice(), req.getQuantity());
        return item.getId();
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Item"));
        itemRepository.delete(item);
    }

    @Override
    public List<ItemDto> getItemList() {
        return itemRepository.findAll().stream().map(ItemDto::of).collect(Collectors.toList());
    }

    @Override
    public ItemDto findItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no item"));
        return ItemDto.of(item);
    }
}
