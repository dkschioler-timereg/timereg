
create table user_password (
	id int primary key AUTO_INCREMENT,	
	user_id int  not null,
	created timestamp not null default now(),
	is_active enum ('Y','N') not null default 'y',
	ended timestamp null ,
	password varchar(60)  not null,
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;


