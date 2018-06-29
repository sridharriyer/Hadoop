create external table sridhar.gdpr_contact_preferences(
email string comment "email",
filterid string comment "filter id",
preferencetype string comment "preference type",
customerdate string comment "customer date",
preferencestatus string comment "preference status",
expirationdate string comment "expiration date")
comment 'customer contact preference'
partitioned by (dt string)
location 's3://map-dev-data/iyers/gdpr'


insert into sridhar.gdpr_contact_preferences 
partition(dt='20180628') 
values
('adbc342@gmail.com', 'def-345', 'email', '2018-11-28', 'active', '2020-01-17')


create  external table sridhar.gdpr_contact_preferences_scrubbed 
(
email string comment "email",
filterid string comment "filter id",
preferencetype string comment "preference type",
customerdate string comment "customer date",
preferencestatus string comment "preference status",
expirationdate string comment "expiration date",
dt string comment "date")
location 's3://map-dev-data/iyers/gdpr/scrub'


insert overwrite table sridhar.gdpr_contact_preferences_scrubbed 
select email, filterid, preferencetype, customerdate, preferencestatus, expirationdate, dt
from gdpr_contact_preferences where not (email like '%\'\'%')

set hive.exec.dynamic.partition.mode=nonstrict;
insert into gdpr_contact_preferences partition(dt) select * from gdpr_contact_preferences_scrubbed scrub;
