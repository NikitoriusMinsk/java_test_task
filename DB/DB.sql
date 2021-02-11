create database Pharmacy;
use Pharmacy;

create table Shops(
shop_id int auto_increment primary key,
adress varchar(40) not null,
shop_name varchar(40) not null
);

insert into Shops (adress,shop_name) values 
("Pushkina 10","ph1"),
("Marcynkevicha 5","ph2"),
("Filimonova 22","ph3");

create table Med_groups(
group_id int auto_increment primary key,
group_name varchar(30) not null
);

insert into Med_groups (group_name) values 
("immune"),
("diagnostic"),
("neuron");

create table Medicine(
med_id int auto_increment primary key,
group_id int not null,
med_name varchar(30) not null,
foreign key (group_id) references Med_groups(group_id) on update cascade on delete cascade
);

insert into Medicine (group_id,med_name) values 
(1,"immune_med1"),
(1,"immune_med2"),
(2,"diag_med1");

create table Availability(
id int auto_increment primary key,
shop_id int not null,
med_id int not null,
quantity int not null,
foreign key (shop_id) references Shops(shop_id) on update cascade on delete cascade,
foreign key (med_id) references Medicine(med_id) on update cascade on delete cascade
);

insert into Availability (shop_id, med_id, quantity) values 
(1,2,10),
(2,3,15),
(3,1,9);

create table Employees(
emp_id int auto_increment primary key,
shop_id int not null,
emp_name varchar(50) not null,
position varchar(30) not null,
foreign key (shop_id) references Shops(shop_id)
);

insert into Employees (shop_id,emp_name,position) values 
(1,"name1","cashier"),
(1,"name2","cashier"),
(2,"name3","cashier"),
(3,"name4","cashier");

create table Sales(
sale_id int auto_increment primary key,
shop_id int not null,
med_id int not null,
price float not null,
sale_date varchar(20) not null,
foreign key (shop_id) references Shops(shop_id),
foreign key (med_id) references Medicine(med_id)
);

insert into Sales (shop_id, med_id, price, sale_date) values
(1,2,100,current_date()),
(2,3,5,current_date()),
(3,1,1,current_date()),
(3,1,10,current_date());

select id,med_name, shop_name, quantity from Availability 
join Shops on Availability.shop_id=Shops.shop_id 
join Medicine on Availability.med_id=Medicine.med_id 
where quantity>=10;

select shop_name, med_name, price, sale_date from Sales 
join Shops on Sales.shop_id=Shops.shop_id 
join Medicine on Sales.med_id=Medicine.med_id
where price<=5;

select shop_name, count(*) as EmpCount from Employees 
join Shops on Employees.shop_id = Shops.shop_id
group by Shops.shop_id;

select med_name, count(*) as TimesSold, sum(price) as SumOfSales from Sales 
join Medicine on Sales.med_id=Medicine.med_id
group by Sales.med_id;

select group_name, count(*) as TimesSold from Sales 
join Medicine on Sales.med_id=Medicine.med_id
join Med_groups on Medicine.group_id=Med_groups.group_id
group by Med_groups.group_id;