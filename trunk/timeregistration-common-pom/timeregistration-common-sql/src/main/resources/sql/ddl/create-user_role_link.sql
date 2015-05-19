
create table user_role_link(
	id int primary key AUTO_INCREMENT,
	user_id int  not null,
	role_id int  not null,
	created timestamp not null default now(),
	ended timestamp null,
	is_active enum ('Y','N') not null default 'y',
	foreign key (role_id) references role(id) on delete cascade,
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;

