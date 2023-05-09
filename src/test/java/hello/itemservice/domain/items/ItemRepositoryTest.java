package hello.itemservice.domain.items;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        Assertions.assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 30000, 220);

        //when
        itemRepository.save(item1);
        itemRepository.save(item2);
        List<Item> res = itemRepository.findAll();

        //then
        Assertions.assertThat(res.size()).isEqualTo(2);
        Assertions.assertThat(res).contains(item1, item2);


    }

    @Test
    void updateItem(){
        //given
        Item item = new Item("itemA", 10000, 10);

        Item newItem = new Item("new item", 20000, 20);

        //when
        Item savedItem = itemRepository.save(item);
        itemRepository.update(savedItem.getId(), newItem);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());

        Assertions.assertThat(findItem.getItemName()).isEqualTo(newItem.getItemName());

    }
}