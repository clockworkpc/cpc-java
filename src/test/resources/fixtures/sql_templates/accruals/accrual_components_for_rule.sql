SELECT
arc.company_id,
arc.location_id,
arc.brand_id,
arc.division_id,
arc.department_id,
arc.category_id,
arc.product_id,
arc.spend_amount,
arc.gst_amount,
arc.apportioned_claim_amount,
arc.quantity,
arc.transaction_id
FROM
    RETAILER_DEPLOYMENT.accrual_result_component arc
        JOIN
    RETAILER_DEPLOYMENT.accrual_result ar ON ar.id = arc.accrual_result_id
WHERE
    ar.rule_id IN (RULE_ID)
ORDER BY arc.company_id , arc.location_id , arc.brand_id , arc.division_id , arc.department_id , arc.category_id , arc.product_id , arc.spend_amount , arc.gst_amount , arc.apportioned_claim_amount , arc.quantity, arc.transaction_id;
