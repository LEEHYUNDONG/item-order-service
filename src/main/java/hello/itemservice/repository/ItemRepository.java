package hello.itemservice.repository;

import hello.itemservice.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
}
