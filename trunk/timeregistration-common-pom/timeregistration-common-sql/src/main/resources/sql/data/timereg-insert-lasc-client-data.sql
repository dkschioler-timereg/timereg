
set @system_user_id = 1;
set @system_client_id = 1;
set @system_client_stop_id = 1;
set @system_client_id = 1;
set @system_provider_stop_id = 1;
set @system_registration_item_id = 1;
set @role_admin_id=1;


set @lasc_user_id = 100;
set @lasc_password_id = 100;
set @lasc_accenture_id = 100;
set @lasc_tryg_id = 100;
set @lasc_nordea_id = 101;


insert into client(id, user_id, name) values (@lasc_tryg_id, @lasc_user_id, 'Tryg');
insert into client(id, user_id, name) values (@lasc_nordea_id, @lasc_user_id, 'Nordea');


insert into client_project (id, user_id, client_id, name,code, is_active) values (1,100,100,'CAREER /MENTORING/ANNUAL','975X00','N');
insert into client_project (id, user_id, client_id, name,code, is_active) values (1,100,100,'Job Search / Outplacement','A304005','N');
insert into client_project (id, user_id, client_id, name,code, is_active) values (3,100,100,'New Hire Orientation','A302505','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (4,100,100,'Unassigned Time','A991005','N');
insert into client_project (id, user_id, client_id, name,code, is_active) values (5,100,100,'-','TRAINING','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (6,100,100,'Nord Req Meetings','ANXBZ001','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (7,100,100,'Child\'s Sick Day','955X03','N');
insert into client_project (id, user_id, client_id, name,code, is_active) values (8,100,100,'FERIE / VACATION','900X00','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (9,100,100,'FLEXLEAVE','962X00','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (10,100,100,'HELLIGDAG / PUBLIC HOLIDAY','970X00','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (11,100,100,'ILLNESS','950X00','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (12,100,100,'Jury Duty','992X01','N');
insert into client_project (id, user_id, client_id, name,code, is_active) values (13,100,100,'Manager Days','955X04','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (14,100,100,'MILITARY TRAINING','965X00','N');
insert into client_project (id, user_id, client_id, name,code, is_active) values (15,100,100,'OTHER APPROVED ABSENCE','955X00','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (16,100,100,'OVERTIME TAKEN','984X00','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (17,100,100,'PARENT LEAVE','917X00','N');
insert into client_project (id, user_id, client_id, name,code, is_active) values (18,100,100,'Unpaid Absence','994X01','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (19,100,100,'Central Training - Participant','A301015','Y');
insert into client_project (id, user_id, client_id, name,code, is_active) values (20,100,100,'Req WBS Central Training - Participant','ANJPK003','Y');


	
















