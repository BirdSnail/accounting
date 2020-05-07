# 标签表
create table accounting_tag
(
    `id`          bigint(20) unsigned not null auto_increment,
    `description` varchar(200)        not null,
    `status`      tinyint(1) unsigned not null comment '0 -> disable, 1 -> enable',
    `user_id`     bigint(20) unsigned not null,
    `create_time` datetime            not null default CURRENT_TIMESTAMP,
    `update_time` datetime                     default null on update CURRENT_TIMESTAMP,
    primary key pk_id (id),
    key user_key (user_id),
    foreign key (user_id) references hcas_userinfo (id)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8;

# 账单记录表
create table accounting_record
(
    id          bigint(20) unsigned not null auto_increment,
    amount      decimal(18, 2)      not null default 0.00,
    note        varchar(200)                 default null,
    category    tinyint(1) unsigned not null comment '0 -> outcome, 1 -> income',
    status      tinyint(1) unsigned not null comment '0 -> disable, 1 -> enable',
    user_id     bigint(20) unsigned not null,
    create_time datetime            not null default CURRENT_TIMESTAMP,
    update_time datetime                     default null on update CURRENT_TIMESTAMP,
    primary key pk_id (id),
    key user_key (user_id),
    foreign key (user_id) references hcas_userinfo (id)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8;