SELECT
    product_id, SUM(stock_on_hand)
FROM
    RETAILER_DEPLOYMENT.product_stock_on_hand
WHERE
    vendor_number = 'RETAILER_MERCH_VENDOR_NUMBER'
        AND start_date <= 'EFFECTIVE_DATE'
        AND end_date >= 'EFFECTIVE_DATE'
        AND location_code in (LOCATIONS)
GROUP BY product_id
ORDER BY SUM(stock_on_hand) DESC
LIMIT LIMIT_INTEGER
