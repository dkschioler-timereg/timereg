create or replace view vw_client_code as
SELECT client.id as client_id, client.name as client_name, client_code.code as client_code, client_code.name as client_code_name, client_code.id as client_code_id
  FROM `timereg`.`client`, `timereg`.`client_code`
  WHERE client.id = client_code.client_id;
  
       
       