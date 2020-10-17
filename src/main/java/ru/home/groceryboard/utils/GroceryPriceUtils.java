package ru.home.groceryboard.utils;

import lombok.experimental.UtilityClass;
import ru.home.groceryboard.data.model.GroceryPrice;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class GroceryPriceUtils {

    public static List<GroceryPrice> putNewPrice(List<GroceryPrice> initialPriceList, Date dateFrom, Date dateTo, double price) {
        final List<GroceryPrice> pricesForEdit = initialPriceList.stream()
                .sorted((GroceryPriceUtils::compareGroceryPrice))
                .filter(groceryPrice -> {
                    final Date localDateFrom = groceryPrice.getDateFrom();
                    final Date localDateTo = groceryPrice.getDateTo();

                    final boolean condition1 = localDateTo != null && (localDateTo.after(dateFrom) || localDateTo.equals(dateFrom));
                    final boolean condition2 = localDateFrom != null && (localDateFrom.before(dateTo) || localDateFrom.equals(dateTo));

                    return condition1 || condition2;
                }).peek(groceryPrice -> {
                    if(groceryPrice.getPrice() == price) {

                        //  boolean wholeInclude =

                    } else {

                    }
                }).collect(Collectors.toList());

        return pricesForEdit;
    }

    public static int compareGroceryPrice(GroceryPrice first, GroceryPrice second) {
        final Date firstFrom = first.getDateFrom();
        final Date secondFrom = second.getDateFrom();

        if (firstFrom != null && secondFrom != null) {
            return firstFrom.compareTo(secondFrom);
        } else {
            return firstFrom != null ? 1 : -1;
        }
    }

}
