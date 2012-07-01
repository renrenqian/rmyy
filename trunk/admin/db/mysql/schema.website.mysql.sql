/*==============================================================*/
/* Database name:  hzhos                                        */
/* DBMS name:      mysql 5.1                                    */
/* Created on:     2012-06-10 14:20:00                          */
/*==============================================================*/

/*==============================================================*/
/* drop tables                                                  */
/*==============================================================*/
/*==============================================================*/
/* website manage                                                */
/*==============================================================*/
DROP TABLE HOME_IMAGE;
DROP TABLE CONTENT_DETAIL;
DROP TABLE CONTENT_SHARE;
DROP TABLE CONTENT_CLICK;
DROP TABLE COLUMN_INFO;
DROP TABLE ONLINE_CONSULTATION;

/* set the db encodings */
set names utf8;
/* commit;*/
/*==============================================================*/
/* Tables: website manage                                        */
/*==============================================================*/
/*==============================================================*/
/* 表名：首页滚动图片信息表  Table Name(HOME_IMAGE)               */
/*==============================================================*/
CREATE TABLE HOME_IMAGE  (
   HI_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   HI_TITLE              DATETIME,
   HI_IMAGE_RESULATION   VARCHAR(16),
   HI_LINK               VARCHAR(256),
   HI_ORDER              INTEGER,
   PRIMARY KEY (HI_ID)
) type=InnoDB;

alter table HOME_IMAGE auto_increment= 1000;

/*==============================================================*/
/* 表名：内容信息详细表  Table Name(CONTENT_DETAIL)                */
/*==============================================================*/
CREATE TABLE CONTENT_DETAIL  (
   CD_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   CD_TITAL              VARCHAR(128),
   CI_HOME_PAGE          INTEGER,
   CI_ID                 INTEGER,
   CD_SHOW_DAYS          INTEGER,
   CD_MEDIUM             INTEGER,
   CD_KEY                VARCHAR(512),
   CD_PUBLISH_TIME       DATETIME,
   CD_AUDIT_RESULT       INTEGER   DEFAULT '1',
   CD_ORDER              INTEGER   DEFAULT '1',
   GM_ID                 INTEGER,
   CD_AUTHOR             VARCHAR(512),
   CD_DETAIL             BLOB,
   CD_ATTACHMENT         VARCHAR(512),
   PRIMARY KEY (CD_ID)
) type=InnoDB;

alter table CONTENT_DETAIL auto_increment= 1000;

/*==============================================================*/
/* 表名：内容分享表  Table Name(CONTENT_SHARE)                    */
/*==============================================================*/
CREATE TABLE CONTENT_SHARE  (
   CD_ID                 INTEGER,
   GM_ID                 INTEGER
) type=InnoDB;

/*==============================================================*/
/* 表名：内容点击信息表  Table Name(CONTENT_CLICK)                */
/*==============================================================*/
CREATE TABLE CONTENT_CLICK  (
   CD_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   CC_TIME               DATETIME,
   CC_IP                 VARCHAR(32),
   CC_USER               INTEGER,
   PRIMARY KEY (CD_ID)
) type=InnoDB;

alter table CONTENT_CLICK auto_increment= 1000;

/*==============================================================*/
/* 表名：栏目信息表  Table Name(COLUMN_INFO)                      */
/*==============================================================*/
CREATE TABLE COLUMN_INFO  (
   CI_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   CI_TITAL              VARCHAR(128),
   CI_ICON               VARCHAR(256),
   CI_LEVEL              INTEGER,
   CI_URL                VARCHAR(256),
   CI_PARENT             INTEGER,
   CI_NEWS               INTEGER   DEFAULT '1',
   CI_DESC               VARCHAR(1024),
   CI_ADD_TIME           DATETIME,
   CI_ADDER              INTEGER,
   PRIMARY KEY (CI_ID)
) type=InnoDB;

alter table COLUMN_INFO auto_increment= 1000;

/*==============================================================*/
/* 表名：在线咨询信息表  Table Name(ONLINE_CONSULTATION)          */
/*==============================================================*/
CREATE TABLE ONLINE_CONSULTATION  (
   OC_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   OC_POST_TIME          DATETIME,
   OC_REQUEST_OFFICE     INTEGER,
   OC_RECEIVE_OFFICE     INTEGER,
   OC_POST_NAME          VARCHAR(64),
   OC_POST_AGE           INTEGER,
   OC_SEX                INTEGER,
   OC_POST_SUBJECT       VARCHAR(64),
   OC_DESC               VARCHAR(512),
   OS_ANSWER             VARCHAR(512),
   OS_ANSWERED           INTEGER,
   OS_ANSWER_OFFICE      INTEGER,
   OS_ANSWER_TIME        DATETIME,
   OS_TYPICAL            INTEGER,
   PRIMARY KEY (OC_ID)
) type=InnoDB;

alter table DOCTOR_INFO auto_increment= 1000;

/*==============================================================*/
/* 表名：人才招聘表  Table Name(STAFF_RECRUITMENT)                */
/*==============================================================*/
CREATE TABLE STAFF_RECRUITMENT  (
   SR_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   SR_POSITION           VARCHAR(128),
   SR_LOCATION           VARCHAR(128),
   SP_PUBLISH_DATE       DATETIME,
   SP_EXPIRY_DATE        DATETIME,
   SP_RECRUIT_NO         INTEGER,
   SP_RECRUIT_AVAILABLE  INTEGER,
   SP_ATTACHEMENT        VARCHAR(256),
   SP_REQUIREMENT        VARCHAR(4096),
   PRIMARY KEY (SR_ID)
) type=InnoDB;

alter table STAFF_RECRUITMENT auto_increment= 1000;
