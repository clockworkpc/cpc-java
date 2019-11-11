SELECT
    TRIM(CONCAT_WS('-', cr.rule_id, cr.id)) AS 'HELPER',
		cr.rule_id,
		cr.id AS 'claim_result_id',
		c.id AS 'claim_id',
		ROUND((cr.amount / cr.driver_amount), 4) AS 'driver_amount_ratio',
		ROUND((cr.gst / cr.driver_gst), 4) AS 'driver_gst_ratio',
		ROUND((cr.gst / cr.amount), 4) AS 'gst_rate',
		cr.driver_amount,
		cr.amount,
		cr.driver_gst,
		cr.gst
		c.override_amount,
		c.override_gst,
		c.override_reason,



		-- cr.claim_tag_id,
		-- c.user_id,
    -- cr.cumulative_driver_amount,
    -- cr.cumulative_claim_amount,
    -- cr.cumulative_driver_gst,
    -- cr.cumulative_claim_gst,
    -- cr.uncapped_amount,
    -- cr.uncapped_cumulative_claim_amount,
    -- cr.uncapped_gst,
    -- cr.uncapped_cumulative_claim_gst,
    -- cr.annualized_amount,
    -- cr.moving_annual_target_amount,
    -- cr.admin_fee,
    -- cr.admin_fee_gst
FROM
    claim_result cr
        JOIN
    rule r ON r.id = cr.rule_id
        JOIN
    agreement a ON a.id = r.agreement_id
        JOIN
    claim c ON c.claim_result_id = cr.id
        JOIN
    claim_component cc ON cc.claim_id = c.id
WHERE
    a.id = 202
GROUP BY cr.rule_id , cr.id
ORDER BY cr.rule_id , cr.id
