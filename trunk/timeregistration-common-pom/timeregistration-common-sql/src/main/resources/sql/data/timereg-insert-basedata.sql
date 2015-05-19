


insert into user (id, login, name) values (@system_user_id,'system','system');
insert into role (id, role) values (@role_admin_id, 'ROLE_ADMIN');
insert into role (id, role) values (2, 'ROLE_USER');
insert into role (id, role) values (3, 'ROLE_MONITOR');
insert into role (id, role) values (4, 'ROLE_REPORT');
insert into user_role_link (id, user_id, role_id) values (1, @system_user_id,1);

insert into work_clock_event_type (id, event_type) values (1, "stop");
insert into work_clock_event_type (id, event_type) values (2, "start");










