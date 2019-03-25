package com.kata.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@EqualsAndHashCode(of= {"name"})
@Getter
@Setter
@Builder
public class Product {
    String name;
    float price;
    Discount discounts;
}
