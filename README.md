# i2i_netmed_backup


#i2i_Netmed_Vital_Sign_Module

Requirment Analysis

Pulse Rate : 60 to 100 beats per minute. - int

Body Temperature : 95 degree F to 115 degree F

On July 10, 1980, 52-year-old Willie Jones of Atlanta was admitted to the hospital with heatstroke and a temperature of 115 degrees Fahrenheit. He spent 24 days in the hospital and survived.

Spo2 level: 60 to 100 - int

I had seen spo2 drops below 70 in Covid Affected Patients in Real life. But normal range is between 85 to 100

Weight - int

Blood Sugar level : 120 to 300 (3 Hours after eating)














#SQL Scripts:


create database netmed_dev

use netmed_dev

create table netmed_role(role_id Integer primary key, role_name varchar(40));

insert into netmed_role values(1,"Doctor");

select * from netmed_role





create table netmed_user(user_id Integer Primary key, user_name varchar(256), password varchar(256), role_id Integer references netmed_role (role_id), created_by varchar(256), created_date timestamp , last_modified_by varchar(256), last_modified_date timestamp);

insert into netmed_user values(1, "Hari Prasath", "xyz", 1, "Admin","2021-01-19 03:14:07" , "Admin", "2021-01-19 03:14:07");
insert into netmed_user values(2, "Nataraj", "abc", 2, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07");

select * from netmed_user






create table netmed_patient_mst(patient_id Integer, user_id long references netmed_user (user_id), dob date, age integer, gender integer, mobile varchar(20), email varchar(256), address varchar(256), zipcode integer, subscription_start_date date, subscription_end_date date);

alter table netmed_patient_mst add created_by varchar(256), add created_date timestamp , add last_modified_by varchar(256), add last_modified_date timestamp 

0 - Male
1 - Other
2 - Others

insert into netmed_patient_mst values(1, 2, '1995-09-26', 25, 0, "9842482716", "nataraj2609@gmail.com", "219 Florida", 608602, '2020-09-26', '2030-09-26')

update netmed_patient_mst set created_by = "Doctor hari",  created_date = "2021-01-19 03:14:07",
last_modified_by = "Doctor hari",  last_modified_date = "2021-01-19 03:14:07"
where patient_id = 1 and user_id = 2

select * from netmed_patient_mst






create table netmed_vital_mst(checkup_id integer, patient_id integer references netmed_patient_mst (patient_id) ,checkup_date date, body_temperature float, pulse_rate integer, weight integer, spo2 integer, blood_sugar_level integer, examiner_id integer references netmed_user (user_id), created_by varchar(256), created_date timestamp , last_modified_by varchar(256), last_modified_date timestamp )

insert into netmed_vital_mst values(1, 1, '2021-02-02', 98.2, 93, 80, 98, 240, 1, "Doctor Hari","2021-01-19 03:14:07" , "Doctor Hari", "2021-01-19 03:14:07")

select * from netmed_vital_mst
