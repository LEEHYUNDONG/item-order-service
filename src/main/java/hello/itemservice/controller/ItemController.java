package hello.itemservice.controller;

import hello.itemservice.dto.ItemDto;
import hello.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class ItemController {

    private final ItemService itemService;

    @PostConstruct
    public void init(){
        itemService.addItem(new ItemDto("testA", 10000, 10));
        itemService.addItem(new ItemDto("testB", 20000, 20));
    }

    @GetMapping
    public HttpEntity<List<ItemDto>> items(){
//        return new ResponseEntity<List<ItemDto>>(itemService.getItemList(), HttpStatus.OK);
        return new ResponseEntity<>(itemService.getItemList(), HttpStatus.OK);

    }

    @GetMapping("/{itemId}")
    public HttpEntity<ItemDto> item(@PathVariable Long itemId) {
        return new ResponseEntity<>(itemService.findItem(itemId), HttpStatus.OK);
    }


    @PostMapping("/add")
    public HttpEntity<ItemDto> addItem(@RequestBody ItemDto item) {
        ItemDto savedItem = itemService.addItem(item);

        return new ResponseEntity<ItemDto>(savedItem, HttpStatus.OK);
    }


    @PostMapping("/{itemId}/edit")
    public HttpEntity<Object> edit(@PathVariable Long itemId, @RequestBody ItemDto item) {
        itemService.updateItem(itemId, item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public HttpEntity<Object> delete(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
