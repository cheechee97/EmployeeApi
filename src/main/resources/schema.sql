create table employees
(
   employeeId integer IDENTITY not null,
   employeeName varchar(255) not null,
   age integer not null,
   email_address varchar(255) not null,
   primary key(employeeId)
);