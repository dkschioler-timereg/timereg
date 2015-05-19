


create table registration_submitted (
	id int primary key AUTO_INCREMENT,
	user_id int not null,
	registration_item_id int not null,	
	created timestamp not null default now(),
	amount decimal (5,1) not null,
	comments varchar(120) ,	
	foreign key (registration_item_id) references registration_item(id) on delete cascade,
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;

