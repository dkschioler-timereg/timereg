create or replace view vw_user_registration as
select 
	u.id as user_id, u.login as user_login, 
	item.id as item_id,	item.project_name as item_project_name,	item.item_name as item_item_name, 
	cc.client_name, cc.client_code, cc.client_code_name,
    pc.provider_name, pc.provider_code, pc.provider_code_name,
    event.id as event_id, event.event_time, event.comments
from user u, registration_item item, vw_client_code cc, vw_provider_code pc, registration_event event
where 
    u.id = event.user_id  
    and item.client_code_id = cc.client_code_id and item.provider_code_id = pc.provider_code_id
    and event.registration_item_id = item.id
