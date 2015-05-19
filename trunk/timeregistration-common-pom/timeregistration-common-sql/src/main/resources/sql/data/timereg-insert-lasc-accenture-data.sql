
set @lasc_user_id = 100;
set @lasc_accenture_id = 100;

insert into user (id, login, name,is_login_enabled) values (@lasc_user_id,'lasc','Lars Schiøler', 'Y');
insert into user_password(id, user_id, password) values (100, @lasc_accenture_id, 'Abcd1234');
insert into user_role_link(id, user_id, role_id) values (2,@lasc_user_id,1);

insert into provider(id, user_id, name) values (@lasc_accenture_id, @lasc_user_id, 'Accenture');

insert into provider_account (id, user_id, provider_id, name,code, is_active) values (1,@lasc_user_id,@lasc_accenture_id,'CAREER /MENTORING/ANNUAL','975X00','N');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (2,@lasc_user_id,@lasc_accenture_id,'Job Search / Outplacement','A304005','N');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (3,@lasc_user_id,@lasc_accenture_id,'New Hire Orientation','A302505','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (4,@lasc_user_id,@lasc_accenture_id,'Unassigned Time','A991005','N');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (5,@lasc_user_id,@lasc_accenture_id,'-','TRAINING','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (6,@lasc_user_id,@lasc_accenture_id,'Nord Req Meetings','ANXBZ001','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (7,@lasc_user_id,@lasc_accenture_id,'Child\'s Sick Day','955X03','N');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (8,@lasc_user_id,@lasc_accenture_id,'FERIE / VACATION','900X00','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (9,@lasc_user_id,@lasc_accenture_id,'FLEXLEAVE','962X00','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (10,@lasc_user_id,@lasc_accenture_id,'HELLIGDAG / PUBLIC HOLIDAY','970X00','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (11,@lasc_user_id,@lasc_accenture_id,'ILLNESS','950X00','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (12,@lasc_user_id,@lasc_accenture_id,'Jury Duty','992X01','N');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (13,@lasc_user_id,@lasc_accenture_id,'Manager Days','955X04','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (14,@lasc_user_id,@lasc_accenture_id,'MILITARY TRAINING','965X00','N');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (15,@lasc_user_id,@lasc_accenture_id,'OTHER APPROVED ABSENCE','955X00','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (16,@lasc_user_id,@lasc_accenture_id,'OVERTIME TAKEN','984X00','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (17,@lasc_user_id,@lasc_accenture_id,'PARENT LEAVE','917X00','N');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (18,@lasc_user_id,@lasc_accenture_id,'Unpaid Absence','994X01','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (19,@lasc_user_id,@lasc_accenture_id,'Central Training - Participant','A301015','Y');
insert into provider_account (id, user_id, provider_id, name,code, is_active) values (20,@lasc_user_id,@lasc_accenture_id,'Req WBS Central Training - Participant','ANJPK003','Y');

insert into provider_account (id, user_id, provider_id, name, code, is_active) values ( 21, @lasc_user_id, @lasc_accenture_id,'NORDEA BANK AB - Collateral Management - Denmark','ANWVQ004','Y');
insert into provider_account (id, user_id, provider_id, name, code, is_active) values ( 22, @lasc_user_id, @lasc_accenture_id,'NORDEA BANK AB - Enkel PoC - Denmark','ARZS7005','Y');
insert into provider_account (id, user_id, provider_id, name, code, is_active) values ( 23, @lasc_user_id, @lasc_accenture_id,'TRYG FORSIKRING AS - Tryg-AO- Major Enhancements-DK','Z002HU96','Y');
insert into provider_account (id, user_id, provider_id, name, code, is_active) values ( 24, @lasc_user_id, @lasc_accenture_id,'TRYG FORSIKRING AS - Tryg-AO- Minor Enhancements-DK','Z002HV05','Y');
insert into provider_account (id, user_id, provider_id, name, code, is_active) values ( 25, @lasc_user_id, @lasc_accenture_id,'TRYG FORSIKRING AS - Tryg-AO-AM-DK','Z002EN01','Y');
insert into provider_account (id, user_id, provider_id, name, code, is_active) values ( 26, @lasc_user_id, @lasc_accenture_id,'TRYG FORSIKRING AS - Tryg-PMO-AM-DK','Z002EM95','Y');
insert into provider_account (id, user_id, provider_id, name, code, is_active) values ( 27, @lasc_user_id, @lasc_accenture_id,'TRYG FORSIKRING AS - Tryg-SAP FSCD-DK','Z002EM76','Y');


insert into client(id, user_id, name) values (100, @lasc_user_id, 'Tryg Forsikring AS');
insert into client(id, user_id, name) values (101, @lasc_user_id, 'Nordea Bank AB');

insert into client_project (id,user_id, client_id, name, code, is_active) values (2,100,100,'AD 00102 SuccessFactor 2015 AE Major-Minor-Fast Track - SOA Services R1 ','HP-14-3006-02','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (3,100,100,'AD 507: Email fra CMS til Siebel 2015 AE Major-Minor-Fast Track - SOA Services R1 ','HP-14-3010-02','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (4,100,100,'Development 2015 AE Major-Minor-Fast Track - Infrastructure ','HP-14-3012-02','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (5,100,100,'SOS VPN change 2015 AE Major-Minor-Fast Track - SOA Services R1 ','HP-14-3003-02','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (6,100,100,'Service Request - SOA Services  and Integration AM ','HP-14-3015-01','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (7,100,100,'2015 AE Major-Minor-Fast Track - Web and Mobile R1 ','HP-14-3011-02','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (8,100,100,'Development - Project - DMR 2015 AE Major-Minor-Fast Track - Web and Mobile R1 ','HP-14-3001-02','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (9,100,100,'Development 2015 Project - Digital Customer NO ','HP-12-1252-11','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (10,100,100,'2015 Project - Migration of Moderna Integrations ','HP-15-4040-04','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (11,100,100,'MCN Email Service 2015 Project - My Company Notification ','HP-14-1252-11 002','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (12,100,100,'Development 2015 Project - Nordea Program ','HP-12-1261-00','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (13,100,100,'Windows Server Upgrade - Startup - Feb 2015 2015 Project - Windows Server Upgrade ','HP-15-4040-03','Y');

insert into client_project (id,user_id, client_id, name, code, is_active) values (14,100,101,'Core Banking replacement','CBP-2015','Y');
insert into client_project (id,user_id, client_id, name, code, is_active) values (15,100,101,'Collateral & Assets','NCS-2015','Y');











