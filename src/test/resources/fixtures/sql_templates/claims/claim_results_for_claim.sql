SELECT
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
	RETAILER_DEPLOYMENT.claim_result cr
WHERE
    cr.id IN (CLAIM_ID)
