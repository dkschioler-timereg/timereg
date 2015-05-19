create or replace view vw_user_password  as
SELECT 
  `timereg`.`user_password`.`password`,
   `timereg`.`user_password`.`created` as password_created,
  `timereg`.`user`.`id` as user_id, 
  `timereg`.`user`.`created` as user_created, 
  `timereg`.`user`.`login`,
  `timereg`.`user`.`name`
FROM
  `timereg`.`user_password` JOIN `timereg`.`user` ON `timereg`.`user_password`.`user_id` = `timereg`.`user`.`id` ;

       
create or replace view users as 
   select u.login as username, p.password as password, u.enabled as enabled 
   from user u, user_password p 
   where u.id = p.user_id and p.ended = 0;

   
create or replace view vw_user_role as
select u.login as username, r.role as role 
from user u, role r, user_role_link as link
where u.id = link.user_id and r.id = link.role_id and link.ended = 0;
