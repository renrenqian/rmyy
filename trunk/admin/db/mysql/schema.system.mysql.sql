/*==============================================================*/
/* Database name:  hzhos                                        */
/* DBMS name:      mysql 5.1                                    */
/* Created on:     2012-06-10 14:20:00                          */
/*==============================================================*/

/*==============================================================*/
/* drop tables                                                  */
/*==============================================================*/
/*==============================================================*/
/* system manage                                                */
/*==============================================================*/
DROP TABLE USER_ROLE_REF;
DROP TABLE USER_INFO;
DROP TABLE ROLE_INFO;
DROP TABLE ROLE_PRIVILEGE_REF;
DROP TABLE PRIVILEGE_INFO;
DROP TABLE SYSTEM_LOG;
DROP TABLE SYSTEM_INFO;
DROP TABLE SYSTEM_PARAMETER;
DROP TABLE DB_LOG;
DROP TABLE DATA_DICT;
DROP TABLE INSTATION_MESSAGE;

/* set the db encodings */
set names utf8;
/* commit;*/
/*==============================================================*/
/* Tables: system manage                                        */
/*==============================================================*/
/*==============================================================*/
/* 表名：用户信息表  Table Name(USER_INFO)                      */
/*==============================================================*/
CREATE TABLE USER_INFO  (
   UI_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   UI_ACCOUNT            VARCHAR(128),
   UI_NAME               VARCHAR(128),
   UI_PWD                VARCHAR(128),
   UI_SHOW               INTEGER       default 1,
   UI_ENABLE             INTEGER       default 1,
   UI_DESC               VARCHAR(512),
   UI_OFFIC_PHONE        VARCHAR(16),
   UI_CELL_PHONE         VARCHAR(16),
   UI_MAIL               VARCHAR(64),
   UI_HOME               VARCHAR(256),
   UI_COMPANY            VARCHAR(256),
   PRIMARY KEY (UI_ID)
) type = InnoDB;

alter table USER_INFO auto_increment= 1000;
/* COMMENT ON COLUMN CONTENT_PL_REF.PL_ORDER IS */
/* '0 alway at the front, -1 alwasy at the end, this will be input the order INTEGER on page';*/

/*==============================================================*/
/* 表名：用户角色关联表  Table Name(USER_ROLE_REF)              */
/*==============================================================*/
CREATE TABLE USER_ROLE_REF  (
   UI_ID                 INTEGER               NOT NULL,
   RI_ID                 INTEGER               NOT NULL
) type=InnoDB;

/*==============================================================*/
/* 表名：角色信息表  Table Name(ROLE_INFO)                      */
/*==============================================================*/
CREATE TABLE ROLE_INFO  (
   RI_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   RI_NAME               VARCHAR(128),
   RI_PARENT             INTEGER,
   RI_LEVEL              INTEGER,
   RI_DESC               VARCHAR(256),
   RI_SHOW               INTEGER   default 1,
   PRIMARY KEY (RI_ID)
) type=InnoDB;

alter table ROLE_INFO auto_increment= 1000;

/*==============================================================*/
/* 表名：角色权限关联表  Table Name(ROLE_PRIVILEGE_REF)         */
/*==============================================================*/
CREATE TABLE ROLE_PRIVILEGE_REF  (
   RI_ID                 INTEGER               NOT NULL,
   PI_ID                 INTEGER               NOT NULL
) type=InnoDB;

/*==============================================================*/
/* 表名：权限信息表  Table Name(PRIVILEGE_INFO)                 */
/*==============================================================*/
CREATE TABLE PRIVILEGE_INFO  (
   PI_ID                 INTEGER               NOT NULL,
   PI_METHOD             VARCHAR(128),
   PI_NAME               VARCHAR(128),
   PI_PARENT             INTEGER,
   PI_LEVEL              INTEGER,
   PI_ORDER              INTEGER,
   PI_DESC               VARCHAR(256),
   PI_SHOW               INTEGER,
   PI_NEED_LOGON         INTEGER default 1,
   PRIMARY KEY (PI_ID)
) type=InnoDB;

/* alter table ROLE_INFO auto_increment= 1000; */

/*==============================================================*/
/* 表名：系统日志 Table Name(SYSTEM_LOG)                        */
/*==============================================================*/
CREATE TABLE SYSTEM_LOG  (
   SL_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   SL_TIME               DATETIME,
   SL_CATE               INTEGER,
   SL_USER               INTEGER,
   SL_IP                 VARCHAR(16),
   SL_STATE              INTEGER      comment '0: admin, user; 1: customer',
   SL_ACTION             VARCHAR(32),
   SL_PARAMS             VARCHAR(1024),
   PRIMARY KEY (SL_ID)
) type=InnoDB;

alter table SYSTEM_LOG auto_increment= 1000;

/*==============================================================*/
/* 表名：系统信息表 Table Name(SYSTEM_INFO)                     */
/*==============================================================*/
CREATE TABLE SYSTEM_INFO  (
   SYS_ID                INT                   NOT NULL  AUTO_INCREMENT,
   SYS_NAME              VARCHAR(128),
   SYS_CODE              VARCHAR(128),
   SYS_VERSION           VARCHAR(8),
   REDIS_IP              VARCHAR(32),
   REDIS_PORT            INT,
   FTP_ACCOUNT           VARCHAR(64),
   FTP_PWD               VARCHAR(64),
   SRC_ROOT              VARCHAR(32),
   DST_ROOT              VARCHAR(32),
   ADD_TIME              DATETIME,
   ADD_USER              VARCHAR(64),
   UPDATE_TIME           DATETIME,
   UPDATE_USER           VARCHAR(64),
   PRIMARY KEY (SYS_ID)
) type=InnoDB;

/*==============================================================*/
/* 表名：系统参数表 Table Name(SYSTEM_PARAMETER)                */
/*==============================================================*/
CREATE TABLE SYSTEM_PARAMETER  (
   SP_ID                 INT                   NOT NULL comment 'cant be new added',
   SP_NAME               VARCHAR(128),
   SP_CODE               VARCHAR(64),
   SP_MARK               VARCHAR(32),
   SP_UPDATE_CACHE       INT,
   SP_CATE               VARCHAR(64),
   SP_TYPE               VARCHAR(16),
   SP_VALUE              VARCHAR(32),
   SP_EDITABLE           INT,
   SP_SHOW               INT,
   SP_DESC               VARCHAR(256),
   PRIMARY KEY (SP_ID)
) type=InnoDB;

/*==============================================================*/
/* 表名：数据库日志 TableName(DB_LOG )                          */
/*==============================================================*/
CREATE TABLE DB_LOG  (
   DL_NAME               VARCHAR(128),
   DL_RESULT             VARCHAR(16),
   DL_TIME               DATETIME,
   DL_DESC               VARCHAR(512)
) type=InnoDB;
/*  Create/Recreate indexes */
create index dl_name_index on DB_LOG (DL_NAME);

/*==============================================================*/
/* 表名：数据字典 Table Name(DATA_DICT)                         */
/*==============================================================*/
CREATE TABLE DATA_DICT  (
   DICT_KIND             VARCHAR(32)       NOT NULL,
   DICT_VALUE            VARCHAR(64)       NOT NULL,
   DICT_KEY              INTEGER,
   DICT_DISVALUE         VARCHAR(128)      NOT NULL,
   DICT_HIDE             INTEGER,
   DICT_PARENTKIND       VARCHAR(32)       NOT NULL,
   DICT_USERADD          INTEGER           DEFAULT 0,
   PRIMARY KEY (DICT_KIND, DICT_KEY)
) type=InnoDB;

/*==============================================================*/
/* 表名：站内消息表 Table Name(INSTATION_MESSAGE)               */
/*==============================================================*/
CREATE TABLE INSTATION_MESSAGE  (
   IMSG_ID               INTEGER             NOT NULL  AUTO_INCREMENT,
   IMSG_NAME             VARCHAR(128),
   IMSG_CONTENT          VARCHAR(512),
   IMSG_SENDER           INTEGER             NOT NULL,
   IMSG_RECEIVER         INTEGER,
   IMSG_READ             INTEGER             DEFAULT '0',
   IMSG_SENDTIME         DATETIME,
   IMSG_READTIME         DATETIME,
   IMSG_MARK             INTEGER,
   PRIMARY KEY (IMSG_ID)
) type=InnoDB;

alter table INSTATION_MESSAGE auto_increment= 1000;
