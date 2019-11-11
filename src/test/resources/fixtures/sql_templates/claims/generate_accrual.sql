SELECT
    rr.generate_accrual
FROM
    RETAILER_DEPLOYMENT.rule r
        JOIN
    RETAILER_DEPLOYMENT.retailer_rule rr ON rr.id = r.retailer_rule_id
WHERE
    r.id = RULE_ID
