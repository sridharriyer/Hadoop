with a as(
select  sum(p.quantity) as unit_count, 
CASE
WHEN (p.quantity < 0) then count(p.order_number) - (2 * count(p.quantity))
ELSE count(p.order_number)
END as order_count
FROM siyer.orders_with_trials p
where p.order_number = 67623213152 
group by p.quantity , p.order_number)
select sum(unit_count) unit_count, sum(order_count) from a;
