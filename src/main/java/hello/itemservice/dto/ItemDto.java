package hello.itemservice.dto;


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

}
