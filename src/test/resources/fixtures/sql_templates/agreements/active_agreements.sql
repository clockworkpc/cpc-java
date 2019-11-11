SELECT
    *
FROM
    RETAILER_DEPLOYMENT.agreement
WHERE
    agreement_type = 'rebate'
        AND status IN ('approved' , 'expiring')
ORDER BY `status` , `id`
