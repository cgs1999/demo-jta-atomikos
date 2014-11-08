drop database if exists jtatest1;
create database jtatest1 default character set utf8;
use jtatest1;
create table users (
	id int not null auto_increment,
	name varchar(50) not null,
	primary key(id)
) engine=InnoDB default charset=utf8;



drop database if exists jtatest2;
create database jtatest2 default character set utf8;
use jtatest2;
create table product (
	id int not null auto_increment,
	name varchar(50) not null,
	primary key(id)
) engine=InnoDB default charset=utf8;