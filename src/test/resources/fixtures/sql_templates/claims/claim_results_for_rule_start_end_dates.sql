SELECT
    a.id as 'agreement_id',
    cr.rule_id as 'rule_id',
    cr.id as 'claim_id',
    cr.start_date_time,
    cr.end_date_time,
    cr.amount,
    cr.gst,
    cr.driver_amount,
    cr.driver_gst,
    cr.cumulative_claim_amount,
    cr.cumulative_claim_gst
FROM
    RETAILER_DEPLOYMENT.agreement a
        JOIN
    RETAILER_DEPLOYMENT.rule r ON r.agreement_id = a.id
        JOIN
    RETAILER_DEPLOYMENT.claim_result cr ON cr.rule_id = r.id
WHERE
    r.id IN (RULE_ID)
    AND cr.start_date_time = 'START_DATE_TIME'
    AND cr.end_date_time = 'END_DATE_TIME'
    ORDER BY cr.start_date_time
