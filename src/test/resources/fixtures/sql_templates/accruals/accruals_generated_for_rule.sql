SELECT
    COUNT(ar.id) as 'accruals_generated'
FROM
    RETAILER_DEPLOYMENT.accrual_result ar
WHERE
    rule_id IN (RULE_ID)
