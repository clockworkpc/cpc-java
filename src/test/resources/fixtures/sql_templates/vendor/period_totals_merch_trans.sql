SELECT
    DATE_FORMAT(trans_date, '%Y-%m') AS 'trans_month',
    SUM(quantity) AS 'quantity',
    SUM(quantity / pack_size) AS 'quantity_pack',
    SUM(cost_price_ex_gst) AS 'cost_price_ex_gst',
    SUM(cost_price_gst_amt) AS 'cost_price_gst_amt',
    SUM(sell_price_ex_gst) AS 'sell_price_ex_gst',
    SUM(sell_price_gst_amt) AS 'sell_price_gst_amt',
    (SUM(cost_price_gst_amt)) / SUM(cost_price_ex_gst) AS 'period_gst_ratio'
FROM
    RETAILER_DEPLOYMENT.merch_trans
WHERE
    vendor_number  = 'RETAILER_MERCH_VENDOR_NUMBER'
        AND trans_date BETWEEN 'START_DATE' AND 'EXPIRY_DATE'
        AND active = 1
        AND product_id IN (PRODUCTS)
        AND location_id IN (LOCATIONS)
GROUP BY DATE_FORMAT(trans_date, '%Y-%m');
