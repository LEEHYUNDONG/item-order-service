package hello.itemservice.dto;


import hello.itemservice.domain.item.Item;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ItemDto {

    @NonNull
    private String itemName;

    @NonNull
    private Integer price;

    @NonNull
    private Integer quantity;

    public static ItemDto of(Item item){
        return ItemDto.builder()
                .itemName(item.getItemName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();
    }
}
