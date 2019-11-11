SELECT
    r.start_date AS 'rule_start_date',
    r.end_date AS 'rule_end_date',
    a.start_date AS 'agreement_start_date',
    a.end_date AS 'agreement_end_date',
    r.claim_frequency AS 'frequency'
FROM
    RETAILER_DEPLOYMENT.rule r
        JOIN
    agreement a ON a.id = r.agreement_id
WHERE
    r.id = RULE_ID
