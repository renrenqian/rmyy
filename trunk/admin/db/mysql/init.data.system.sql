﻿set names utf8;
/*For login user table*/
truncate table USER_INFO;
INSERT INTO USER_INFO(UI_ID, UI_ACCOUNT, UI_NAME, UI_PWD, UI_ENABLE, UI_DESC, UI_OFFIC_PHONE, UI_CELL_PHONE, UI_MAIL, UI_HOME, UI_COMPANY,UI_SHOW) VALUES(1000, 'admin', '管理员', '1b3231655cebb7a1f783eddf27d254ca', 1, 'super user', '', '10086', '', '', 'lovezq',0);
/* role */
truncate table ROLE_INFO;
INSERT INTO ROLE_INFO (RI_ID, RI_NAME, RI_PARENT, RI_LEVEL, RI_DESC, RI_SHOW) VALUES (1000, '超级管理员', 0, 1, '拥有所有权限', 0);
truncate table USER_ROLE_REF;
INSERT INTO USER_ROLE_REF (UI_ID, RI_ID) VALUES (1000, 1000);

truncate table ROLE_PRIVILEGE_REF;
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10102);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10103);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10201);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10202);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10203);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10204);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10205);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10206);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10207);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10208);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10301);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10302);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10303);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10304);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10305);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10306);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10401);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10501);
INSERT INTO ROLE_PRIVILEGE_REF (RI_ID, PI_ID) VALUES (1000, 10611);
/* PRIVILEGE*/
truncate table PRIVILEGE_INFO;
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10000, 'system', '系统管理', 0, 1, 'system', 1, 1);

INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10100, 'base', '基本操作', 10000, 2, 'basic', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10102, 'login', '登录', 10100, 3, 'login', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10103, 'logout', '登出', 10100, 3, 'logout', 1, 1);

INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10200, 'user', '用户管理', 10000, 2, 'userManage', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10201, 'addUser', '新增用户', 10200, 3, 'addUser', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10202, 'listUser', '查询用户', 10200, 3, 'listUser', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10203, 'updateUser', '更新用户信息', 10200, 3, 'updateUser', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10204, 'updateUserPwd', '修改用户密码', 10200, 3, 'deleteUser', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10205, 'deleteUser', '删除用户', 10200, 3, 'deleteUser', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10206, 'batchDeleteUser', '批量删除用户', 10200, 3, 'batchDeleteUser', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10207, 'listRoleByUserId', '查询用户所具有的角色', 10200, 3, 'listRoleByUserId', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10208, 'toggleEnableUser', '启停用户', 10200, 3, 'toggleEnableUser', 1, 1);

INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10300, 'role', '角色管理', 10000, 2, 'roleManage', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10301, 'addRole', '新增角色', 10300, 3, 'addRole', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10302, 'listRole', '查询角色', 10300, 3, 'listRole', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10303, 'updateRole', '更新角色', 10300, 3, 'updateRole', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10304, 'deleteRole', '删除角色', 10300, 3, 'deleteRole', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10305, 'bacthDeleteRole', '批量删除角色', 10300, 3, 'bacthDeleteRole', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10306, 'findPrivilegeByRoleId', '查询角色的权限', 10300, 3, 'findPrivilegeByRoleId', 1, 1);

INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10400, 'operationLog', '操作日志', 10000, 2, 'operationLog', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10401, 'listOperationLog', '查询操作日志', 10400, 3, 'listOperationLog', 1, 1);

INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10500, 'updatePerson', '个人信息设置', 10000, 2, 'updatePerson', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10501, 'updatePersonInfo', '更新个人信息', 10500, 3, 'updatePersonInfo', 1, 1);
/* for config manage*/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10600, 'systemConfig', '系统配置', 0, 1, 'systemConfig', 1, 1);
/* for sysparamConfig*/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10610, 'sysparamConfig', '系统参数配置', 10600, 2, 'sysparamConfig', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(10611, 'findSystemInfo', '更新系统参数配置', 10610, 3, 'findSystemInfo', 1, 1);

/** for group manage**/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2000, 'group', '集团管理', 0, 1, 'group', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2100, 'groupmember', '集团信息管理', 2000, 2, 'groupmember', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2101, 'addGroupMember', '添加集团成员', 2100, 3, 'addGroupMember', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2102, 'searchMember', '查看集团成员', 2100, 3, 'searchMember', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2103, 'listGroupMember', '搜素集团成员', 2100, 3, 'listGroupMember', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2104, 'updateGroupMember', '更新集团成员', 2100, 3, 'updateGroupMember', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2105, 'deleteGroupMember', '删除集团成员', 2100, 3, 'deleteGroupMember', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2106, 'batchDeleteGroupMember', '批量删除集团成员', 2100, 3, 'batchDeleteGroupMember', 1, 1);
/** for dept info **/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2200, 'deptinfo', '科室信息管理', 2000, 2, 'deptinfo', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2201, 'addDept', '添加科室信息', 2200, 3, 'addDept', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2202, 'searchDept', '查看科室信息', 2200, 3, 'searchDept', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2203, 'listDept', '搜素科室信息', 2200, 3, 'listDept', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2204, 'updateDept', '更新科室信息', 2200, 3, 'updateDept', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2205, 'deleteDept', '删除科室信息', 2200, 3, 'deleteDept', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2206, 'batchDeleteDept', '批量删除科室信息', 2200, 3, 'deleteDept', 1, 1);

/** for leader info **/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2300, 'leaderinfo', '领导信息管理', 2000, 2, 'leaderinfo', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2301, 'addLeader', '添加领导信息', 2300, 3, 'addLeader', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2302, 'searchLeader', '查看领导信息', 2300, 3, 'searchLeader', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2303, 'listLeader', '搜素领导信息', 2300, 3, 'listLeader', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2304, 'updateLeader', '更新领导信息', 2300, 3, 'updateLeader', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2305, 'deleteLeader', '删除领导信息', 2300, 3, 'deleteLeader', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2306, 'batchDeleteLeader', '批量删除领导信息', 2300, 3, 'deleteLeader', 1, 1);

/** for content info **/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2400, 'contentinfo', '内容信息管理', 2000, 2, 'contentinfo', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2401, 'addContent', '添加内容信息', 2400, 3, 'addContent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2402, 'searchContent', '查看内容信息', 2400, 3, 'searchContent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2403, 'listContent', '搜素内容信息', 2400, 3, 'listContent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2404, 'updateContent', '更新内容信息', 2400, 3, 'updateContent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2405, 'deleteContent', '删除内容信息', 2400, 3, 'deleteContent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2406, 'batchDeleteContent', '批量删除内容信息', 2400, 3, 'deleteContent', 1, 1);

/** for online info **/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2500, 'consultationinfo', '咨询信息管理', 2000, 2, 'consultationinfo', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2501, 'addConsultation', '添加咨询信息', 2500, 3, 'addConsultation', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2502, 'searchConsultation', '查看咨询信息', 2500, 3, 'searchConsultation', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2503, 'listConsultation', '搜素咨询信息', 2500, 3, 'listConsultation', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2504, 'updateConsultation', '更新咨询信息', 2500, 3, 'updateConsultation', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2505, 'deleteConsultation', '删除咨询信息', 2500, 3, 'deleteConsultation', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2506, 'batchDeleteConsultation', '批量删除咨询信息', 2500, 3, 'deleteConsultation', 1, 1);

/** for column info **/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2600, 'columninfo', '栏目信息管理', 2000, 2, 'columninfo', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2601, 'addColumn', '添加栏目信息', 2600, 3, 'addColumn', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2602, 'searchColumn', '查看栏目信息', 2600, 3, 'searchColumn', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2603, 'listColumn', '搜素栏目信息', 2600, 3, 'listColumn', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2604, 'updateColumn', '更新栏目信息', 2600, 3, 'updateColumn', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2605, 'deleteColumn', '删除栏目信息', 2600, 3, 'deleteColumn', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2606, 'batchDeleteColumn', '批量删除栏目信息', 2600, 3, 'deleteColumn', 1, 1);

/** for Talent info **/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2700, 'talentinfo', '人才类型管理', 2000, 2, 'talentinfo', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2701, 'addTalent', '添加人才类型', 2700, 3, 'addTalent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2702, 'searchTalent', '查看人才类型', 2700, 3, 'searchTalent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2703, 'listTalent', '搜素人才类型', 2700, 3, 'listTalent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2704, 'updateTalent', '更新人才类型', 2700, 3, 'updateTalent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2705, 'deleteTalent', '删除人才类型', 2700, 3, 'deleteTalent', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2706, 'batchDeleteTalent', '批量删除人才类型', 2700, 3, 'deleteTalent', 1, 1);

/** for DeptType info **/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2800, 'depttypeinfo', '科室类型管理', 2000, 2, 'depttypeinfo', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2801, 'addDeptType', '添加科室类型', 2800, 3, 'addDeptType', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2802, 'searchDeptType', '查看科室类型', 2800, 3, 'searchDeptType', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2803, 'listDeptType', '搜素科室类型', 2800, 3, 'listDeptType', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2804, 'updateDeptType', '更新科室类型', 2800, 3, 'updateDeptType', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2805, 'deleteDeptType', '删除科室类型', 2800, 3, 'deleteDeptType', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2806, 'batchDeleteDeptType', '批量删除科室类型', 2800, 3, 'deleteDeptType', 1, 1);

/** for doctor info **/
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2900, 'doctorinfo', '医师信息管理', 2000, 2, 'doctorinfo', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2901, 'addDoctor', '添加医师信息', 2900, 3, 'addDoctor', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2902, 'searchDoctor', '查看医师信息', 2900, 3, 'searchDoctor', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2903, 'listDoctor', '搜素医师信息', 2900, 3, 'listDoctor', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2904, 'updateDoctor', '更新医师信息', 2900, 3, 'updateDoctor', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2905, 'deleteDoctor', '删除医师信息', 2900, 3, 'deleteDoctor', 1, 1);
INSERT INTO PRIVILEGE_INFO(PI_ID, PI_METHOD, PI_NAME, PI_PARENT, PI_LEVEL, PI_DESC, PI_SHOW, PI_NEED_LOGON) VALUES(2906, 'batchDeleteDoctor', '批量删除医师信息', 2900, 3, 'deleteDoctor', 1, 1);





