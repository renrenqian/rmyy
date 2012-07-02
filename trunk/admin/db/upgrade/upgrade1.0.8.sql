CREATE TABLE EMPLOYEE_RECRUITMENT  (
   ER_ID                 INTEGER               NOT NULL  AUTO_INCREMENT,
   ER_POSITION           VARCHAR(128),
   ER_LOCATION           VARCHAR(128),
   ER_PUBLISH_DATE       DATETIME,
   ER_EXPIRY_DATE        DATETIME,
   ER_RECRUIT_NO         INTEGER,
   ER_RECRUIT_AVAILABLE  INTEGER,
   ER_ATTACHEMENT        VARCHAR(256),
   ER_REQUIREMENT        VARCHAR(4096),
   PRIMARY KEY (ER_ID)
) type=InnoDB;

alter table EMPLOYEE_RECRUITMENT auto_increment= 1000;

CREATE TABLE USESR_DEPT_REF  (
   UI_ID                 INTEGER,
   OD_ID                 INTEGER
) type=InnoDB;

ALTER TABLE CONTENT_DETAIL ADD CD_DISPLAY_IMAGE VARCHAR(256);

ALTER TABLE CONTENT_DETAIL ADD CD_DISPLAY_IMAGE VARCHAR(256);
ALTER TABLE CONTENT_DETAIL MODIFY COLUMN CD_AUDIT_RESULT int DEFAULT '0';
