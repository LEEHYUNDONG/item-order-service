package hello.itemservice.domain.item;


import lombok.*;

import javax.persistence.*;

@Data
@Entity(name="item")
@NoArgsConstructor
@AllArgsConstructor
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String itemName;
    @Column
    private Integer price;
    @Column
    private Integer quantity;


    @Builder
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public void update(String itemName, Integer price, Integer quantity){
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
