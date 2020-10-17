package ru.home.groceryboard.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.home.groceryboard.data.dto.CreateGroceryDto;
import ru.home.groceryboard.data.dto.GroceryDto;
import ru.home.groceryboard.data.model.Grocery;
import ru.home.groceryboard.data.model.GroceryPrice;
import ru.home.groceryboard.service.GroceryService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grocery")
@RequiredArgsConstructor
public class GroceryController {
    private final GroceryService groceryService;

    @PostMapping("/create")
    public GroceryDto create(@RequestBody CreateGroceryDto createGroceryDto) {
        final Grocery grocery = groceryService.createGrocery(createGroceryDto);
        return GroceryDto.of(grocery, grocery.getPrices().get(0));
    }

    @DeleteMapping("/delete")
    public void deleteGrocery(@RequestParam long id) {
        groceryService.deleteGrocery(id);
    }

    @GetMapping("/all")
    public List<GroceryDto> getAll(@RequestParam Date date) {
        final List<Grocery> groceries = groceryService.getAllGroceries();
        date = date != null ? date : new Date();

        Date finalDate = date;
        return groceries.stream().map(grocery -> {
            final GroceryPrice groceryPrice = groceryService.choosePriceAtDay(grocery, finalDate);
            return GroceryDto.of(grocery, groceryPrice);
        }).collect(Collectors.toList());
    }

}
