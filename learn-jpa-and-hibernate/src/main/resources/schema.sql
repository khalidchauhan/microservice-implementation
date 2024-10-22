create table Course (
	id bigint not null,
	name varchar(255) not null,
	author varchar(255) not null,
	primary key (id)
);

Insert into COURSE(id, name, author) values(1, 'Learn AWS', 'Ranga');