SELECT
    ar.accrued_claim_amount,
    ar.accrued_spend_amount,
    ar.accrued_gst_amount,
    ar.cumulative_accrued_claim_amount
FROM
    RETAILER_DEPLOYMENT.accrual_result ar
        JOIN
    rule r ON r.id = ar.rule_id
        JOIN
    agreement a ON a.id = r.agreement_id
WHERE
    a.id IN (AGREEMENT_ID);
