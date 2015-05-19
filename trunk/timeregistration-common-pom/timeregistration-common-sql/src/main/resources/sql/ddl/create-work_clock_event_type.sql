create table work_clock_event_type (
	id int primary key AUTO_INCREMENT ,
	created timestamp not null default now(),
	event_type varchar(60)  not null
) engine innodb;

