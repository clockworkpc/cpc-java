SELECT
    r.id as 'rule_id',
    r.agreement_id as 'agreement_id',
    r.rule_type as 'rule_type',
    r.comment as 'rule_description'
FROM
    RETAILER_DEPLOYMENT.rule r
WHERE
    r.agreement_id = AGREEMENT_ID
