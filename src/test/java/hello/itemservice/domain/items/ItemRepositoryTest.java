package hello.itemservice.domain.items;


import hello.itemservice.domain.item.Item;
import hello.itemservice.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;


@ActiveProfiles("test")
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void setup(){

    }

    @Test
    void save(){
        //given
        Item item1 = Item.builder()
                .itemName("item1")
                .price(1000)
                .quantity(23)
                .build();

        //when
        itemRepository.save(item1);
        Item findItem = itemRepository.findById(item1.getId()).orElseThrow(() -> new IllegalArgumentException());

        //then
        Assertions.assertThat(item1.getItemName()).isEqualTo(findItem.getItemName());
    }


}
