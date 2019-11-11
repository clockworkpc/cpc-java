SELECT DISTINCT
    *
FROM
    RETAILER_DEPLOYMENT.retailer_vendor
WHERE
    retailer_merch_vendor_number IS NOT NULL
        AND retailer_ap_vendor_number = 'RETAILER_AP_VENDOR_NUMBER'
