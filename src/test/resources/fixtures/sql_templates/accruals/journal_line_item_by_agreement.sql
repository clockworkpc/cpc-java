SELECT DISTINCT
    jli.id,
    jli.journal_id,
    jli.vendor_number,
    jli.line_item_type,
    jli.gl_number,
    jli.accrual_finyear,
    jli.accrual_period,
    jli.accrual_type,
    jli.company_code,
    jli.retailer_location_id,
    jli.retailer_division_id,
    jli.retailer_department_id,
    jli.retailer_category_id,
    jli.retailer_product_id,
    jli.description,
    jli.amount,
    jli.accrual_week,
    jli.accrual_day,
    jli.quantity
FROM
    RETAILER_DEPLOYMENT.journal_line_item jli
        JOIN
    RETAILER_DEPLOYMENT.journal_accrual_result jar ON jar.journal_id = jli.journal_id
        JOIN
    RETAILER_DEPLOYMENT.journal j ON j.id = jar.journal_id
        JOIN
    RETAILER_DEPLOYMENT.accrual_result ar ON ar.id = jar.accrual_result_id
WHERE
    j.journal_type = 'accrual'
        AND ar.rule_id IN (SELECT
            r.id
        FROM
            rule r
        WHERE
            r.agreement_id = AGREEMENT_ID)
GROUP BY jli.id , jli.journal_id , jli.vendor_number , jli.line_item_type , jli.gl_number , jli.accrual_finyear , jli.accrual_period , jli.accrual_type , jli.company_code , jli.retailer_location_id , jli.retailer_division_id , jli.retailer_department_id , jli.retailer_category_id , jli.retailer_product_id , jli.description , jli.amount , jli.accrual_week , jli.accrual_day , jli.quantity
ORDER BY jli.id , jli.journal_id , jli.vendor_number , jli.line_item_type , jli.gl_number , jli.accrual_finyear , jli.accrual_period , jli.accrual_type , jli.company_code , jli.retailer_location_id , jli.retailer_division_id , jli.retailer_department_id , jli.retailer_category_id , jli.retailer_product_id , jli.description , jli.amount , jli.accrual_week , jli.accrual_day , jli.quantity
