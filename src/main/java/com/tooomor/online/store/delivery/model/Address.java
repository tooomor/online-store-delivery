package com.tooomor.online.store.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    String street;
    String houseNumber;
    String apartmentNumber;
    String postalCode;
    String city;
    Location location;
}
