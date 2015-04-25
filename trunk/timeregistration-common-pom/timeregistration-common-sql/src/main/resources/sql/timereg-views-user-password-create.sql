create or replace view vw_user_password  as
SELECT 
  `timereg`.`password`.`password`,
   `timereg`.`password`.`created` as password_created,
  `timereg`.`user`.`id` as user_id, 
  `timereg`.`user`.`created` as user_created, 
  `timereg`.`user`.`login`,
  `timereg`.`user`.`name`, 
  `timereg`.`user_password_link`.`created` as link_created,
  `timereg`.`user_password_link`.`ended` as link_ended
  FROM
       `timereg`.`user_password_link` JOIN `timereg`.`user` ON `timereg`.`user_password_link`.`user_id` = `timereg`.`user`.`id` JOIN `timereg`.`password` ON `timereg`.`user_password_link`.`password_id` = `timereg`.`password`.`id`;

       
       
create or replace view users as 
   select u.login as username, p.password as password, u.enabled as enabled 
   from user u, password p, user_password_link link 
   where u.id = link.user_id and p.id = link.password_id and link.ended = 0;

   
create or replace view vw_user_role as
select u.login as username, r.role as role 
from user u, role r, user_role_link as link
where u.id = link.user_id and r.id = link.role_id and link.ended = 0;
