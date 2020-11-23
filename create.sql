create table address_entity (id integer not null auto_increment, city varchar(255), number integer not null, street varchar(255), primary key (id)) engine=InnoDB;
create table departement_entity (id integer not null auto_increment, code_depart varchar(255) not null, name varchar(255), primary key (id)) engine=InnoDB;
create table employee (code integer not null auto_increment, date_of_birth date, employeename varchar(50) not null, address_id integer, primary key (code)) engine=InnoDB;
create table phone_number_entity (id integer not null auto_increment, number varchar(255), operator varchar(255), employee_code integer, primary key (id)) engine=InnoDB;
create table worked_in (departs_id integer not null, employees_code integer not null) engine=InnoDB;
alter table departement_entity add constraint UK_7vq43kloew08gn6ul03i7f708 unique (code_depart);
alter table employee add constraint UK_d8gxwkhe2275no2gknyn4aud9 unique (employeename);
alter table employee add constraint FKdjw360q23xli6x0o7yiy3gy9 foreign key (address_id) references address_entity (id);
alter table phone_number_entity add constraint FKqp0ldno07j5gn34qm5ga387bq foreign key (employee_code) references employee (code);
alter table worked_in add constraint FKfse4nykp2yvl5q4i9pxam1ho3 foreign key (employees_code) references employee (code);
alter table worked_in add constraint FKj2kxvjvdaig93b8wg62lnk18t foreign key (departs_id) references departement_entity (id);
