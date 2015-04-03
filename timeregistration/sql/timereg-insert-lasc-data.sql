
set @system_user_id = 1;
set @system_client_id = 1;
set @system_client_stop_id = 1;
set @system_provider_id = 1;
set @system_provider_stop_id = 1;
set @system_registration_item_id = 1;

insert into user (id, login, name, is_login_enabled) values (@system_user_id,'system','system','N');
insert into role (id, role) values (1, 'ROLE_ADMIN');
insert into role (id, role) values (2, 'ROLE_USER');
insert into role (id, role) values (3, 'ROLE_MONITOR');
insert into role (id, role) values (4, 'ROLE_REPORT');


insert into client (id, name) values (@system_client_id,'system');
insert into client_code (id, client_id, code, name) values  (@system_client_stop_id,@system_client_id,'-1', 'stop');
insert into provider (id,name) values (@system_provider_id,'system');
insert into provider_code (id, provider_id, code, name) values  (@system_provider_stop_id,@system_provider_id, '-1', 'stop');

insert into registration_item (id, provider_code_id, client_code_id, project_name, item_name) values (@system_registration_item_id,@system_provider_stop_id,@system_client_stop_id,'system','stop');

set @lasc_user_id = 100;
set @lasc_password_id = 100;

insert into user (id, login, name, is_login_enabled) values (@lasc_user_id,'lasc','Lars Schiøler','Y');
insert into password (id, password) values (@lasc_password_id, 'Abcd1234');
insert into user_role_link (user_id, role_id) values (@lasc_user_id,1);
insert into user_role_link (user_id, role_id) values (@lasc_user_id,2);
insert into user_password_link (user_id, password_id) values (@lasc_user_id,@lasc_password_id);
insert into user_registration_item_link (user_id, registration_item_id) values (@lasc_user_id,@system_registration_item_id);


insert into provider (id, name) values (1, 'Accenture');
insert into client (id, name) values (1, 'Tryg');
insert into client (id, name) values (2, 'Nordea');












