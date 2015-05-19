create table work_clock_event (
	id int primary key AUTO_INCREMENT,
	created timestamp not null default now(),
	user_id int  not null,
	work_clock_event_type_id int  not null,
	foreign key (user_id) references user(id) on delete cascade,
	foreign key (work_clock_event_type_id) references work_clock_event_type(id) on delete cascade
) engine innodb;

