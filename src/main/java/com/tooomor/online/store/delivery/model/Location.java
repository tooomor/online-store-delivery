package com.tooomor.online.store.delivery.model;

import javax.persistence.OneToOne;

class Location {
    private Integer id;
    private String locationName;
    @OneToOne
    private Address address;
}
