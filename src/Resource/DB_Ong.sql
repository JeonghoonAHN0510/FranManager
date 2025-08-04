drop database if exists FranManager;
create database FranManager;
use FranManager;

create table Store(
franNo int auto_increment,
franName varchar(30) not null unique,
franAddress varchar(500) not null,
franCall varchar(15) not null default "000-0000-0000" ,
franOwner varchar(15) not null,
franStatus bool not null default false,
constraint primary key( franNo ) );

create table Product(
proNo int auto_increment,
proName varchar(60) not null, 
proSupPrice int not null,
proPrice int not null,
constraint primary key( proNo ));

select *from product;