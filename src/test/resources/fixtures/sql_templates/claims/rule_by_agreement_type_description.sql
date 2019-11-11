SELECT
    r.id as 'rule_id',
    r.agreement_id as 'agreement_id',
    r.rule_type as 'rule_type',
    r.comment as 'rule_description',
    r.claim_frequency as 'rule_claim_frequency'
FROM
    RETAILER_DEPLOYMENT.rule r
WHERE
    r.agreement_id = AGREEMENT_ID
        AND r.rule_type = 'RULE_TYPE'
        AND r.comment = 'RULE_DESCRIPTION'
