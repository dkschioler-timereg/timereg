create or replace view vw_user_registration_item as
select 
	u.id as user_id, u.login as user_login, 
	item.id as item_id, item.project_name as item_project_name, item.item_name as item_item_name, cc.client_name, cc.client_code, cc.client_code_name,
    pc.provider_name, pc.provider_code, pc.provider_code_name
from user u, user_registration_item_link link, registration_item item, vw_client_code cc, vw_provider_code pc
where u.id = link.user_id and link.registration_item_id = item.id and item.client_code_id = cc.client_code_id and item.provider_code_id = pc.provider_code_id
