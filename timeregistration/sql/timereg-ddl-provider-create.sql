
create table provider (
	id int primary key not null AUTO_INCREMENT,
	name varchar(60)  not null,
	created timestamp not null default now()
) engine innodb;

create table provider_code (
	id int primary key not null AUTO_INCREMENT,
	provider_id int  not null,
	created timestamp not null default now(),
	code varchar(20)  not null,
	name varchar(60)  not null,
	foreign key (provider_id) references provider(id) on delete cascade
) engine innodb;

create table user_provider_code_link (
	id int primary key not null AUTO_INCREMENT,
	user_id int  not null,
	provider_code_id int  not null,
	created timestamp not null default now(),
	ended timestamp ,
	foreign key (user_id) references user(id) on delete cascade,
	foreign key (provider_code_id) references provider_code(id) on delete cascade
) engine innodb;

