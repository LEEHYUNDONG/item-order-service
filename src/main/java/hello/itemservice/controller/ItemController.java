package hello.itemservice.controller;

import hello.itemservice.domain.item.Item;
import hello.itemservice.repository.ItemRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepositoryImpl itemRepositoryImpl;

    @PostConstruct
    public void init(){
        itemRepositoryImpl.save(new Item("testA", 10000, 10));
        itemRepositoryImpl.save(new Item("testB", 20000, 20));
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepositoryImpl.findAll();
        model.addAttribute("items", items);
        return "basic/items";

    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepositoryImpl.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam Integer price,
                            @RequestParam Integer quantity,
                            Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepositoryImpl.save(item);
        model.addAttribute("item", item);

        return "redirect:/basic/items";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){

        itemRepositoryImpl.save(item);
//        model.addAttribute("item", item);

        return "redirect:/basic/items";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model){

        itemRepositoryImpl.save(item);
//        model.addAttribute("item", item);

        return "redirect:/basic/items";
    }


//    @PostMapping("/add")
    public String addItemV4(Item item, Model model){

        itemRepositoryImpl.save(item);
//        model.addAttribute("item", item);

        return "redirect:/basic/items";
    }

    /**
     * PRG - Post/Redirect/Get
     */
//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepositoryImpl.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    /**
     * RedirectAttributes
     */
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepositoryImpl.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepositoryImpl.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepositoryImpl.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
}
