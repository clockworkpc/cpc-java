-- @accrued_product_ids = (SELECT arc.product_id from accrual_result_component arc);
-- @accrued_location_ids = (SELECT arc.location_id from accrual_result_component arc);
SET @start_date := '2018-10-01';
SET @expiry_date := '2018-12-31';

SELECT 
    RIGHT(rv.retailer_ap_vendor_number, 7) AS 'vendor_number',
    v.`name`,
    mt.invoice_number,
    mt.po_number,
    mt.trans_date,
    SUM(mt.cost_price_ex_gst) AS 'total_ex_gst',
    SUM(mt.cost_price_gst_amt) AS 'total_gst',
    (SUM(mt.cost_price_ex_gst) + SUM(mt.cost_price_gst_amt)) AS 'total_inc_gst',
    arc.apportioned_claim_amount
FROM
    merch_trans mt
        JOIN
    retailer_vendor rv ON rv.retailer_merch_vendor_number = mt.vendor_number
        JOIN
    vendor v ON v.vendor_number = rv.retailer_ap_vendor_number
        JOIN
    accrual_result_component arc ON arc.product_id = mt.product_id
 --        AND arc.location_id = mt.location_id
WHERE
    mt.trans_date BETWEEN @start_date AND @expiry_date
        AND mt.product_id IN (SELECT 
            arc.product_id
        FROM
            accrual_result_component arc)
        AND mt.location_id IN (SELECT 
            arc.location_id
        FROM
            accrual_result_component arc)
        AND mt.trans_type_id IN ('PIRA' , 'PIRB', 'PCRA', 'PCRB')
GROUP BY rv.retailer_ap_vendor_number , mt.invoice_number