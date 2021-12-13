drop table if exists product;
create table if not exists product
(
    id                 bigserial primary key,
    title               varchar(255),
    price              int
);

drop table if exists customer;
create table if not exists customer
(
    id    bigserial primary key,
    name varchar(255)
);

drop table if exists product_customer;
create table if not exists product_customer
(
    product_id bigint,
    customer_id  bigint,

    foreign key (product_id) references product (id),
    foreign key (customer_id) references customer (id)
);

insert into customer (name)
values ('Андрей'),
       ('Иван'),
       ('Дмитрий');

insert into product (title, price)
values ('Компьютер', 70000),
       ('Ноутбук', 50000),
       ('Телефон', 30000);

insert into product_customer (product_id, customer_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (3, 2),
       (3, 3);

select s.id as product_id, title, price,
       c.id as customer_id, c.name from product s
                left join product_customer sc on s.id = sc.product_id
                left join customer c on sc.customer_id = c.id;