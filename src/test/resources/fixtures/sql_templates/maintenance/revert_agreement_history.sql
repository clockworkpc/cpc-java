-- 2. Revert Agreements to Pending and revert rule processing
-- --------------------------------------------------------------------------------------------------
SELECT RETAILER_DEPLOYMENT.database();
-- CALL usp_agreement_purge_ghost( 1 );
-- Agreement Statuses
SELECT
CASE WHEN retailer_vendor_id IS NULL THEN 'GHOST' ELSE 'NORMAL' END AS is_ghost ,
`status` ,
datasource ,
COUNT(*)
FROM
agreement
GROUP BY
CASE WHEN retailer_vendor_id IS NULL THEN 'GHOST' ELSE 'NORMAL' END ,
`status` ,
datasource;
-- SELECT * FROM agreement WHERE `status` NOT IN ( 'PENDING' , 'APPROVED' );
-- SELECT * FROM agreement WHERE `status` <> 'PENDING';
UPDATE agreement SET `status` = 'PENDING' WHERE retailer_vendor_id IS NOT NULL;
UPDATE
rule R
INNER JOIN
agreement AG ON
R.agreement_id = AG.id
SET
R.last_accrual_date = NULL ,
R.last_claim_date = NULL ,
R.next_accrual_date = NULL ,
R.next_claim_date = NULL ,
R.last_period_count = 0 ,
R.condition_status = CASE WHEN R.condition_status IN ( 'MET' , 'NOT_MET' ) THEN 'PENDING' ELSE R.condition_status END
WHERE
AG.retailer_vendor_id IS NOT NULL;
TRUNCATE TABLE rule_condition_history;
-- --------------------------------------------------------------------------------------------------
-- 3. Revert agreement history - last entry before APPROVED
-- --------------------------------------------------------------------------------------------------
/*
SELECT
AH.*
FROM
agreement_history AH
INNER JOIN
(
SELECT agreement_id , MIN( id ) AS approved_agreement_history_id
FROM agreement_history
WHERE operation = 'APPROVED'
GROUP BY agreement_id
) APPR ON
APPR.agreement_id = AH.agreement_id AND
AH.id >= APPR.approved_agreement_history_id;
*/
-- todo: revert attachments, notes, comments etc where date >= approve date
DELETE AH.*
FROM
agreement_history AH
INNER JOIN
(
SELECT agreement_id , MIN( id ) AS approved_agreement_history_id
FROM agreement_history
WHERE operation = 'APPROVED'
GROUP BY agreement_id
) APPR ON
APPR.agreement_id = AH.agreement_id AND
AH.id >= APPR.approved_agreement_history_id;
SELECT * FROM attachment WHERE filename LIKE 'APPROVED%';
SELECT * FROM agreement_attachment;
DELETE FROM agreement_attachment;
DELETE FROM attachment WHERE filename LIKE 'APPROVED%';
SELECT * FROM agreement_comment;
SELECT * FROM agreement_note;
/*
SELECT * FROM agreement_history;
SELECT *
FROM
agreement_note AN
INNER JOIN
(
SELECT agreement_id , MAX( audit_date ) AS max_audit_date
FROM agreement_history
GROUP BY agreement_id
) AH ON
AH.agreement_id = AN.agreement_id AND
AN.`date` >= AH.max_audit_date;
*/
-- --------------------------------------------------------------------------------------------------
-- 4. Delete Journals
-- --------------------------------------------------------------------------------------------------
TRUNCATE TABLE journal_line_item;
DELETE FROM journal_header;
TRUNCATE TABLE journal_download;
alter table journal_claim drop foreign key fk_journal_claim_journal_id;
DELETE FROM journal;
ALTER TABLE journal_line_item AUTO_INCREMENT=1;
ALTER TABLE journal_header AUTO_INCREMENT=1;
ALTER TABLE journal_download AUTO_INCREMENT=1;
ALTER TABLE journal AUTO_INCREMENT=1;


-- --------------------------------------------------------------------------------------------------
-- 5. Delete Claims
-- --------------------------------------------------------------------------------------------------
TRUNCATE TABLE claim_history;
TRUNCATE TABLE claim_component;
TRUNCATE TABLE claim_attachment;

ALTER TABLE agreement_attachment drop foreign key fk_agreement_attachment_id;
ALTER TABLE claim_attachment drop foreign key fk_claim_attachment_id;
TRUNCATE TABLE attachment;

ALTER TABLE agreement_attachment
ADD CONSTRAINT `fk_agreement_attachment_id`
  FOREIGN KEY (`id`)
  REFERENCES `attachment` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE claim_attachment
ADD CONSTRAINT `fk_claim_attachment_id`
  FOREIGN KEY (`id`)
  REFERENCES `attachment` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

DELETE FROM journal_claim;

ALTER TABLE journal_claim
ADD CONSTRAINT `fk_journal_claim_journal_id`
  FOREIGN KEY (`journal_id`)
  REFERENCES `journal` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

DELETE FROM claim;
DELETE FROM recosting_queue_entry;
DELETE FROM claim_batch;
TRUNCATE TABLE claim_result_merch_trans_difot;
DELETE FROM claim_result;
-- Truncate if exists
SET @sql_temp := CASE
WHEN EXISTS ( SELECT * FROM INFORMATION_SCHEMA.`TABLES` WHERE `TABLE_SCHEMA`=database() AND `TABLE_NAME`='claim_tag_ap_trans' )
THEN 'TRUNCATE TABLE claim_tag_ap_trans;'
ELSE 'SET @dummy:=1;'
END;
PREPARE stmnt_Temp FROM @sql_temp;
EXECUTE stmnt_Temp;
DEALLOCATE PREPARE stmnt_Temp;
-- Truncate if exists
SET @sql_temp := CASE
WHEN EXISTS ( SELECT * FROM INFORMATION_SCHEMA.`TABLES` WHERE `TABLE_SCHEMA`=database() AND `TABLE_NAME`='claim_tag_merch_trans' )
THEN 'TRUNCATE TABLE claim_tag_merch_trans;'
ELSE 'SET @dummy:=1;'
END;
PREPARE stmnt_Temp FROM @sql_temp;
EXECUTE stmnt_Temp;
DEALLOCATE PREPARE stmnt_Temp;
-- Truncate if exists
SET @sql_temp := CASE
WHEN EXISTS ( SELECT * FROM INFORMATION_SCHEMA.`TABLES` WHERE `TABLE_SCHEMA`=database() AND `TABLE_NAME`='claim_tag_sales_trans' )
THEN 'TRUNCATE TABLE claim_tag_sales_trans;'
ELSE 'SET @dummy:=1;'
END;
PREPARE stmnt_Temp FROM @sql_temp;
EXECUTE stmnt_Temp;
DEALLOCATE PREPARE stmnt_Temp;
TRUNCATE TABLE claim_tag_history;
DELETE FROM claim_tag;
ALTER TABLE claim AUTO_INCREMENT=1;
ALTER TABLE claim_batch AUTO_INCREMENT=1;
ALTER TABLE claim_result AUTO_INCREMENT=1;
ALTER TABLE claim_tag AUTO_INCREMENT=1;
-- --------------------------------------------------------------------------------------------------
-- 6. Delete Accrual Results and Income Recognition
-- --------------------------------------------------------------------------------------------------
TRUNCATE TABLE accrual_result_component;
DELETE FROM accrual_result;
DELETE FROM average_accrual;
DELETE FROM income_recognition;
ALTER TABLE accrual_result AUTO_INCREMENT=1;
ALTER TABLE average_accrual AUTO_INCREMENT=1;
ALTER TABLE income_recognition AUTO_INCREMENT=1;
-- --------------------------------------------------------------------------------------------------
-- Retailer
-- --------------------------------------------------------------------------------------------------
UPDATE retailer
SET
latest_ap_trans_data_date = NULL ,
latest_merch_trans_data_date = NULL ,
latest_sales_trans_data_date = NULL ,
reference_data_import_date = NULL ,
next_claim_number = 1;
-- UPDATE job SET `status` = 'COMPLETED' WHERE `status` = 'RUNNING';
-- SELECT * FROM job;
-- ---------------------------------------------------------------------------------------------------
-- 7. Update Agreement
-- ---------------------------------------------------------------------------------------------------
UPDATE agreement
SET
cease_date = NULL;
-- ---------------------------------------------------------------------------------------------------
-- 8. Update Rules
-- ---------------------------------------------------------------------------------------------------
UPDATE rule
SET
reconciled =0;

-- ---------------------------------------------------------------------------------------------------
-- 9. Delete all Journal logs
-- ---------------------------------------------------------------------------------------------------
truncate table journal_generation_log;
-- ----------------------------------------------------------------------------------------------------
-- 10. Delete replicated agreements
-- ----------------------------------------------------------------------------------------------------
DELETE from agreement
where id in (select child_agreement_id from agreement_copy);
truncate agreement_copy;
-- ----------------------------------------------------------------------------------------------------
-- 11. Delete roll over agreements
-- ----------------------------------------------------------------------------------------------------
delete from tier
where rule_id in (select id from rule where agreement_id in (select id from agreement where parent_agreement_id is not null));
delete from rule_gl_account
where rule_id in (select id from rule where agreement_id in (select id from agreement where parent_agreement_id is not null));
-- alter table rule_attribute drop foreign key fk_rule_attribue_rule_id;
delete from item
where rule_id in (select id from rule where agreement_id in (select id from agreement where parent_agreement_id is not null));
delete from transaction_exemptions
where rule_id in (select id from rule where agreement_id in (select id from agreement where parent_agreement_id is not null));
/*delete from claim_result
where rule_id in (select id from rule where agreement_id in (select id from agreement where parent_agreement_id is not null));
delete from claim
where claim_result_id in (select id from claim_result where (select id from rule where agreement_id in (select id from agreement where parent_agreement_id is not null)));*/
-- alter table tier_report_result drop foreign key tier_report_result_rule_id;
delete from rule_product
where rule_id in (select id from rule where agreement_id in (select id from agreement where parent_agreement_id is not null));
delete from rule_attribute
where rule_id in (select id from rule where agreement_id in (select id from agreement where parent_agreement_id is not null));
delete from rule
where agreement_id in (select id from agreement where parent_agreement_id is not null);
delete from agreement_history
where agreement_id in (select id from agreement where parent_agreement_id is not null);
delete from attachment
where agreement_id in (select id from agreement where parent_agreement_id is not null);
delete from agreement_note
where agreement_id in (select id from agreement where parent_agreement_id is not null);
-- alter table tier_report_result drop foreign key tier_report_result_agreement_id;
delete from tier_report_result;
delete from agreement
where parent_agreement_id is not null;
update agreement
set autoextended =0;
-- --------------------------------------------------------------------------------------------------
-- 12. Delete Agreement Transfer Table
-- --------------------------------------------------------------------------------------------------
delete from agreement_transfer
