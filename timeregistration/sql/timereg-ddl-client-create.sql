
create table client (
	id int primary key not null AUTO_INCREMENT,
	name varchar(60)  not null,
	created timestamp not null default now()
) engine innodb;

create table client_code (
	id int primary key not null AUTO_INCREMENT,
	client_id int  not null,
	created timestamp not null default now(),
	code varchar(20)  not null,
	name varchar(60)  not null,
	foreign key (client_id) references client(id) on delete cascade
) engine innodb;

create table user_client_code_link (
	id int primary key not null AUTO_INCREMENT,
	user_id int  not null,
	client_code_id int  not null,
	created timestamp not null default now(),
	ended timestamp ,
	foreign key (user_id) references user(id) on delete cascade,
	foreign key (client_code_id) references client_code(id) on delete cascade
) engine innodb;

