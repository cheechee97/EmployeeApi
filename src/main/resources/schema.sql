create table employees
(
   id integer IDENTITY not null,
   name varchar(255) not null,
   age integer not null,
   email_address varchar(255) not null,
   primary key(id)
);