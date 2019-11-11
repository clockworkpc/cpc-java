SELECT
    cr.id as 'claim_id'
FROM
    RETAILER_DEPLOYMENT.agreement a
        JOIN
    RETAILER_DEPLOYMENT.rule r ON r.agreement_id = a.id
        JOIN
    RETAILER_DEPLOYMENT.claim_result cr ON cr.rule_id = r.id
WHERE
  a.id IN (AGREEMENT_ID)
  AND cr.start_date_time = 'START_DATE_TIME'
  AND cr.end_date_time = 'END_DATE_TIME'
  ORDER BY r.comment
