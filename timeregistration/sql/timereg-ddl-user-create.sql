
create table user (
	id int primary key not null AUTO_INCREMENT,
	created timestamp not null default now(),
	login varchar(80) not null,
	name varchar(140),
	is_login_enabled enum ('Y','N') not null default 'n',
	enabled TINYINT NOT NULL DEFAULT 1 
) engine innodb;

create table password(
	id int primary key not null AUTO_INCREMENT,	
	created timestamp not null default now(),
	password varchar(60)  not null
) engine innodb;

create table user_password_link(
	id int primary key not null AUTO_INCREMENT,
	user_id int  not null,
	password_id int  not null,
	created timestamp not null default now(),
	ended timestamp ,
	foreign key (password_id) references password(id) on delete cascade,
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;

create table role(
	id int primary key not null AUTO_INCREMENT,	
	created timestamp not null default now(),
	role varchar(60)  not null
) engine innodb;

create table user_role_link(
	id int primary key not null AUTO_INCREMENT,
	user_id int  not null,
	role_id int  not null,
	created timestamp not null default now(),
	ended timestamp ,
	foreign key (role_id) references role(id) on delete cascade,
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;

