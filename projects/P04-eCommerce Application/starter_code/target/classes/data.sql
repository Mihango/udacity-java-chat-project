create table if not exists item(name varchar(50) primary key, price double, description varchar(60));
insert into item (name, price, description) values ('Round Widget', 2.99, 'A widget that is round');
insert into item (name, price, description) values ('Square Widget', 1.99, 'A widget that is square');
