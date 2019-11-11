SELECT
    rv.retailer_ap_vendor_number
FROM
    RETAILER_DEPLOYMENT.DATA_SOURCE mt
        JOIN
    RETAILER_DEPLOYMENT.retailer_vendor rv ON rv.retailer_merch_vendor_number = mt.vendor_number
WHERE
    trans_date BETWEEN 'START_DATE' AND 'EXPIRY_DATE'
GROUP BY rv.retailer_ap_vendor_number
ORDER BY COUNT(*) DESC;
