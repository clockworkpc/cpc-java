
SELECT
    product_id, SUM(stock_on_hand)
FROM
    RETAILER_DEPLOYMENT.product_stock_on_hand
WHERE
    vendor_number = 'RETAILER_MERCH_VENDOR_NUMBER'
        AND product_id in (PRODUCTS)
        AND location_code in (LOCATIONS)
        AND start_date <= 'EFFECTIVE_DATE'
        AND end_date >= 'EFFECTIVE_DATE'
GROUP BY product_id
ORDER BY SUM(stock_on_hand) DESC;
