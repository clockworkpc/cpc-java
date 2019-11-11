SELECT DISTINCT
    TABLE_SCHEMA
FROM
    information_schema.tables
WHERE
    TABLE_SCHEMA LIKE 'rdm_%_prod'
