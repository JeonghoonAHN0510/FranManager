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
	constraint primary key( franNo )
);

create table Product(
	proNo int auto_increment,
	proName varchar(60) not null, 
	proSupPrice int not null,
	proPrice int not null,
    proStatus bool not null default true,
	constraint primary key( proNo )
);

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

create table IOLog(
    ioNo int auto_increment,
    proNo int,
    IO int default 0,
    # 입고 : 0, 출고 : supNo
    ioQty int default "0",
    ioDate datetime default now(),
    ioMemo varchar(200),
    constraint primary key(ioNo),
    constraint foreign key(proNo) references Product(proNo)
);


create table Review(
    reviewNo int auto_increment,
    review longtext not null,
    orderNo int,
    constraint primary key(reviewNo),
    constraint foreign key(orderNo) references orderLog(orderNo)
);

alter table Fran auto_increment = 10001;
alter table Product auto_increment = 20001;
alter table SupplyLog auto_increment = 30001;
alter table OrderLog auto_increment = 40001;
alter table IOLog auto_increment = 50001;
alter table Review auto_increment = 60001;

select * from Fran;
select * from Product;
select * from SupplyLog;
select * from OrderLog;
select * from IOLog;
select * from Review;

insert into Fran (franName, franAddress, franCall, franOwner ) values
	( '부천로점', '경기도 부천시 원미구 부천로 270', '032-050-1111', '홍길동' ),
	( '가좌1동점', '인천광역시 서구 원적로 105 1층', '032-111-2222', '김서방' ),
    ( '가좌2동점', '인천광역시 서구 장고개로243번길 25 1층', '032-222-3333', '흥부' ),
    ( '신중동', '경기도 부천시 원미구 중동 1058-2', '032-333-4444', '신데렐라' );

insert into Product ( proName, proSupPrice, proPrice, proStatus ) values
	( '김치찌개', 4000, 8000, true ),
    ( '알리오올리오', 5500, 8500, true ),
    ( '버섯전골', 7500, 12500, false ),
    ( '춘천닭갈비', 8000, 12000, true );
    
insert into SupplyLog ( franNo, proNo, supQty, supReqDate, supStatus, supMemo ) values
	( 10002, 20002, 10, '2025-07-21 15:25:08', 1, '' ),
    ( 10003, 20004, 15, '2025-07-22 19:30:54', 2, '빠른 배송 부탁드립니다.' ),
    ( 10001, 20002, 30, '2025-07-22 21:19:07', 1, '' ),
    ( 10001, 20001, 5, '2025-07-25 10:23:31', 0, '' );
    
insert into OrderLog ( franNo, proNo, orderQty, orderPrice, orderDate ) values
	( 10002, 20001, 2, 8000, '2025-07-22 11:35:47'),
    ( 10001, 20003, 3, 12500, '2025-07-22 11:35:59'),
    ( 10002, 20001, 1, 7200, '2025-07-22 12:07:09'),
    ( 10004, 20002, 4, 8500, '2025-07-22 12:09:10');
    
insert into IOLog ( proNo, IO, ioQty, ioDate, ioMemo ) values
	( 20001, 0, 10, '2025-07-30 14:40:00', '20001번 10개' ),
    ( 20002, 0, 25, '2025-07-30 15:45:00', '' ),
    ( 20003, 0, 15, '2025-07-30 17:55:00', '' ),
    ( 20002, 30001, 20, '2025-07-31 15:15:00', '' );
    
insert into Review ( review, orderNo ) values
	( '여기 부대찌개 맛있어요~', 40004 ),
    ( '라면사리 넣어먹으면 최고에요', 40003 ),
    ( '싸고 맛있어요', 40002 ),
    ( '벌레나왔어요 ㅡㅡ', 40002 );