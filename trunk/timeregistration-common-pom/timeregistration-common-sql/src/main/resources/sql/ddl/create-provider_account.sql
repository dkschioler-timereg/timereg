
create table provider_account (
	id int primary key AUTO_INCREMENT,
	user_id int  not null,
	provider_id int  not null,
	created timestamp not null default now(),
	code varchar(20)  not null unique,
	name varchar(60)  not null,
	is_active enum ('Y','N') not null default 'y',
	foreign key (provider_id) references provider(id) on delete cascade,
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;



