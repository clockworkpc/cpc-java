SELECT
    dgki.id AS 'key_item_id',
    rg.key_item_id AS 'rg_key_item_id',
    rg.value AS 'retailer_value',
    dgki.value AS 'default_value',
    rg.retailer_id
FROM
    default_glossary_key_item dgki
        LEFT JOIN
    retailer_glossary rg ON rg.key_item_id = dgki.id
        AND rg.retailer_id = 0
        JOIN
    default_glossary_key dgk ON dgk.id = dgki.key_id
WHERE
    dgki.lang_code = 'en_GB'
