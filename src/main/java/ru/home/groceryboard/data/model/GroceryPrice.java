package ru.home.groceryboard.data.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "grocery_price")
@Builder
public class GroceryPrice {

    @Id
    @SequenceGenerator(
            name = "grocery_price_sequence",
            sequenceName = "grocery_price_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "grocery_price_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_to")
    private Date dateTo;

    @Column(name = "price")
    private double price;

}
