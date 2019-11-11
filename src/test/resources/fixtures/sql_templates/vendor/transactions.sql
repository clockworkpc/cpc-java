SELECT DISTINCT
    product_id,
    location_id,
    category_id,
    department_id,
    division_id,
    SUM(cost_price_ex_gst),
    SUM(cost_price_gst_amt),
    SUM(quantity)
FROM
    RETAILER_DEPLOYMENT.DATA_SOURCE
WHERE
    trans_date BETWEEN 'START_DATE' AND 'EXPIRY_DATE'
        AND vendor_number = 'RETAILER_MERCH_VENDOR_NUMBER'
        AND cost_price_ex_gst > 0
        AND cost_price_gst_amt GST_OPERATOR 0
GROUP BY location_id, product_id, division_id, department_id, category_id
ORDER BY SUM(cost_price_ex_gst) DESC , SUM(quantity) DESC
LIMIT 100;
