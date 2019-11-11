SELECT
    *
FROM
    RETAILER_DEPLOYMENT.journal_line_item jli
        JOIN
    journal j ON j.id = jli.journal_id
WHERE
    j.journal_type = 'JOURNAL_TYPE'
        AND j.id = (SELECT
            id
        FROM
            journal
        ORDER BY id DESC
        LIMIT 1)
