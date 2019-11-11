SELECT
    a.start_date AS 'agreement_start_date',
    a.end_date AS 'agreement_end_date'
FROM
    RETAILER_DEPLOYMENT.agreement a
WHERE
    a.id = AGREEMENT_ID
