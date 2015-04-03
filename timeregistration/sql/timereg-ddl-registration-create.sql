
create table registration_item (
	id int primary key not null AUTO_INCREMENT,	
	provider_code_id int not null,
	client_code_id int ,
	created timestamp not null default now(),
	ended timestamp ,
	project_name varchar(120)  not null,
	item_name varchar(120)  not null,
	foreign key (provider_code_id) references provider_code(id) on delete cascade,
	foreign key (client_code_id) references client_code(id) on delete cascade	
) engine innodb;

create table user_registration_item_link (
	id int primary key not null AUTO_INCREMENT,
	user_id int  not null,
	registration_item_id int  not null,
	created timestamp not null default now(),
	ended timestamp ,
	foreign key (user_id) references user(id) on delete cascade,
	foreign key (registration_item_id) references registration_item(id) on delete cascade
) engine innodb;

create table registration_event (
	id int primary key not null AUTO_INCREMENT,
	user_id int not null,
	registration_item_id int not null,	
	created timestamp not null default now(),
	event_time timestamp not null ,
	comments varchar(120)  not null,	
	foreign key (registration_item_id) references registration_item(id) on delete cascade,
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;

create table registration_submitted (
	id int primary key not null AUTO_INCREMENT,
	user_id int not null,
	registration_item_id int not null,	
	created timestamp not null default now(),
	amount decimal (5,1) not null,
	comments varchar(120) ,	
	foreign key (registration_item_id) references registration_item(id) on delete cascade,
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;

