       
create or replace view vw_users as 
   select u.login as username, p.password as password, u.enabled as enabled 
   from user u, user_password p 
   where u.id = p.user_id and p.ended = 0;

