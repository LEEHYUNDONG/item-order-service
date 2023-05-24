package hello.itemservice.domain.items;

import hello.itemservice.domain.item.Item;
import hello.itemservice.repository.ItemRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class ItemRepositoryImplTest {

    ItemRepositoryImpl itemRepositoryImpl = new ItemRepositoryImpl();

    @AfterEach
    void afterEach(){
        itemRepositoryImpl.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepositoryImpl.save(item);

        //then
        Item findItem = itemRepositoryImpl.findById(item.getId());
        Assertions.assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 30000, 220);

        //when
        itemRepositoryImpl.save(item1);
        itemRepositoryImpl.save(item2);
        List<Item> res = itemRepositoryImpl.findAll();

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
        Item savedItem = itemRepositoryImpl.save(item);
        itemRepositoryImpl.update(savedItem.getId(), newItem);

        //then
        Item findItem = itemRepositoryImpl.findById(savedItem.getId());

        Assertions.assertThat(findItem.getItemName()).isEqualTo(newItem.getItemName());

    }
}