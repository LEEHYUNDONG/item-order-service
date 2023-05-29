package hello.itemservice.controller;

import hello.itemservice.domain.item.Item;
import hello.itemservice.dto.ItemDto;
import hello.itemservice.repository.ItemRepositoryImpl;
import hello.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class ItemController {
    private final ItemRepositoryImpl itemRepositoryImpl;

    private final ItemService itemService;

    @PostConstruct
    public void init(){
        itemRepositoryImpl.save(new Item("testA", 10000, 10));
        itemRepositoryImpl.save(new Item("testB", 20000, 20));
    }

    @GetMapping
    public HttpEntity<List<ItemDto>> items(){
        return new ResponseEntity<List<ItemDto>>(itemService.getItemList(), HttpStatus.OK);


    }

    @GetMapping("/{itemId}")
    public HttpEntity<ItemDto> item(@PathVariable Long itemId) {
        return new ResponseEntity<ItemDto>(itemService.findItem(itemId), HttpStatus.OK);
    }


    @PostMapping("/add")
    public HttpEntity<ItemDto> addItem(ItemDto item, RedirectAttributes redirectAttributes) {
        ItemDto savedItem = itemService.addItem(item);

        return new ResponseEntity<ItemDto>(savedItem, HttpStatus.OK);
    }


    @PostMapping("/{itemId}/edit")
    public HttpEntity<Object> edit(@PathVariable Long itemId, @ModelAttribute ItemDto item) {
        itemService.updateItem(itemId, item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public HttpEntity<Object> delete(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
