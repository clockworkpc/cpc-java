SELECT DISTINCT
    *
FROM
    information_schema.COLUMNS
WHERE
    table_schema = 'RETAILER_DEPLOYMENT'
        AND TABLE_NAME IN ('retailer_settings_accounting')
