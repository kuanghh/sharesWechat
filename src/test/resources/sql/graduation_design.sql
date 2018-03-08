/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/8 20:46:32                            */
/*==============================================================*/


drop table if exists tb_check_code;

drop table if exists tb_shares;

drop table if exists tb_shares_detailed;

drop table if exists tb_user;

drop table if exists tb_user_search_log;

/*==============================================================*/
/* Table: tb_check_code                                         */
/*==============================================================*/
create table tb_check_code
(
   id                   varchar(36) not null,
   user_id              varchar(36) not null comment '用户id',
   open_id              varchar(36) not null comment 'open_id',
   create_time          datetime not null comment '生成时间',
   is_valid             tinyint not null comment '是否有效',
   primary key (id)
);

/*==============================================================*/
/* Table: tb_shares                                             */
/*==============================================================*/
create table tb_shares
(
   id                   varchar(36) not null comment '唯一标识',
   shares_num           varchar(36) not null comment '股票代码',
   shares_href          varchar(36) not null comment '股票网址',
   shares_name          varchar(36) not null comment '股票名字',
   create_time          datetime not null comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: tb_shares_detailed                                    */
/*==============================================================*/
create table tb_shares_detailed
(
   id                   varchar(36) not null comment '唯一标识',
   shares_id            varchar(36) not null comment '股票外键',
   create_time          datetime not null comment '创建时间',
   open_price           int not null comment '开盘价',
   close_price          int not null comment '收盘价',
   ceilling_price       int not null comment '最高价',
   floor_price          int not null comment '最低价',
   rise_and_fall_range  int not null comment '涨跌幅（%）',
   rise_and_fall_quota  int not null comment '涨跌额',
   volume               int not null comment '成交量(手)',
   turn_volume          int not null comment '成交额（万）',
   turnover_rate        int not null comment '换手率（%）',
   amplitude            int not null comment '振幅',
   p_e_ratio            int not null comment '市盈率',
   state                tinyint not null comment '状态',
   primary key (id)
);

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   id                   varchar(36) not null,
   open_id              varchar(50) not null,
   name                 varchar(50) comment '名字',
   phone                varchar(13) not null,
   email                varchar(100),
   account              varchar(60) comment '帐号',
   is_binding           tinyint not null comment '是否注册了(是:1,否:0)',
   status               tinyint comment '状态(已关注:1,未关注:0)',
   create_time          datetime not null comment '关注时间',
   register_time        datetime comment '注册时间',
   unregister_time      datetime comment '取消关注时间',
   primary key (id)
);

/*==============================================================*/
/* Table: tb_user_search_log                                    */
/*==============================================================*/
create table tb_user_search_log
(
   id                   varchar(36) not null,
   user_id              varchar(36) not null,
   open_id              varchar(36) not null,
   start_time           datetime not null,
   end_time             datetime not null,
   shares_id            varchar(36) not null,
   create_time          datetime not null,
   primary key (id)
);

