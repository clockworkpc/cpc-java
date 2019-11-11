SELECT
    COUNT(ar.id) AS 'null_journals'
FROM
    RETAILER_DEPLOYMENT.accrual_result ar
        LEFT JOIN
    journal_accrual_result jar ON jar.accrual_result_id = ar.id
WHERE
    jar.journal_id IS NULL
        AND ar.end_date < (SELECT
            MAX(accrual_day)
        FROM
            RETAILER_DEPLOYMENT.journal
        WHERE
            journal_type = 'ACCRUAL')
