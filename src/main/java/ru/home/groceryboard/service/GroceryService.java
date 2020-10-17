package ru.home.groceryboard.service;

import com.google.common.collect.Lists;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.groceryboard.data.dao.GroceryDao;
import ru.home.groceryboard.data.dto.CreateGroceryDto;
import ru.home.groceryboard.data.model.Grocery;
import ru.home.groceryboard.data.model.GroceryPrice;
import ru.home.groceryboard.utils.GroceryPriceUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class GroceryService {
    private final GroceryDao groceryDao;

    public Grocery createGrocery(CreateGroceryDto createGroceryDto) {
        final Grocery grocery = Grocery.builder()
                .name(createGroceryDto.getName())
                .build();

        final GroceryPrice groceryPrice = GroceryPrice.builder()
                .dateFrom(new Date())
                .build();

        grocery.getPrices().add(groceryPrice);

        groceryDao.save(grocery);
        return grocery;
    }

    public GroceryPrice choosePriceAtDay(Grocery grocery, Date date) {
        return grocery.getPrices().stream()
                .sorted((GroceryPriceUtils::compareGroceryPrice))
                .filter(price -> choosePrice(price, date)).findFirst().orElse(null);
    }

    public void deleteGrocery(long id) {
        groceryDao.deleteById(id);
    }

    public List<Grocery> getAllGroceries() {
        return Lists.newArrayList(groceryDao.findAll());
    }

    public void setPrice(long id, double price, Date dateFrom, Date dateTo) {
        final Grocery grocery = groceryDao.findById(id).orElseThrow(IllegalStateException::new);


        //   grocery.setPrices(pricesForEdit);
        groceryDao.save(grocery);
    }

    private boolean choosePrice(GroceryPrice groceryPrice, Date date) {
        final Date dateFrom = groceryPrice.getDateFrom();
        final Date dateTo = groceryPrice.getDateTo();

        if (dateFrom != null && dateTo != null) {
            return !dateFrom.after(date) && !dateTo.before(date);
        } else if (dateFrom != null) {
            return !dateFrom.after(date);
        } else {
            return !dateTo.before(date);
        }
    }

}
