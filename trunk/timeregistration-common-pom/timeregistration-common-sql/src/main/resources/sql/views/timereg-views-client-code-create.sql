create or replace view vw_client_work as 
select u.id as u_id, c.id as c_id, cp.id as cp_id, ct.id as ct_id, u.login, c.name as client_name, cp.name as clientproject_name, cp.code as clientproject_code, ct.name as clienttask_name 
from ((client c inner join user u on c.user_id = u.id)inner join client_project cp on c.id=cp.client_id) left join client_task ct on cp.id = ct.client_project_id;