package com.kata.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@EqualsAndHashCode(of= {"number"})
@Getter
@Setter
@Builder
public class Discount {
    Integer number;
    Float value;
}
