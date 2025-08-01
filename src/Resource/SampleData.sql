drop database if exists FranManager;
create database FranManager;
use FranManager;

create table Fran(
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

create table SupplyLog (
	supNo int auto_increment,
    franNo int not null,
    proNo int not null,
    supQty int not null,
    supReqDate datetime default now(),
    supStatus int default 0,
    supMemo varchar(200),
    constraint primary key(supNo),
    constraint foreign key(franNo) references Fran(franNo),
    constraint foreign key(proNo) references Product(proNo)
);


create table OrderLog(
	orderNo int auto_increment,
    franNo int not null,
    proNo int not null,
    orderQty int not null,
    orderPrice int not null,
    orderDate datetime default now(),
    constraint primary key(orderNo),
    constraint foreign key(franNo) references Fran(franNo),
    constraint foreign key(proNo) references Product(proNo)
);

create table InventoryLog(
    invenNo int auto_increment,
    constraint primary key(invenNo),
    proNo int,
    invenIO bool not null,
    invenQty int default "0",
    invenDate datetime default now(),
    invenMemo varchar(200),
    constraint foreign key(proNo) references Product(proNo)
);


create table Review(
    reviewNo int auto_increment,
    constraint primary key(reviewNo),
    review longtext not null,
    orderNo int,
    constraint foreign key(orderNo) references orderLog(orderNo)
);

alter table Fran auto_increment = 10001;
alter table Product auto_increment = 20001;
alter table SupplyLog auto_increment = 30001;
alter table OrderLog auto_increment = 40001;
alter table InventoryLog auto_increment = 50001;
alter table Review auto_increment = 60001;

select * from Fran;
select * from Product;
select * from SupplyLog;
select * from OrderLog;
select * from InventoryLog;
select * from Review;