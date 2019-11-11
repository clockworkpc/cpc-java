SELECT
    ar.id as 'accrual_result_id',
    ar.accrued_claim_amount,
    ar.accrued_spend_amount,
    ar.accrued_gst_amount,
    ar.cumulative_accrued_claim_amount,
    ar.accrual_result_type
FROM
    RETAILER_DEPLOYMENT.accrual_result ar
WHERE
    ar.rule_id IN (RULE_ID);
