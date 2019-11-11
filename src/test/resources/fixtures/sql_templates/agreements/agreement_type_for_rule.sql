SELECT
    a.agreement_type
FROM
    RETAILER_DEPLOYMENT.rule r
        JOIN
    agreement a ON a.id = r.agreement_id
WHERE
    r.id = RULE_ID
