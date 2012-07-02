/*==============================================================*/
/* Database name:  hzhos                                        */
/* DBMS name:      mysql 5.1                                    */
/* Created on:     2012-06-10 14:20:00                          */
/*==============================================================*/

/*==============================================================*/
/* drop tables                                                  */
/*==============================================================*/
/*==============================================================*/
/* group manage                                                 */
/*==============================================================*/
DROP TABLE GROUP_MEMBER;
DROP TABLE OFFICE_GENRE;
DROP TABLE OFFICE_REF;
DROP TABLE OFFICE_DETAIL;
DROP TABLE STAFF_INFO;
DROP TABLE TALENT_GENRE;
DROP TABLE OUTPATIENT_SERVICE;
DROP TABLE LEADER_GROUP_REF;
DROP TABLE LEADER_INFO;
DROP TABLE DOCTOR_OFFICE_REF;
DROP TABLE DOCTOR_TALENT_REF;
DROP TABLE DOCTOR_INFO;

/* set the db encodings */
set names utf8;
/*==============================================================*/
/* Tables: group manage                                         */
/*==============================================================*/
/*==============================================================*/
/* 表名：集团成员表  Table Name(GROUP_MEMBER)                     */
/*==============================================================*/
CREATE TABLE GROUP_MEMBER  (
   GM_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   GM_NAME               VARCHAR(128),
   GM_NAME_SHORT         VARCHAR(64),
   GM_ADDRESS            VARCHAR(256),
   GM_POSTAL             VARCHAR(8),
   GM_TELEPHONE          VARCHAR(16),
   GM_FAX                VARCHAR(16),
   GM_EMAIL              VARCHAR(128),
   PRIMARY KEY (GM_ID)
) type=InnoDB;

alter table GROUP_MEMBER auto_increment= 1020;

/*==============================================================*/
/* 表名：科室类型表  Table Name(OFFICE_GENRE)                     */
/*==============================================================*/
CREATE TABLE OFFICE_GENRE  (
   OG_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   OG_NAME               VARCHAR(64),
   OG_DESC               VARCHAR(128),
   OG_LEVEL              INTEGER,
   OG_PARENT             INTEGER,
   PRIMARY KEY (OG_ID)
) type=InnoDB;

alter table OFFICE_GENRE auto_increment= 1000;

/*==============================================================*/
/* 表名：科室所属分类表  Table Name(OFFICE_REF)                     */
/*==============================================================*/
CREATE TABLE OFFICE_REF  (
   OD_ID                 INTEGER,
   OG_ID                 INTEGER
) type=InnoDB;

/*==============================================================*/
/* 表名：科室详情表  Table Name(OFFICE_DETAIL)                    */
/*==============================================================*/
CREATE TABLE OFFICE_DETAIL  (
   OD_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   OD_NAME               VARCHAR(64),
   OD_ORDER              INTEGER  DEFAULT 1,
   OD_BELONG             INTEGER,
   OD_IN_CHARGE          VARCHAR(64),
   OD_TYPE               VARCHAR(128),
   OD_NOTE               VARCHAR(128),
   OD_TELEPHONE          VARCHAR(32),
   OD_EMAIL              VARCHAR(64),
   OD_SITE               VARCHAR(256),
   OD_LOCATION           VARCHAR(512),
   OD_BED_COUNTER        INTEGER,
   OD_DESC               VARCHAR(512),
   OD_ACADEMIC_POSITION  VARCHAR(128),
   OD_TECH_ADV           VARCHAR(512),
   OD_RESEARCH_DIRECTION VARCHAR(512),
   PRIMARY KEY (OD_ID)
) type=InnoDB;

alter table OFFICE_DETAIL auto_increment= 1050;

/*==============================================================*/
/* 表名：人员信息表  Table Name(STAFF_INFO)                       */
/*==============================================================*/
CREATE TABLE STAFF_INFO  (
   SI_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   SI_NAME               VARCHAR(64),
   SI_NUMBER             VARCHAR(16),
   SI_LEADER             INTEGER,
   SI_DOCTOR             INTEGER,
   SI_TALENT             INTEGER,
   PRIMARY KEY (SI_ID)
) type=InnoDB;

alter table STAFF_INFO auto_increment= 1000;

/*==============================================================*/
/* 表名：人才类型表  Table Name(TALENT_GENRE)                     */
/*==============================================================*/
CREATE TABLE TALENT_GENRE  (
   TG_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   TG_TYPE               INTEGER,
   TG_NAME               VARCHAR(64),
   TG_DESC               VARCHAR(64),
   PRIMARY KEY (TG_ID)
) type=InnoDB;

alter table TALENT_GENRE auto_increment= 1000;

/*==============================================================*/
/* 表名：门诊信息表  Table Name(OUTPATIENT_SERVICE)               */
/*==============================================================*/
CREATE TABLE OUTPATIENT_SERVICE  (
   OS_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   OS_CATE               VARCHAR(128),
   OS_TIME               VARCHAR(128),
   OS_LOCATION           VARCHAR(128),
   OS_LIMIT              INTEGER,
   OS_STATUS             INTEGER,
   OS_COST               VARCHAR(128),
   OS_BOOK_LINK          VARCHAR(128),
   PRIMARY KEY (OS_ID)
) type=InnoDB;

alter table OUTPATIENT_SERVICE auto_increment= 1000;

/*==============================================================*/
/* 表名：领导所属表  Table Name(LEADER_GROUP_REF)                 */
/*==============================================================*/
CREATE TABLE LEADER_GROUP_REF  (
   LI_ID                 INTEGER,
   GM_ID                 INTEGER
) type=InnoDB;

/*==============================================================*/
/* 表名：领导信息表  Table Name(LEADER_INFO)                      */
/*==============================================================*/
CREATE TABLE LEADER_INFO  (
   LI_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   LI_CATE               INTEGER,
   LI_CURRENT            INTEGER,
   LI_NAME               VARCHAR(64),
   LI_HOLD_PERIOD        VARCHAR(32),
   LI_HOLD_START         DATE,
   LI_HOLD_END           DATE,
   LI_QUARTERS           VARCHAR(64),
   LI_TECH_POSITION      VARCHAR(128),
   LI_TELEPHONE          VARCHAR(32),
   LI_EMAIL              VARCHAR(64),
   LI_PORTRAIT           VARCHAR(256),
   LI_RANG               VARCHAR(128),
   LI_ORDER              INTEGER,
   LI_RESUME             VARCHAR(128),
   LI_DESC               VARCHAR(128),
   PRIMARY KEY (LI_ID)
) type=InnoDB;

alter table LEADER_INFO auto_increment= 1000;

/*==============================================================*/
/* 表名：医师分院科室信息表  Table Name(DOCTOR_OFFICE_REF)         */
/*==============================================================*/
CREATE TABLE DOCTOR_OFFICE_REF  (
   LI_ID                 INTEGER,
   GM_ID                 INTEGER,
   OD_ID                 INTEGER
) type=InnoDB;

/*==============================================================*/
/* 表名：医师人才类型表  Table Name(DOCTOR_TALENT_REF)            */
/*==============================================================*/
CREATE TABLE DOCTOR_TALENT_REF  (
   DI_ID                 INTEGER,
   TG_ID                 INTEGER
) type=InnoDB;

/*==============================================================*/
/* 表名：医师信息表  Table Name(DOCTOR_INFO)                      */
/*==============================================================*/
CREATE TABLE DOCTOR_INFO  (
   DI_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   DI_NAME               VARCHAR(64),
   DI_POSITION           VARCHAR(64),
   DI_DEPTTYPE           INTEGER,
   DI_PORTRAIT           VARCHAR(256),
   DI_TAL_TYPE           VARCHAR(64),
   OS_EXPERT_ID          INTEGER,
   OS_FAMOUS_ID          INTEGER,
   DI_ORDER              INTEGER,
   DI_SEX                INTEGER,
   DI_EDUCATION          VARCHAR(16),
   DI_RESUME             VARCHAR(512),
   DI_MAJOR              VARCHAR(512),
   DI_RESEARCH_DIRECTION VARCHAR(512),
   DI_ACCOMPLISHMENT     VARCHAR(512),
   PRIMARY KEY (DI_ID)
) type=InnoDB;

alter table DOCTOR_INFO auto_increment= 1000;


/*==============================================================*/
/* 表名：用户科室表表  Table Name(USESR_DEPT_REF)                 */
/*==============================================================*/
CREATE TABLE USESR_DEPT_REF  (
   UI_ID                 INTEGER,
   OD_ID                 INTEGER
) type=InnoDB;

