

create table registration_item (
	id int primary key AUTO_INCREMENT,
	user_id int  not null,
	provider_account_id int  not null,
	client_project_id int not null,
	created timestamp not null default now(),
	is_active enum ('Y','N') not null default 'y',
	ended timestamp null ,
	name varchar(60)  not null,
	foreign key (user_id) references user(id) on delete cascade,
	foreign key (client_project_id) references client_project(id) on delete cascade,
	foreign key (provider_account_id) references provider_account(id) on delete cascade
) engine innodb;
