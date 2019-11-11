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
