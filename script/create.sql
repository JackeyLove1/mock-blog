create database if not exists mock_blog;
use mock_blog;

drop table if exists ms_sys_user;
CREATE TABLE `ms_sys_user`
(
    `id`                  bigint(0)          NOT NULL AUTO_INCREMENT,
    `account`             varchar(64) UNIQUE NOT NULL COMMENT '账号',
    `admin`               bit(1)                  default 0 NULL DEFAULT NULL COMMENT '是否管理员',
    `avatar`              varchar(255)            default null COMMENT '头像',
    `create_date`         bigint(0)          NULL DEFAULT NULL COMMENT '注册时间',
    `deleted`             bit(1)                  default 0 DEFAULT NULL COMMENT '是否删除',
    `email`               varchar(128)            DEFAULT NULL COMMENT '邮箱',
    `last_login`          bigint(0)          NULL DEFAULT NULL COMMENT '最后登录时间',
    `mobile_phone_number` varchar(20)             default NULL COMMENT '手机号',
    `nickname`            varchar(255)       NOT NULL COMMENT '昵称',
    `password`            varchar(64)        NOT NULL COMMENT '密码',
    `salt`                varchar(255)            default NULL DEFAULT NULL COMMENT '加密盐',
    `status`              varchar(255)            default NULL DEFAULT NULL COMMENT '状态',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_account` (`account`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

drop table if exists `ms_admin`;

create table `ms_admin`
(
    id       bigint(0)    not null auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    primary key (id),
    index idx_username (username)
);

drop table if exists `ms_article`;
create table `ms_article`
(
    id             bigint(0) not null auto_increment,
    comment_counts int          default 0,
    view_counts    int          default 0,
    create_time    bigint(0),
    title          varchar(64)  default null,
    summary        varchar(255) default null,
    weight         int(0)       default 0,
    author_id      bigint(0) not null,
    body_id        bigint(0) not null,
    category_id    int(0)    not null,
    primary key (id),
    index idx_create_time (create_time),
    index idx_author_id (author_id),
    index idx_category_id (category_id),
    index idx_title (title)
);

drop table if exists `ms_article_body`;
create table `ms_article_body`
(
    id           bigint(0) not null auto_increment,
    content      longtext,
    content_html longtext,
    article_id   bigint(0) not null,
    primary key (id),
    index idx_article_id (article_id)
);

drop table if exists `ms_article_tag`;
create table `ms_article_tag`
(
    id         bigint(0) not null auto_increment,
    article_id bigint(0) not null,
    tag_id     bigint(0) not null,
    primary key (id),
    index idx_article_id (article_id),
    index idx_tag_id (tag_id)
);

drop table if exists `ms_category`;
create table `ms_category`
(
    id            bigint(0)    not null auto_increment,
    avatar        varchar(255) default null,
    category_name varchar(255) not null,
    description   varchar(255) default null,
    primary key (id)
);

drop table if exists `ms_comment`;
create table `ms_comment`
(
    id          bigint(0)    not null auto_increment,
    content     varchar(255) not null,
    create_date bigint(0)    not null,
    article_id  int(0)       not null,
    author_id   bigint(0)    not null,
    parent_id   bigint(0)    not null,
    to_uid      bigint(0)    not null,
    level       varchar(1),
    primary key (id),
    index idx_create_date (create_date),
    index idx_article_id (article_id),
    index idx_author_id (author_id)
);

drop table if exists `ms_tag`;
create table `ms_tag`
(
    id       bigint(0)    not null auto_increment,
    tag_name varchar(255) not null,
    primary key (id)
)