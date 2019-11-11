SELECT DISTINCT
    ar.id as 'accrual_result_id',
    (ar.accrued_claim_amount - SUM(arc.apportioned_claim_amount)) AS 'accrued_claim_amount_difference',
    (ar.accrued_gst_amount - SUM(arc.gst_amount)) AS 'accrued_gst_amount_difference',
    ar.accrued_claim_amount,
    SUM(arc.apportioned_claim_amount) AS 'accrued_claim_amount_components_sum',
    ar.accrued_gst_amount,
    SUM(arc.gst_amount) AS 'accrued_gst_amount_components_sum'
FROM
    RETAILER_DEPLOYMENT.accrual_result ar
        JOIN
    rule r ON r.id = ar.rule_id
        JOIN
    accrual_result_component arc ON arc.accrual_result_id = ar.id
WHERE
    r.id IN (RULE_ID)
GROUP BY ar.id
ORDER BY ar.id
