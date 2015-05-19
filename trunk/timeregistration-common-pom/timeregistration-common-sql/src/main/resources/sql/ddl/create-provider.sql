
create table provider (
	id int primary key AUTO_INCREMENT,
	user_id int  not null,
	name varchar(60)  not null,
	created timestamp not null default now(),
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;




