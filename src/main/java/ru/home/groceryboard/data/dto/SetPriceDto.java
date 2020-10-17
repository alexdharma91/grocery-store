package ru.home.groceryboard.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SetPriceDto {
  private long id;
  private double price;
  private Date dateFrom;
  private Date dateTo;
}
