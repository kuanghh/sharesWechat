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
   user_id              varchar(36) not null comment '�û�id',
   open_id              varchar(36) not null comment 'open_id',
   create_time          datetime not null comment '����ʱ��',
   is_valid             tinyint not null comment '�Ƿ���Ч',
   primary key (id)
);

/*==============================================================*/
/* Table: tb_shares                                             */
/*==============================================================*/
create table tb_shares
(
   id                   varchar(36) not null comment 'Ψһ��ʶ',
   shares_num           varchar(36) not null comment '��Ʊ����',
   shares_href          varchar(36) not null comment '��Ʊ��ַ',
   shares_name          varchar(36) not null comment '��Ʊ����',
   create_time          datetime not null comment '����ʱ��',
   primary key (id)
);

/*==============================================================*/
/* Table: tb_shares_detailed                                    */
/*==============================================================*/
create table tb_shares_detailed
(
   id                   varchar(36) not null comment 'Ψһ��ʶ',
   shares_id            varchar(36) not null comment '��Ʊ���',
   create_time          datetime not null comment '����ʱ��',
   open_price           int not null comment '���̼�',
   close_price          int not null comment '���̼�',
   ceilling_price       int not null comment '��߼�',
   floor_price          int not null comment '��ͼ�',
   rise_and_fall_range  int not null comment '�ǵ�����%��',
   rise_and_fall_quota  int not null comment '�ǵ���',
   volume               int not null comment '�ɽ���(��)',
   turn_volume          int not null comment '�ɽ����',
   turnover_rate        int not null comment '�����ʣ�%��',
   amplitude            int not null comment '���',
   p_e_ratio            int not null comment '��ӯ��',
   state                tinyint not null comment '״̬',
   primary key (id)
);

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   id                   varchar(36) not null,
   open_id              varchar(50) not null,
   name                 varchar(50) comment '����',
   phone                varchar(13) not null,
   email                varchar(100),
   account              varchar(60) comment '�ʺ�',
   is_binding           tinyint not null comment '�Ƿ�ע����(��:1,��:0)',
   status               tinyint comment '״̬(�ѹ�ע:1,δ��ע:0)',
   create_time          datetime not null comment '��עʱ��',
   register_time        datetime comment 'ע��ʱ��',
   unregister_time      datetime comment 'ȡ����עʱ��',
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

