package ru.home.groceryboard.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.groceryboard.data.dto.SetPriceDto;
import ru.home.groceryboard.service.GroceryService;

import java.util.Date;

@RestController
@RequestMapping("/grocery")
@RequiredArgsConstructor
public class GroceryPriceController {
    private final GroceryService groceryService;

    @PostMapping("/setprice")
    public void setPrice(@RequestBody SetPriceDto setPriceDto) {
        final long id = setPriceDto.getId();
        final double price = setPriceDto.getPrice();
        final Date dateFrom = setPriceDto.getDateFrom();
        final Date dateTo = setPriceDto.getDateTo();

        groceryService.setPrice(id, price, dateFrom, dateTo);
    }
}
