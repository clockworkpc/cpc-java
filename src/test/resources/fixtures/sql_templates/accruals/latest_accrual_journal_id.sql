SELECT DISTINCT
    j.id
FROM
    RETAILER_DEPLOYMENT.journal_line_item jli
        JOIN
    RETAILER_DEPLOYMENT.journal j ON j.id = jli.journal_id
WHERE
    j.journal_type = 'ACCRUAL'
        AND jli.accrual_day = (SELECT
            MAX(accrual_day)
        FROM
            RETAILER_DEPLOYMENT.journal_line_item);            
