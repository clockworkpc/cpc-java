SELECT
    a.id AS 'agreement_id',
    r.id AS 'rule_id',
    cr.id AS 'claim_result_id',
    c.id AS 'claim_id',
    cc.*
FROM
    RETAILER_DEPLOYMENT.claim_component cc
        JOIN
    claim c ON c.id = cc.claim_id
        JOIN
    claim_result cr ON cr.id = c.claim_result_id
        JOIN
    rule r ON r.id = cr.rule_id
        JOIN
    agreement a ON a.id = r.agreement_id
WHERE
    a.id IN (AGREEMENT_ID)
ORDER BY cc.id DESC
