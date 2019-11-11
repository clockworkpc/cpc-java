SELECT
    rr.allowance_type, rr.rule_type, rr.description
FROM
    RETAILER_DEPLOYMENT.retailer_rule rr
        JOIN
    rule_type rt ON rt.rule_type = rr.rule_type
        JOIN
    default_glossary_key_item dgki ON dgki.key_id = rt.glossary_key_id
WHERE
    rr.deleted = 0 AND rr.retired = 0
        AND rr.description = dgki.value
        AND lang_code = 'en_GB'
ORDER BY rr.description
