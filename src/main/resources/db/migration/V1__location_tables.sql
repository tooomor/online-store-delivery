create table locations
(
    id            int primary key auto_increment,
    location_code varchar(5),
    location_name varchar(100),
    address_id    int not null
);

create table addresses
(
    id               int primary key auto_increment,
    street           varchar(100),
    house_number     varchar(5),
    apartment_number varchar(5),
    postal_code      varchar(7),
    city             varchar(25),
    location_id      int not null
);

create table geolocation
(
    id         int primary key auto_increment,
    position_x double not null,
    position_y double not null
);