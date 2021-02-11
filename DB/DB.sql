create database Pharmacy;
use Pharmacy;

create table Shops(
shop_id varchar(10) primary key,
adress varchar(40) not null,
shop_name varchar(40) not null
);

insert into Shops values 
("shop1","Pushkina 10","ph1"),
("shop2","Marcynkevicha 5","ph2"),
("shop3","Filimonova 22","ph3");

create table Med_groups(
group_id varchar (10) primary key,
group_name varchar(30) not null
);

insert into Med_groups values 
("gr1","immune"),
("gr2","diagnostic"),
("gr3","neuron");

create table Medicine(
med_id varchar(10) primary key,
group_id varchar (10) not null,
med_name varchar(30) not null,
foreign key (group_id) references Med_groups(group_id) on update cascade on delete cascade
);

insert into Medicine values 
("med1","gr1","immune_med1"),
("med2","gr1","immune_med2"),
("med3","gr2","diag_med1");

create table Availability(
id int auto_increment primary key,
shop_id varchar(10) not null,
med_id varchar(10) not null,
quantity int not null,
foreign key (shop_id) references Shops(shop_id) on update cascade on delete cascade,
foreign key (med_id) references Medicine(med_id) on update cascade on delete cascade
);

insert into Availability (shop_id, med_id, quantity) values 
("shop1","med2",10),
("shop2","med3",15),
("shop3","med1",9);

create table Employees(
emp_id varchar(10) primary key,
shop_id varchar(10) not null,
emp_name varchar(50) not null,
position varchar(30) not null,
foreign key (shop_id) references Shops(shop_id)
);

insert into Employees values 
("emp1","shop1","name1","cashier"),
("emp2","shop1","name2","cashier"),
("emp3","shop2","name3","cashier"),
("emp4","shop3","name4","cashier");

create table Sales(
sale_id int auto_increment primary key,
shop_id varchar(10) not null,
med_id varchar(10) not null,
price float not null,
sale_date varchar(20) not null,
foreign key (shop_id) references Shops(shop_id),
foreign key (med_id) references Medicine(med_id)
);

insert into Sales (shop_id, med_id, price, sale_date) values
("shop1","med2",100,current_date()),
("shop2","med3",5,current_date()),
("shop3","med1",1,current_date()),
("shop3","med1",10,current_date());

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