create database if not exists blog;
use blog;

create table users(
	name varchar(50) primary key,
    fullName varchar(50),
    password varchar(100),
    email varchar(50),
    gender enum("homme","femme"),
    phoneNo char(8)
);

create table article(
	id int auto_increment primary key,
    title varchar(255) not null,
    content longtext,
    created_at datetime default now(),
    author varchar(50) references users(name)
);

create table likes(
	id int references article(id),
    user varchar(50) references users(name),
    primary key (id, user)
);
