SELECT * FROM java.users;
drop table if exists message;
create table message(
    username varchar(50),
    text mediumtext,
    time datetime
);
insert into message(username,text,time)values
('wangjie','我是测试的评论1','2022-11-10 12:22:12'),
('wangjie','我是测试的评论2','2022-11-11 11:11:11'),
('wangjie','我是测试的评论3','2022-11-12 10:10:10');

drop table if exists books;
create table books(
	bookid int primary key,
    bookname varchar(50),
    price double
);
insert into books values
(1000,'JAVA技术手册',98.60),
(1001,'jQuery技术手册',89.90),
(1002,'ServletAndJsp技术手册',100.20);

drop table if exists orders;
create table orders(
	orderid int auto_increment primary key,
    username varchar(50),
    bookname varchar(50),
    price double,
    quantity int,
    amount double as(price * quantity)
);
insert into orders (username,bookname,price,quantity) values ('王杰','嗡嗡嗡',12.00,1);