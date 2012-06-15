/**
 * CmcpseConstant.java
 */
package com.kevin.common.constant;

import java.util.Hashtable;
import java.util.Map;

public interface SystemConstant {/*定义成接口避免被实例化*/
    /** 用户启停状态  **/
    static final int USER_ENABLE = 1;//启用用户
    static final int USER_DISABLE = 0;//停用用户
    /** 当前登入用户 key **/
    static final String CURRENT_LOGIN_USER_NAME = "CURRENT_LOGIN_USER_NAME";
    static final String CURRENT_LOGIN_USER_ID = "CURRENT_LOGIN_USER_ID";
    static final String CURRENT_LOGIN_USER_KEY = "CURRENT_LOGIN_USER";

    static final String CMCPSE_ALL_PRIVILEGE="_CMCPSE_ALL_PRIVILEGE_KEY_";

    /** 消息状态 **/
    static final Integer MSG_NEW = 0;//新消息
    static final Integer MSG_READ = 1;//已读消息

    /** 审核状态 **/
    static final int AUDIT_NO_AUDIT=0;//未审核
    static final int AUDIT_SUCCESS=1;//审核成功
    static final int AUDIT_FAIL=2;//审核失败
    static final int AUDIT_RETRY_AUDIT=3;//已修改待重审

    /** 请求返回结果代码 **/
    static final int NO_PRIVILEGE=-401;//无权访问
    static final int NO_LOGIN=-402;//未登录或会话超时
    static final int SESSION_TIMEOUT=-403;//会话超时 
    /** 传输协议 **/
    static final int FTP=1;
    static final int HTTP=2;
    static final int TCP=3;
    /** 发布状态 **/
    static final int PUBLISH_STATE_WAITING=0;
    static final int PUBLISH_STATE_PUBLISHING=1;
    static final int PUBLISH_STATE_PUBLISH_SUCCESS=2;
    static final int PUBLISH_STATE_PUBLISH_FAIL=3;
    static final int PUBLISH_STATE_MOVING=4;
    static final int PUBLISH_STATE_MOVE_SUCCESS=5;
    static final int PUBLISH_STATE_MOVE_FAIL=6; 
    /**
     * 系统参数MAP
     */
    static final Map<String,String> SYS_PARAM_MAP = new Hashtable<String,String>();
    static final Map<String,String> SENSITIVITY_FILTER=new Hashtable<String,String>();
    /**
     * 系统参数KEY
     */
    static final String DATA_SRC_ROOT_KEY="DATA_SRC_ROOT";
    static final String DATA_DST_ROOT_KEY="DATA_DST_ROOT";
    static final String DATA_PUBLISH_ROOT_KEY="DATA_PUBLISH_ROOT";

      /** 属性文件名 **/
    public static final String PROPERTIES_FILE_SYSTEM = "/system.properties";
    /** 素材文件内容类型 **/
    public static final  String MATERIAL_FILE_CONTENT_TYPE_TXT = "1";//文本
    public static final  String MATERIAL_FILE_CONTENT_TYPE_IMG = "2";//图片
    /** material video type value='3' **/
    public static final  String MATERIAL_FILE_CONTENT_TYPE_VIDEO = "3";//视频
}