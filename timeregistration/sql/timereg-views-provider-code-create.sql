create or replace view vw_provider_code as
SELECT provider.id as provider_id, provider.name as provider_name, provider_code.code as provider_code, provider_code.name as provider_code_name, provider_code.id as provider_code_id
  FROM `timereg`.`provider`, `timereg`.`provider_code`
  WHERE provider.id = provider_code.provider_id;