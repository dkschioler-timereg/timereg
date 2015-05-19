
create table registration_event (
	id int primary key AUTO_INCREMENT,
	user_id int not null,
	registration_item_id int not null,
	work_clock_event_id int not null,
	created timestamp not null default now(),
	event_time timestamp not null ,
	comments varchar(220),	
	foreign key (registration_item_id) references registration_item(id) on delete cascade,
	foreign key (work_clock_event_id) references work_clock_event(id) on delete cascade,
	foreign key (user_id) references user(id) on delete cascade
) engine innodb;
