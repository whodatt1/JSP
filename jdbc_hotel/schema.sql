mysql -uroot -pmysql

use javatest;

drop table if exists room;

create table if not exists room (
room_num varchar(10) primary key,
user_name varchar(10),
user_age tinyint(3),
user_gen char(1),
is_empty tinyint(1) default 1,
check_in_time datetime, 
check_out_time datetime 
);

 