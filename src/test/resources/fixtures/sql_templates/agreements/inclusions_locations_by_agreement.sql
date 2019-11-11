SELECT
    r.id AS 'rule_id',
    dgki.value,
    rr.description,
    i.item_type,
    i.item_reference,
    i.name,
    rp.retailer_vendor_product_code,
    rp.unit_amount,
    rp.unit_percentage
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
        LEFT JOIN
    RETAILER_DEPLOYMENT.rule_product rp ON rp.rule_id = r.id
WHERE
    r.agreement_id IN (AGREEMENT_ID)
        AND dgki.lang_code = 'en_us'
ORDER BY r.id , dgki.value , rr.description , i.item_type , i.item_reference , i.name , rp.retailer_vendor_product_code , rp.unit_amount , rp.unit_percentage
