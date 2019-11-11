SELECT
    usl.session_id
FROM
    RETAILER_DEPLOYMENT.user_security_log usl
        JOIN
    RETAILER_DEPLOYMENT.users u ON u.id = usl.user_id
WHERE
    u.email = 'EMAIL_ADDRESS'
ORDER BY usl.id DESC
LIMIT 1
;
