package ru.home.groceryboard.data.dto;

import lombok.Data;

@Data
public class CreateGroceryDto {
    private String name;
    private double price;
}
