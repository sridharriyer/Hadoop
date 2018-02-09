select 12 * (YEAR(nextbillingdate) - YEAR(createddate)) +  (MONTH(nextbillingdate) - MONTH(createddate)) AS months from    db.table where id = 1313
