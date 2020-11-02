create database if not exists `ssm_test` default character set utf8mb4 collate utf8mb4_unicode_ci;
use `ssm_test`;

# 这个语句是客户端与服务端交互的字符编码
# 具体可以通过 show variables like "character%"; 来查看
set names utf8mb4;

drop table if exists `tb_friend`;
set character_set_client=utf8mb4;
create table if not exists `tb_friend` (
    `id` int(11) not null auto_increment,
    `name` varchar(15) not null,
    `gender` char(1) not null,
    `age` int(3) not null,
    `clazz` varchar(20) not null,
    `qq` varchar(12) not null,
    `wechat` varchar(20) not null,
    `telephone` varchar(12) not null,
    `address` varchar(60) not null,
    primary key(`id`)
) engine=InnoDB auto_increment=424 default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

# 注意，auto_incrment=424，表示起始位置就是 424

# 具体看书签吧
lock tables `tb_friend` write;
unlock tables;
