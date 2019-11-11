SELECT
    `value` AS 'setting_attribute'
FROM
    RETAILER_DEPLOYMENT.retailer_settings_attribute
WHERE
    `name` IN ('SETTING_NAME');
