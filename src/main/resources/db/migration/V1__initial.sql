create table valorant_premier_entry
(
    id   bigint auto_increment
        primary key,
    map  varchar(255) not null,
    date datetime     not null
);