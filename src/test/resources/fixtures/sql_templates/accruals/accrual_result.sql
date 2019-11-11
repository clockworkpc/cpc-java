SELECT
    ar.id as 'accrual_result_id',
    ar.accrued_claim_amount,
    ar.accrued_spend_amount,
    ar.accrued_gst_amount,
    ar.cumulative_accrued_claim_amount
FROM
    RETAILER_DEPLOYMENT.accrual_result ar
WHERE
    ar.id IN (ACCRUAL_RESULT_ID);
