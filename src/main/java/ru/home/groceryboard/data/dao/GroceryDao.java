package ru.home.groceryboard.data.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.groceryboard.data.model.Grocery;

@Repository
public interface GroceryDao extends CrudRepository<Grocery, Long> {
}
