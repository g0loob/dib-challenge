create table beer(
    id bigint auto_increment primary key,
    external_id bigint unique,
    name varchar(255) not null,
    description varchar(1000)
);

create table beer_mash_temperature(
    id bigint auto_increment primary key,
    beer_id bigint not null,
    value decimal(13, 4) not null,
    constraint fk_beer_id foreign key (beer_id) references beer(id)
);
