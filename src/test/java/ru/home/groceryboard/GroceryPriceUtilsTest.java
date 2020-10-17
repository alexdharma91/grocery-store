package ru.home.groceryboard;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import ru.home.groceryboard.data.model.Grocery;
import ru.home.groceryboard.data.model.GroceryPrice;
import ru.home.groceryboard.utils.GroceryPriceUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroceryPriceUtilsTest {
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /*
    Дефолтная цена = 10 (срок действия 17/10/2020 - ****)
    Добавляем временную цену = 12 (срок действия 20/10/2020 - 22/10/2020)

    Ожидаем в итоге получить 3 промежутка с ценой
     */
    @Test
    void case1() throws ParseException {
        List<GroceryPrice> initialPriceList = new ArrayList<>();
        initialPriceList.add(getGroceryPrice(getDate("17/10/2020"), null, 10));

        GroceryPriceUtils.putNewPrice(initialPriceList, getDate("20/10/2020"), getDate("22/10/2020"), 12);

        assertEquals(3, initialPriceList.size());
    }

    /*
      Изначально есть 2 цены на разные периоды -
      1) цена - 10 (срок действия **** - 17/10/2020)
      2) цена - 12 (срок действия 18/10/2020 - 30/10/2020)

      Пытаемся добавить новую цену, 12 для периода 20/10/2020 - 22/10/2020
      Новый период должен лечь в уже существующий
     */
    @Test
    void case2() throws ParseException {
        List<GroceryPrice> initialPriceList = new ArrayList<>();
        initialPriceList.add(getGroceryPrice(null, getDate("17/10/2020"), 10));
        initialPriceList.add(getGroceryPrice(getDate("18/10/2020"), getDate("30/10/2020"), 12));

        GroceryPriceUtils.putNewPrice(initialPriceList, getDate("20/10/2020"), getDate("22/10/2020"), 12);

        assertEquals(2, initialPriceList.size());
    }

    /*
     Изначально есть 2 цены на разные периоды -
      1) цена - 10 (срок действия **** - 17/10/2020)
      2) цена - 12 (срок действия 18/10/2020 - 30/10/2020)

      Пытаемся добавить новую цену, 15 для периода 15/10/2020 - 20/10/2020

      На выходе должны иметь следующие цены для перидов
      1) цена - 10 (срок действия **** - 14/10/2020)
      2) цена - 15 (срок действия 15/10/2020 - 20/10/2020)
      3) цена - 12 (срок действия 21/10/2020 - 30/10/2020)
     */
    @Test
    void case3() throws ParseException {
        List<GroceryPrice> initialPriceList = new ArrayList<>();
        initialPriceList.add(getGroceryPrice(null, getDate("17/10/2020"), 10));
        initialPriceList.add(getGroceryPrice(getDate("18/10/2020"), getDate("30/10/2020"), 12));

        GroceryPriceUtils.putNewPrice(initialPriceList, getDate("15/10/2020"), getDate("20/10/2020"), 15);

        assertEquals(3, initialPriceList.size());
    }

    private GroceryPrice getGroceryPrice(Date from, Date to, double price) {
        return GroceryPrice.builder()
                .dateFrom(from)
                .dateTo(to)
                .price(price)
                .build();
    }

    private Date getDate(String date) throws ParseException {
        return StringUtils.isNotBlank(date) ? simpleDateFormat.parse(date) : null;
    }

}
