package ru.home.groceryboard.data.dto;

import lombok.Builder;
import ru.home.groceryboard.data.model.Grocery;
import ru.home.groceryboard.data.model.GroceryPrice;

@Builder
public class GroceryDto {
    private long id;
    private String name;
    private double price;

    public static GroceryDto of(Grocery grocery, GroceryPrice groceryPrice) {
        return GroceryDto.builder()
                .id(grocery.getId())
                .name(grocery.getName())
                .price(groceryPrice != null ? groceryPrice.getPrice() : -1).build();
    }
}
