SELECT
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
    RETAILER_DEPLOYMENT.journal j ON j.id = jli.journal_id
WHERE
    j.accrual_finyear IN (ACCRUAL_FINYEAR)
    AND j.accrual_period IN (ACCRUAL_PERIOD)
    and j.journal_type = 'ACCRUAL'
ORDER BY jli.journal_id , jli.vendor_number , jli.line_item_type , jli.gl_number , jli.accrual_finyear , jli.accrual_period , jli.accrual_type , jli.company_code , jli.retailer_location_id , jli.retailer_division_id , jli.retailer_department_id , jli.retailer_category_id , jli.retailer_product_id , jli.description , jli.amount , jli.accrual_week , jli.accrual_day , jli.quantity
