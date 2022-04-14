mysql -uroot -pmysql

use javatest;

drop table if exists room;

create table if not exists room (
room_num int(10) not null,
user varchar(30) default null,
is_empty boolean not null default 1,
check_in_time datetime default null,
check_out_time datetime default null
);

