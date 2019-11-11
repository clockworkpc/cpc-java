SELECT
    dgki.value AS 'rule_type',
    rr.description AS 'rule_description',
    a.start_date AS 'agreement_start_date',
    a.end_date AS 'agreement_end_date',
    r.start_date AS 'rule_start_date',
    r.end_date 'rule_end_date',
    r.claim_frequency,
    i.item_type,
    i.name,
    rp.retailer_vendor_product_code,
    rp.unit_amount,
    rp.unit_percentage,
    rp.stock_on_hand,
    rp.stock_on_hand_date
FROM
    RETAILER_DEPLOYMENT.rule r
        JOIN
    RETAILER_DEPLOYMENT.rule_type rt ON rt.id = r.rule_type_id
        JOIN
    RETAILER_DEPLOYMENT.default_glossary_key_item dgki ON dgki.key_id = rt.glossary_key_id
        JOIN
    RETAILER_DEPLOYMENT.retailer_rule rr ON rr.id = r.retailer_rule_id
        JOIN
    RETAILER_DEPLOYMENT.item i ON i.rule_id = r.id
        JOIN
    RETAILER_DEPLOYMENT.rule_product rp ON rp.rule_id = r.id
        JOIN
    RETAILER_DEPLOYMENT.agreement a ON a.id = r.agreement_id
WHERE
    r.agreement_id = AGREEMENT_ID
        AND dgki.lang_code = 'en_GB'
ORDER BY dgki.value , rr.description , a.start_date , a.end_date , r.start_date , r.end_date , r.claim_frequency , i.item_type , i.name , rp.retailer_vendor_product_code , rp.unit_amount , rp.unit_percentage , rp.stock_on_hand , rp.stock_on_hand_date
