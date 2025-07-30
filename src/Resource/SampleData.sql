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

create table SupplyLog (
	supNo int auto_increment,
    franNo int not null,
    proNo int not null,
    supQty int not null,
    supReqDate datetime default now(),
    supAppDate datetime default null,
    supStatus int default 0,
    supMemo varchar(200),
    constraint primary key(supNo),
    constraint foreign key(franNo) references Store(franNo),
    constraint foreign key(proNo) references Product(proNo)
);

create table orderLog(
	orderNo int auto_increment,
    franNo int not null,
    proNo int not null,
    orderQty int not null,
    orderPrice int not null,
    orderDate datetime default now(),
    constraint primary key(orderNo),
    constraint foreign key(franNo) references Store(franNo),
    constraint foreign key(proNo) references Product(proNo)
);

create table inventoryLog(
    invenNo int auto_increment,
    constraint primary key(invenNo),
    proNo int,
    invenIO bool not null,
    invenQty int default "0",
    invenDate datetime default now(),
    invenMemo varchar(200),
    constraint foreign key(proNo) references Product(proNo)
);

create table review(
    reviewNo int auto_increment,
    constraint primary key(reviewNo),
    review longtext not null,
    orderNo int,
    constraint foreign key(orderNo) references orderLog(orderNo)
);

select * from Store;
select * from Product;
select * from SupplyLog;
select * from orderLog;
select * from inventoryLog;
select * from review;

alter table Store auto_increment = 10001;
alter table Product auto_increment = 20001;
alter table SupplyLog auto_increment = 30001;
alter table orderLog auto_increment = 40001;
alter table inventoryLog auto_increment = 50001;
alter table review auto_increment = 60001;

insert into Store(franName,franAddress,franCall,franOwner,franStatus) values("부천로점","경기도 부천시 원미구 부천로 270","032-0000-1111","홍길동",FALSE);
insert into Store(franName,franAddress,franCall,franOwner,franStatus) values("가좌1동점","인천광역시 서구 원적로 105 1층","032-1111-2222","김서방",FALSE);
insert into Store(franName,franAddress,franCall,franOwner,franStatus) values("가좌2동점","인천광역시 서구 장고개로243번길 25 1층","032-2222-3333","흥부",FALSE);
insert into Store(franName,franAddress,franCall,franOwner,franStatus) values("신중동점","경기도 부천시 원미구 중동 1058-2","032-3333-4444","갑을병",FALSE);

insert into Product(proName,proSupPrice,proPrice) values("김치찌개",4000,8000);
insert into Product(proName,proSupPrice,proPrice) values("알리오올리오",5500,8500);
insert into Product(proName,proSupPrice,proPrice) values("버섯전골",7500,12500);
insert into Product(proName,proSupPrice,proPrice) values("춘천닭갈비",8000,12000);

insert into SupplyLog( franNo, proNo, supQty, supReqDate, supAppDate, supStatus, supMemo ) values 
    ( 10002, 20002, 10, "2025-07-21 15:25:08", "2025-07-22 09:25:57", 1, " " ),
    ( 10003, 20004, 15, "2025-07-22 19:30:54", "2025-07-23 09:22:07", 2, "빠른 배송 부탁드립니다." ),
    ( 10001, 20002, 30, "2025-07-22 21:19:07", "2025-07-23 10:07:09", 1, " " );
insert into SupplyLog( franNo, proNo, supQty, supReqDate, supStatus, supMemo )
	values ( 10001, 20001, 5 , "2025-07-25 10:23:31", 0, " " );
insert into orderLog( franNo, proNo, orderQty, orderPrice, orderDate ) values
	( 10002, 20001, 2, 8000, "2025-07-22 11:35:47" ),
    ( 10001, 20003, 3, 12500, "2025-07-22 11:35:59" ),
    ( 10002, 20001, 1, 7200, "2025-07-22 12:07:09" ),
    ( 10004, 20002, 1, 8500, "2025-07-22 12:09:10" );
    
insert into inventoryLog( proNo, invenIO , invenQty , invenDate , invenMemo ) values( 20001, true , 10 , "2025-07-22 9:25:57" , "20001번 10개");
insert into inventoryLog( proNo, invenIO , invenQty , invenDate , invenMemo ) values( 20002, true , 25 , "2025-07-23 9:25:57" , "");
insert into inventoryLog( proNo, invenIO , invenQty , invenDate , invenMemo ) values( 20003, true , 15 , "2025-07-24 9:25:57" , "");
insert into inventoryLog( proNo, invenIO , invenQty , invenDate , invenMemo ) values( 20002, false , 20 , "2025-07-26 9:25:57" , "");

insert into review( review, orderNo ) value("여기 부대찌개 맛있어요~", 40004);
insert into review( review, orderNo ) value("라면사리 넣어먹으면 최고에요", 40003);
insert into review( review, orderNo ) value("싸고 맛있어요", 40002);
insert into review( review, orderNo ) value("벌레나왔어요 ㅡㅡ", 40002);