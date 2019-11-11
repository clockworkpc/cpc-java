SELECT
    r.id AS 'rule_id',
    cr.id AS 'claim_result_id',
    c.id AS 'claim_id',

    cc.*
FROM
    RETAILER_DEPLOYMENT.claim_component cc
        JOIN
    claim c ON c.id = cc.claim_id
        JOIN
    claim_result cr ON cr.id = c.claim_result_id
        JOIN
    rule r ON r.id = cr.rule_id
WHERE
    r.id IN (RULE_ID)
ORDER BY cc.company_id , cc.location_id , cc.brand_id , cc.division_id , cc.department_id , cc.category_id , cc.product_id , cc.po_number , cc.period_spend , cc.period_gst , cc.quantity , cc.apportioned_amount , cc.apportioned_gst , cc.cumulative_spend , cc.cumulative_gst , cc.cumulative_quantity , cc.cumulative_pack_size , cc.cumulative_apportioned_amount , cc.cumulative_apportioned_gst
