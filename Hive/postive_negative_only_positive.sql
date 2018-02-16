SELECT t1.name, t1.number 
 FROM table t1
LEFT OUTER JOIN
 (SELECT name, number FROM table where number < 0) t2
ON
 t1.name = t2.name and t1.number = t2.number
WHERE t1.number > 0 and t2.number IS NOT NULL

UNION ALL

SELECT t1.name, t1.number
 FROM table t1
LEFT OUTER JOIN
 (SELECT name, number FROM table where number < 0) t2
ON
 t1.name = t2.name 
WHERE t1.number > 0 and t2.number IS  NULL;
