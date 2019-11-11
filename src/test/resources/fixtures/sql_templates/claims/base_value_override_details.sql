SELECT
trim(concat_ws('-', cr.rule_id, c.id, r.rule_type_id, cc.product_id, cc.location_id )) as 'HELPER',

	-- Identifiers

	cr.rule_id,
	c.id as 'claim_id',
	cr.id AS 'claim_result_id',
	cc.id AS 'claim_component_id',
	cr.claim_tag_id,
    cc.claim_component_type,
    cc.product_id,
    cc.location_id,
    cc.company_id,
    c.user_id,

    -- Main Results

    cr.driver_amount,
    cr.amount,
    cc.apportioned_amount,
    cr.driver_gst,
    cr.gst,
    cc.apportioned_gst,
    c.override_amount,
    c.override_gst,
    c.override_reason,
    c.recalculated,
    c.value_override,

    -- Period Values

    cc.period_spend,
    cc.period_gst,
    cc.quantity,

    -- Cumulative Values

    cr.cumulative_driver_amount,
    cr.cumulative_claim_amount,
    cc.cumulative_apportioned_amount,

	cr.cumulative_driver_gst,
    cr.cumulative_claim_gst,
    cc.cumulative_apportioned_gst,

    cc.cumulative_spend,
    cc.cumulative_quantity,
    cc.cumulative_pack_size,

    -- Uncapped Values

    cr.uncapped_amount,
    cr.uncapped_cumulative_claim_amount,
	cr.uncapped_gst,
    cr.uncapped_cumulative_claim_gst,

    -- Other Values

    cr.annualized_amount,
    cr.moving_annual_target_amount,
    cr.admin_fee,
    cr.admin_fee_gst

FROM
    RETAILER_DEPLOYMENT.claim_result cr
        JOIN
    rule r ON r.id = cr.rule_id
        JOIN
    agreement a ON a.id = r.agreement_id
        JOIN
    claim c ON c.claim_result_id = cr.id
        JOIN
    claim_component cc ON cc.claim_id = c.id
WHERE
    a.id = AGREEMENT_ID

    order by cr.rule_id, cr.id
