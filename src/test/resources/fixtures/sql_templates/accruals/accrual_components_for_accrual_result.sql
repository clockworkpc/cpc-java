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
arc.quantity
FROM
    RETAILER_DEPLOYMENT.accrual_result_component arc
        JOIN
    accrual_result ar ON ar.id = arc.accrual_result_id
WHERE
    ar.id IN (ACCRUAL_RESULT_ID)
ORDER BY arc.company_id , arc.location_id , arc.brand_id , arc.division_id , arc.department_id , arc.category_id , arc.product_id , arc.spend_amount , arc.gst_amount , arc.apportioned_claim_amount , arc.quantity;
