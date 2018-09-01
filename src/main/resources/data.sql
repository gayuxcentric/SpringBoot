/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, userName) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, userName) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, userName) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, userName) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, userName) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, userName) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, userName)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, userName)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

--DATASET for MANUFACTURER
insert into manufacturer(manufacturer_id,date_created,date_updated,manufacturer_name)
values(1,now(),now(),'BMW');

insert into manufacturer(manufacturer_id,date_created,date_updated,manufacturer_name)
values(2,now(),now(),'AUDI');

insert into manufacturer(manufacturer_id,date_created,date_updated,manufacturer_name)
values(3,now(),now(),'TOYOTA');

insert into manufacturer(manufacturer_id,date_created,date_updated,manufacturer_name)
values(4,now(),now(),'HYUNDAI');

insert into manufacturer(manufacturer_id,date_created,date_updated,manufacturer_name)
values(5,now(),now(),'NISSAN');

insert into manufacturer(manufacturer_id,date_created,date_updated,manufacturer_name)
values(6,now(),now(),'VOLVO');

--DATASET for CAR
insert into car (car_id, engine_type,is_convertible_type,license_plate,rating_stars,seat_count,date_created,date_updated,manufacturer) 
values (1,'gas',true,'AAAAA',5,4,now(),now(),1);

insert into car (car_id, engine_type,is_convertible_type,license_plate,rating_stars,seat_count,date_created,date_updated,manufacturer) 
values (2,'Petrol',true,'BBBB',3,6,now(),now(),2);

insert into car (car_id, engine_type,is_convertible_type,license_plate,rating_stars,seat_count,date_created,date_updated,manufacturer) 
values (3,'Petrol',true,'CCCC',2,6,now(),now(),2);

insert into car (car_id, engine_type,is_convertible_type,license_plate,rating_stars,seat_count,date_created,date_updated,manufacturer) 
values (4,'Battery',false,'DDDD',2,6,now(),now(),5);

insert into car (car_id, engine_type,is_convertible_type,license_plate,rating_stars,seat_count,date_created,date_updated,manufacturer) 
values (5,'Diesel',false,'EEEE',2,4,now(),now(),4);



