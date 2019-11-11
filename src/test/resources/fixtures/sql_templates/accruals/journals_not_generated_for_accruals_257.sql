SELECT
    COUNT(ar.id) as 'null_journals'
FROM
    RETAILER_DEPLOYMENT.accrual_result ar
WHERE
    ar.journal_id IS NULL
        AND ar.end_date < (SELECT
            MAX(accrual_day)
        FROM
            journal
        WHERE
            journal_type = 'ACCRUAL');
