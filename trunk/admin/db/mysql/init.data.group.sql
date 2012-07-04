﻿set names utf8;
/*inti the GROUP_MEMBER table*/
truncate table GROUP_MEMBER;
INSERT INTO GROUP_MEMBER(GM_ID, GM_NAME, GM_NAME_SHORT, GM_ADDRESS, GM_POSTAL, GM_TELEPHONE, GM_FAX, GM_EMAIL) VALUES(1000, '杭州市第一人民医院', '市一', '', '', '', '', '');
INSERT INTO GROUP_MEMBER(GM_ID, GM_NAME, GM_NAME_SHORT, GM_ADDRESS, GM_POSTAL, GM_TELEPHONE, GM_FAX, GM_EMAIL) VALUES(1001, '杭州市妇幼保健院', '妇幼', '', '', '', '', '');
INSERT INTO GROUP_MEMBER(GM_ID, GM_NAME, GM_NAME_SHORT, GM_ADDRESS, GM_POSTAL, GM_TELEPHONE, GM_FAX, GM_EMAIL) VALUES(1002, '杭州市肿瘤医院（吴山院区）', '吴山', '', '', '', '', '');
INSERT INTO GROUP_MEMBER(GM_ID, GM_NAME, GM_NAME_SHORT, GM_ADDRESS, GM_POSTAL, GM_TELEPHONE, GM_FAX, GM_EMAIL) VALUES(1003, '杭州市老年病医院（城北院区）', '城北', '', '', '', '', '');
INSERT INTO GROUP_MEMBER(GM_ID, GM_NAME, GM_NAME_SHORT, GM_ADDRESS, GM_POSTAL, GM_TELEPHONE, GM_FAX, GM_EMAIL) VALUES(1004, '杭州市妇女医院（钱江新城院区）', '钱江', '', '', '', '', '');
INSERT INTO GROUP_MEMBER(GM_ID, GM_NAME, GM_NAME_SHORT, GM_ADDRESS, GM_POSTAL, GM_TELEPHONE, GM_FAX, GM_EMAIL) VALUES(1005, '五云山健康管理中心', '五云山', '', '', '', '', '');

/*inti the OFFICE_DETAIL table*/
truncate table OFFICE_DETAIL;
INSERT INTO OFFICE_DETAIL(OD_ID, OD_NAME, OD_ORDER, OD_BELONG, OD_IN_CHARGE, OD_TELEPHONE, OD_EMAIL, OD_SITE, OD_LOCATION, OD_BED_COUNTER) VALUES(1000, '肿瘤内科', 20, 1000, '潘月龙', '0571-87065701-21681', 'zlnk@126.com', '', '市一医院2号楼6楼', 70);
INSERT INTO OFFICE_DETAIL(OD_ID, OD_NAME, OD_ORDER, OD_BELONG, OD_IN_CHARGE, OD_TELEPHONE, OD_EMAIL, OD_SITE, OD_LOCATION, OD_BED_COUNTER) VALUES(1001, '中医师', 20, 1000, '顾文平', '0571-87065701-21681', 'gwp@126.com', '', '市一医院2号楼6楼', 70);

/*inti the COLUMN_INFO table*/
truncate table COLUMN_INFO;
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1000, '网站首页', '', 1, 0, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1100, '医院概览', '', 1, 0, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1101, '集团介绍', '', 2, 1100, 1, '集团成员展示（重要）', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1102, '医院简介', '', 2, 1100, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1103, '院长寄语', '', 2, 1100, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1104, '医院历史', '', 2, 1100, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1105, '重大记事', '', 2, 1100, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1106, '领导班子', '', 2, 1100, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1111, '现任领导', '', 3, 1106, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1112, '历任领导', '', 3, 1106, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1200, '医疗中心', '', 1, 0, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1201, '癌症中心', '', 2, 1200, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1202, '心脑血管中心', '', 2, 1200, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1203, '危孕产妇中心', '', 2, 1200, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1204, '健康体检中心', '', 2, 1200, 1, '体检套餐说明，体检注意事项介绍等', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1300, '科室导航', '', 1, 0, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1301, '临床科室', '', 1, 1300, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1302, '省市重点', '', 1, 1300, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1303, '特色专科', '', 1, 1300, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1304, '医技科室', '', 1, 1300, 1, '', sysdate(), 1000);
INSERT INTO COLUMN_INFO(CI_ID, CI_TITAL, CI_ICON, CI_LEVEL, CI_PARENT, CI_NEWS, CI_DESC, CI_ADD_TIME, CI_ADDER) VALUES(1305, '职能科室', '', 1, 1300, 1, '', sysdate(), 1000);

/*inti the OFFICE_GENRE table*/
truncate table OFFICE_GENRE;
INSERT INTO OFFICE_GENRE(OG_ID, OG_NAME, OG_CODE, OG_DESC, OG_LEVEL, OG_PARENT) VALUES(1000, '临床科室', 'lcks', '', 1, 0);
INSERT INTO OFFICE_GENRE(OG_ID, OG_NAME, OG_CODE, OG_DESC, OG_LEVEL, OG_PARENT) VALUES(1001, '省市重点', 'sszd', '', 1, 0);
INSERT INTO OFFICE_GENRE(OG_ID, OG_NAME, OG_CODE, OG_DESC, OG_LEVEL, OG_PARENT) VALUES(1002, '医技科室', 'yjks', '', 1, 0);
INSERT INTO OFFICE_GENRE(OG_ID, OG_NAME, OG_CODE, OG_DESC, OG_LEVEL, OG_PARENT) VALUES(1003, '特色专科', 'tszk', '', 1, 0);
INSERT INTO OFFICE_GENRE(OG_ID, OG_NAME, OG_CODE, OG_DESC, OG_LEVEL, OG_PARENT) VALUES(1004, '职能科室', 'znks', '', 1, 0);

/*inti the TALENT_GENRE table*/
truncate table TALENT_GENRE;
INSERT INTO TALENT_GENRE(TG_ID, TG_TYPE, TG_NAME, TG_DESC) VALUES(1000, 1, '博士生导师', '');
INSERT INTO TALENT_GENRE(TG_ID, TG_TYPE, TG_NAME, TG_DESC) VALUES(1001, 1, '硕士生导师', '');
INSERT INTO TALENT_GENRE(TG_ID, TG_TYPE, TG_NAME, TG_DESC) VALUES(1002, 1, '151人才', '');
INSERT INTO TALENT_GENRE(TG_ID, TG_TYPE, TG_NAME, TG_DESC) VALUES(1003, 1, '131人才', '');
INSERT INTO TALENT_GENRE(TG_ID, TG_TYPE, TG_NAME, TG_DESC) VALUES(1004, 1, '南医大学教授', '');
INSERT INTO TALENT_GENRE(TG_ID, TG_TYPE, TG_NAME, TG_DESC) VALUES(1005, 1, '南医大学副教授', '');
INSERT INTO TALENT_GENRE(TG_ID, TG_TYPE, TG_NAME, TG_DESC) VALUES(1006, 1, '浙江省医学会主委/副主委', '');
INSERT INTO TALENT_GENRE(TG_ID, TG_TYPE, TG_NAME, TG_DESC) VALUES(1007, 1, '杭州市医学会主任', '');



