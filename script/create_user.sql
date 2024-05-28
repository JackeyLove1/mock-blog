create database if not exists test;
use test;

create table if not exists `user`
(
    id        bigint(20)   not null auto_increment,
    user_name varchar(30)  not null unique,
    email     varchar(50)  not null unique,
    password  varchar(255) not null,
    name      varchar(30)  not null,
    age       int(11) default null,
    constraint primary key (id),
    constraint ck_user_age check (age >= 0 and age <= 120),
    constraint uk_user_name unique (user_name)
);

create index idx_user_name on user (user_name);
create index idx_user_email on user (email);

INSERT INTO `user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('1', 'zhangsan', '123456', '张三', '18', 'test1@itcast.cn');
INSERT INTO `user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('2', 'lisi', '123456', '李四', '20', 'test2@itcast.cn');
INSERT INTO `user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('3', 'wangwu', '123456', '王五', '28', 'test3@itcast.cn');
INSERT INTO `user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('4', 'zhaoliu', '123456', '赵六', '21', 'test4@itcast.cn');
INSERT INTO `user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('5', 'sunqi', '123456', '孙七', '24', 'test5@itcast.cn');

