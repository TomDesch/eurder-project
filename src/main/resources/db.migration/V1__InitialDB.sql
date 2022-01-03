create sequence IF NOT EXISTS user_seq start with 1 increment by 1;
create table user
(
    id            integer default nextval('user_seq') primary key,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    email         varchar(255),

    street_name   varchar(255),
    house_number  varchar(255),
    postal_code   varchar(255),
    country       varchar(255),

    phone_number  varchar(255),
    user_type     varchar(255),
    country_label varchar(255),
    password      varchar(255)
)

create sequence IF NOT EXISTS item_seq start with 1 increment by 1;
create table item
(
    id          integer default nextval('item_seq') primary key,
    name        varchar(255) not null,
    description varchar(255),
    price       float,
    amount      float,

)