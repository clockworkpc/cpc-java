SELECT
    MAX(id) AS 'agreement_id'
FROM
    RETAILER_DEPLOYMENT.agreement
WHERE
    `status` NOT IN ('DELETED')
        AND `status` IN (AGREEMENT_STATUS)
        AND agreement_type = AGREEMENT_TYPE;
