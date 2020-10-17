package ru.home.groceryboard.data.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "grocery")
@Builder
public class Grocery {

    @Id
    @SequenceGenerator(name = "grocery_sequence", sequenceName = "grocery_sequence", allocationSize = 1)
    @GeneratedValue(generator = "grocery_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "grocery_id")
    private List<GroceryPrice> prices = new ArrayList<>();
}
